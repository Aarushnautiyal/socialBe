package com.socialbe.main.repository.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);


    @Query(value = "SELECT * FROM social_users AS u WHERE u.first_name ILIKE CONCAT('%', :value, '%') OR u.last_name ILIKE CONCAT('%', :value, '%') OR u.email ILIKE CONCAT('%', :value, '%')", nativeQuery = true)
    List<User> search(@Param("value") String value);


//    @Query(value = "select u from socialUsers u where u.firstName LIKE %:value% OR u.lastName LIKE %:value% OR u.email LIKE %:value%",nativeQuery = true)
//    List<User> search(@Param("value") String value);
}
