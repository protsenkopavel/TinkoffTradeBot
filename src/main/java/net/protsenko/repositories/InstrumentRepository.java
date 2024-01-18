package net.protsenko.repositories;

import net.protsenko.models.entities.Instrument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstrumentRepository extends JpaRepository<Instrument, Long> {

    Instrument findByFigiIgnoreCase(String figi);

    Instrument findByTickerIgnoreCase(String ticker);

    Instrument findByFigiIgnoreCaseOrTickerIgnoreCase(String figi, String ticker);

    boolean existsInstrumentByFigiIgnoreCase(String figi);

}
