package com.shoponline.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shoponline.Entity.Product;

@Repository
public interface ProductDAO extends JpaRepository<Product, Integer>{

}
