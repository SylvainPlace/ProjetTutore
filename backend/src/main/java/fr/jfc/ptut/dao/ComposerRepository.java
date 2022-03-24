package fr.jfc.ptut.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import fr.jfc.ptut.entity.Composer;

public interface ComposerRepository extends JpaRepository<Composer, Integer> {


}
