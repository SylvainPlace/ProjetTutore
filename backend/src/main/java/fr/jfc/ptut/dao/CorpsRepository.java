package fr.jfc.ptut.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.jfc.ptut.entity.Corps;

// This will be AUTO IMPLEMENTED by Spring 
//

public interface CorpsRepository extends JpaRepository<Corps, Integer> {

}
