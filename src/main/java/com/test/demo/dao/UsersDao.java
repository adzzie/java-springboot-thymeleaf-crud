package com.test.demo.dao;

import com.test.demo.entity.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Description UserDao
 *
 * @author aji gojali
 */
public interface UsersDao extends JpaRepository<Users, String> {

    @Query("select u from Users u where " +
            "UPPER(u.name) like concat('%',UPPER(:search),'%' ) or " +
            "UPPER(u.email) like concat('%',UPPER(:search),'%' ) ")
    public Page<Users> searchAll(@Param("search") String search, Pageable pageable);
}
