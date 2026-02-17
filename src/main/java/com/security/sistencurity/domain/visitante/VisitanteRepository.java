package com.security.sistencurity.domain.visitante;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;

public interface VisitanteRepository extends JpaRepository<Visitante,Long> {
    Page<Visitante> findByMorador_Id(Long idmorador, Pageable page);
}
