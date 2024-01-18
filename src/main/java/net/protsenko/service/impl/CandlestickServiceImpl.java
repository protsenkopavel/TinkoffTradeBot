package net.protsenko.service.impl;

import lombok.AllArgsConstructor;
import net.protsenko.core.tools.DateTimeTools;
import net.protsenko.models.entities.Candlestick;
import net.protsenko.models.entities.Instrument;
import net.protsenko.models.entities.Timeframe;
import net.protsenko.repositories.CandlestickRepository;
import net.protsenko.service.CandlestickService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class CandlestickServiceImpl implements CandlestickService {

    private CandlestickRepository candlestickRepository;

    @Override
    public Candlestick getById(Long id) {
        return candlestickRepository.getById(id);
    }

    @Override
    public List<Candlestick> getCandlesticks(Instrument instrument, Timeframe timeframe) {
        return getCandlesticks(instrument, timeframe, null, null);
    }

    @Override
    public List<Candlestick> getCandlesticks(Instrument instrument, Timeframe timeframe, ZonedDateTime begPeriod) {
        return getCandlesticks(instrument, timeframe, begPeriod);
    }

    @Override
    public List<Candlestick> getCandlesticks(
            Instrument instrument, Timeframe timeframe, ZonedDateTime begPeriod, ZonedDateTime endPeriod) {
        if (begPeriod == null)
            begPeriod = GlobalContext.BEG_DATE;
        if (endPeriod == null)
            endPeriod = ZonedDateTime.now();
        DateTimeTools.checkInterval(begPeriod, endPeriod);
        return candlestickRepository.getCandlestickByInstrumentAndTimeframeAndSinceBetweenOrderBySince(
                instrument, timeframe, begPeriod, endPeriod);
    }

    @Override
    @Transactional
    public void saveAllIfNotExists(Candlestick candlestick) {
        if (!candlestickRepository.existsByInstrumentAndTimeframeAndSince(
                candlestick.getInstrument(), candlestick.getTimeframe(), candlestick.getSince()))
            candlestickRepository.saveAndFlush(candlestick);
    }
}