package com.projetointegrador.repository;

import com.projetointegrador.entity.States;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatesPersistence extends JpaRepository<States, String> {

    Optional<States> findByCep(String id);
}
