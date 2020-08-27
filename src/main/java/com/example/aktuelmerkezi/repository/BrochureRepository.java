package com.example.aktuelmerkezi.repository;

import com.example.aktuelmerkezi.model.Brochure;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BrochureRepository extends JpaRepository<Brochure, UUID> {
}
