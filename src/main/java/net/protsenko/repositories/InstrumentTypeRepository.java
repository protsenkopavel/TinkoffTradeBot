package net.protsenko.repositories;

import net.protsenko.models.entities.InstrumentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstrumentTypeRepository extends JpaRepository<InstrumentType, Long> {

    InstrumentType findByCodeIgnoreCase(String code);

}