package fr.jfc.ptut.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import fr.jfc.ptut.entity.Medicament;

public interface MedicamentRepository extends JpaRepository<Medicament, Integer> {
}