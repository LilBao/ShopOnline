package com.shoponline.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shoponline.Entity.Authority;

@Repository
public interface AuthorizeDAO extends JpaRepository<Authority, Integer>{

}
