package com.dev.gestao.repository;

import com.dev.gestao.domain.carne.Carne;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarneRepository extends JpaRepository<Carne, Integer>{
}
