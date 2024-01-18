package net.protsenko.service;

import net.protsenko.models.entities.Candlestick;
import net.protsenko.models.entities.Instrument;
import net.protsenko.models.entities.Timeframe;

import java.time.ZonedDateTime;
import java.util.List;

public interface CandlestickService {

    Candlestick getById(Long id);

    List <Candlestick> getCandlesticks(Instrument instrument, Timeframe timeframe);

    List <Candlestick> getCandlesticks(Instrument instrument, Timeframe timeframe, ZonedDateTime begPeriod);

    List<Candlestick> getCandlesticks(
            Instrument instrument, Timeframe timeframe, ZonedDateTime begPeriod, ZonedDateTime endPeriod);

    void saveAllIfNotExists(Candlestick candlestick);

}
