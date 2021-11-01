package com.projetointegrador.repository;

import com.projetointegrador.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypePersistence extends JpaRepository<Type, Long> {

}
