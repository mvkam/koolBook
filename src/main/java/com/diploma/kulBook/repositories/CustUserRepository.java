package com.diploma.kulBook.repositories;

import com.diploma.kulBook.entities.CustUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustUserRepository extends JpaRepository<CustUser, Long> {

    @Query("SELECT u FROM CustUser u where u.login = :login")
    CustUser findByLogin(@Param("login") String login);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false " +
            "END FROM CustUser u WHERE u.login = :login")
    boolean existsByLogin(@Param("login") String login);

    @Query("SELECT u FROM CustUser u where u.email = :email")
    CustUser findByEmail(@Param("email") String email);

    @Query("SELECT cu FROM CustUser cu")
    List<CustUser> findAllUsers(Pageable pageable);

    @Query("select COUNT(cu)  FROM CustUser cu")
    long countAll();

    @Query("SELECT u FROM CustUser u where u.id = :id")
    CustUser getById(@Param("id") Long id);
}
