package com.osmy.wallet.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osmy.wallet.entities.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    @Modifying
    @Query(value = "UPDATE users SET deleted_at = CURRENT_TIMESTAMP WHERE ID = ?1", nativeQuery=true)
    Integer softDeleteById(Long ID);
}