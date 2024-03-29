package net.protsenko.service.impl;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import net.protsenko.core.tools.DateTimeTools;
import net.protsenko.models.entities.Candlestick;
import net.protsenko.models.entities.Instrument;
import net.protsenko.models.entities.InstrumentType;
import net.protsenko.models.entities.Timeframe;
import net.protsenko.service.DataService;
import net.protsenko.service.InstrumentService;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class DataServiceImpl implements DataService {

    private final ContextServiceImpl contextService;
    private final InstrumentService instrumentService;
    private final InstrumentTypeServiceImpl instrumentTypeService;
    private final CandlestickServiceImpl candlestickService;

    @Override
    public void updateInstruments() throws Exception {
        log.info("Updating instruments");
        List<Instrument> instruments = contextService.getInstruments();
        instrumentService.saveAllIfNotExists(instruments);
        log.info(String.format("Updating instruments: Records processed: %d", instruments.size()));
    }

    @Override
    public void updateInstruments(String instrumentTypeCode) throws Exception {
        InstrumentType instrumentType = instrumentTypeService.getByCode(instrumentTypeCode);
        if (instrumentType == null) throw new IllegalArgumentException(String.format(
                "Illegal instrument type: %s", instrumentTypeCode));
        updateInstruments(instrumentType);
    }

    @Override
    public void updateInstruments(InstrumentType instrumentType) throws Exception {
        log.info(String.format("Updating instruments of type: %s", instrumentType.getCode()));
        List<Instrument> instruments = contextService.getInstruments(instrumentType);
        instrumentService.saveAllIfNotExists(instruments);
        log.info(String.format("Updating instruments: Records processed: %d", instruments.size()));
    }

    @Override
    public void updateCandlesticks(@NonNull Instrument instrument, @NonNull Timeframe timeframe) {
        candlestickService.getCandlesticks(instrument, timeframe);
    }

    @Override
    public void updateCandlesticks(
            @NonNull Instrument instrument,
            @NonNull Timeframe timeframe,
            ZonedDateTime begPeriod,
            ZonedDateTime endPeriod) {

        log.info(String.format("Updating candlesticks : %s [%s] %s - %s",
                instrument.getTicker(),
                timeframe,
                DateTimeTools.getTimeFormatted(begPeriod),
                DateTimeTools.getTimeFormatted(endPeriod)));
        List<Candlestick> candlesticks = contextService.getCandlesticks(instrument, timeframe, begPeriod, endPeriod);
        candlesticks.forEach(candlestickService::saveAllIfNotExists);
        log.info(String.format("Updating candlesticks: Records processed: %d", candlesticks.size()));
    }

}
