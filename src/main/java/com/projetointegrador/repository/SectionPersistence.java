package com.projetointegrador.repository;

import com.projetointegrador.entity.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectionPersistence extends JpaRepository<Section, Long> {

}