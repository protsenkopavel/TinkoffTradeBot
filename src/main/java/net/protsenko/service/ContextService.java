package net.protsenko.service;

import net.protsenko.models.entities.Candlestick;
import net.protsenko.models.entities.Instrument;
import net.protsenko.models.entities.InstrumentType;
import net.protsenko.models.entities.Timeframe;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

@Service
public interface ContextService {

    List<Instrument> getInstruments() throws Exception;

    List<Instrument> getInstruments(InstrumentType instrumentType) throws Exception;

    List<Candlestick> getCandlesticks(
            Instrument instrument, Timeframe timeframe, ZonedDateTime begPeriod, ZonedDateTime endPeriod);

}
