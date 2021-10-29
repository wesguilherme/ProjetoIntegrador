package com.projetointegrador.repository;

import com.projetointegrador.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TypePersistence extends JpaRepository<Type, Long> {

}
