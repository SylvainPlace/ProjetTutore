package fr.jfc.ptut.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.jfc.ptut.dto.PopulationResult;
import fr.jfc.ptut.entity.Corps;

// This will be AUTO IMPLEMENTED by Spring 
//

public interface CorpsRepository extends JpaRepository<Corps, Integer> {


}
