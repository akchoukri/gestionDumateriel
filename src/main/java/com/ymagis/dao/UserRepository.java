package com.ymagis.dao;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ymagis.model.AppUser;


public interface UserRepository  extends JpaRepository<AppUser, Long>{
      public AppUser findByUsername(String username);
}