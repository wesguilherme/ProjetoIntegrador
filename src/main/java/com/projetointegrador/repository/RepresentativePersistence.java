package com.projetointegrador.repository;

import com.projetointegrador.entity.Representative;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepresentativePersistence extends JpaRepository<Representative, Long> {

    Representative findByCpf(String cpf);
}
