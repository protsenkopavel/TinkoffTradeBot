package net.protsenko.repositories;

import net.protsenko.models.entities.Timeframe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeframeRepository extends JpaRepository<Timeframe, Long> {

    Timeframe findByCodeIgnoreCase(String code);

}