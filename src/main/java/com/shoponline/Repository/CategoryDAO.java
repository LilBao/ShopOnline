package com.shoponline.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shoponline.Entity.Category;

public interface CategoryDAO extends JpaRepository<Category, Integer>{

}
