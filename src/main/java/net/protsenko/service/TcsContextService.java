package net.protsenko.service;

import net.protsenko.models.entities.Instrument;
import net.protsenko.models.entities.Timeframe;
import ru.tinkoff.invest.openapi.model.rest.Candle;
import ru.tinkoff.invest.openapi.model.rest.MarketInstrument;

import java.time.ZonedDateTime;
import java.util.List;

public interface TcsContextService {

    List<MarketInstrument> getBonds() throws Exception;

    List<MarketInstrument> getEtfs() throws Exception;

    List<MarketInstrument> getCurrencies() throws Exception;

    List<MarketInstrument> getStocks() throws Exception;

    List<Candle> getCandles(Instrument instrument, Timeframe timeframe, ZonedDateTime begPeriod, ZonedDateTime endPeriod);

}
