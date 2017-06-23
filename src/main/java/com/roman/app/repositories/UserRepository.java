package com.roman.app.repositories;

import com.roman.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.name = :name")
    User findByName(@Param("name") String name);

    @Query("SELECT u FROM User u WHERE u.id = :id")
    User findById(@Param("id") long id);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE User u SET u.name = :name, u.age = :age WHERE u.id = :id")
    void updateById(@Param("id") Long id, @Param("name") String name, @Param("age") Integer age);
}