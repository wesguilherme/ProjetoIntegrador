package com.projetointegrador.repository;

import com.projetointegrador.entity.BlackFriday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BlackFridayPersistence extends JpaRepository<BlackFriday, String> {

    Optional<BlackFriday> findByProductId(String productId);

}
