package net.protsenko.repositories;

import net.protsenko.models.entities.Candlestick;
import net.protsenko.models.entities.Instrument;
import net.protsenko.models.entities.Timeframe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;

@Repository
public interface CandlestickRepository extends JpaRepository<Candlestick, Long> {

    List<Candlestick> getCandlestickByInstrumentAndTimeframeAndSinceBetweenOrderBySince(
            Instrument instrument, Timeframe timeframe, ZonedDateTime begPeriod, ZonedDateTime endPeriod);

    boolean existsByInstrumentAndTimeframeAndSince(Instrument instrument, Timeframe timeframe, ZonedDateTime since);

}
