package fr.jfc.ptut.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import fr.jfc.ptut.entity.Soigner;

public interface SoignerRepository extends JpaRepository<Soigner, Integer> {


}
