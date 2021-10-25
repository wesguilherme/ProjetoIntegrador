package com.projetointegrador.repository;

import com.projetointegrador.entity.Representative;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepresentativePersistence extends JpaRepository<Representative, Long> {

    Optional<Representative> findByCpf(String cpf);
}
