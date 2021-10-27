package com.projetointegrador.repository;

import com.projetointegrador.entity.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SectionPersistence extends JpaRepository<Section, String> {

    Optional<Section> findBySectionCode(String code);

    Optional<Section> findByRepresentativeRepresentativeId(Long id);

}