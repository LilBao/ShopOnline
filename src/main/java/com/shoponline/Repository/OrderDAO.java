package com.shoponline.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shoponline.Entity.Order;

@Repository
public interface OrderDAO extends JpaRepository<Order, Integer>{
	@Query("select o from Order o where o.status = :status")
	List<Order> getOrderByStatus(@Param("status") Integer status);
	
	@Query("select o from Order o where account.username = :username")
	List<Order> getOrderByUsername(@Param("username") String username);

	@Query("Select count(o) from Order o where o.status = 0 or o.status=3")
	Integer getPendingRequest();

	@Query("SELECT SUM(o.total) from Order o where Month(o.createdate) = MONTH(GETDATE()) and Year(o.createdate) = YEAR(GETDATE()) and o.status=1")
	Float getEarningMonth();

	@Query("SELECT SUM(o.total) from Order o where Year(o.createdate) = YEAR(GETDATE()) and o.status=1")
	Float getEarningAnnual();
	
	@Query(value="select a.fullname, a.avatar, o.username ,sum(total) as 'total' from orders o inner join accounts a on o.username = a.username\r\n"
			+ "		where o.username is not null and o.status = 1 group by a.fullname, a.avatar, o.username order by total desc",nativeQuery = true)
	Page<Object[]> getTop5(Pageable pageable);
	
	@Query(value="SELECT DATEPART(WEEKDAY, aw.weekday) AS day_of_week, COALESCE(SUM(o.total), 0) AS total_revenue\r\n"
			+ "FROM (SELECT DATEADD(DAY, n, DATEADD(WEEK, DATEDIFF(WEEK, 0, GETDATE()), 0)) AS weekday\r\n"
			+ "FROM (SELECT TOP 7 ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) - 1 AS n\r\n"
			+ "FROM master.dbo.spt_values\r\n"
			+ ") AS Numbers\r\n"
			+ ") AS aw\r\n"
			+ "LEFT JOIN orders o ON aw.weekday = DATEADD(DAY, DATEDIFF(DAY, 0, o.createDate), 0)\r\n"
			+ "    AND o.STATUS = 1\r\n"
			+ "GROUP BY DATEPART(WEEKDAY, aw.weekday)\r\n"
			+ "ORDER BY DATEPART(WEEKDAY, aw.weekday);",nativeQuery = true)
	Object[] getRevenueOfWeek();
}
