package com.shoponline.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shoponline.Entity.Role;
@Repository
public interface RoleDAO extends JpaRepository<Role, String>{

}
