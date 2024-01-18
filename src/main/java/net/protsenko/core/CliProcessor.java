package net.protsenko.core;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.protsenko.core.tools.DateTimeTools;
import net.protsenko.models.entities.Instrument;
import net.protsenko.models.entities.Timeframe;
import net.protsenko.service.InstrumentService;
import net.protsenko.service.impl.DataServiceImpl;
import net.protsenko.service.impl.TimeFrameServiceImpl;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

@Component
@ShellComponent
@AllArgsConstructor
@Slf4j
public class CliProcessor {

    private final DataServiceImpl dataService;
    private final InstrumentService instrumentService;
    private final TimeFrameServiceImpl timeFrameService;

    @ShellMethod(key = "ui", value = "update instruments")
    public void updateInstruments(
            @ShellOption(value = {"-t", "--type"},
                    help = "data type to update (instruments [bond,etf,currency,stock])",
                    defaultValue = ShellOption.NULL)
            String type)
            throws Exception {
        if (type != null) {
            String[] types = type.split(",");
            for (int i = 0; i < types.length; i++)
                dataService.updateInstruments(types[i]);
        } else
            dataService.updateInstruments();
    }

    @ShellMethod(key = "uc", value = "update candlesticks")
    public void updateCandlesticks(
            @ShellOption(value = {"-i", "--id"},
                    help = "instrument identifier (ticker or figi)")
            String iIdentifier,
            @ShellOption(value = {"-t", "--tf"},
                    help = "timeframe (min1,min2,min5,min10,min15,min30,hour1,day1,week1,mon1)",
                    defaultValue = "day1")
            String iTimeFrame,
            @ShellOption(value = {"-b", "--bp"},
                    help = "the beginning of period",
                    defaultValue = ShellOption.NULL)
            String iBegPeriod,
            @ShellOption(value = {"-e", "--ep"},
                    help = "the end of period",
                    defaultValue = ShellOption.NULL)
            String iEndPeriod) {
        ZonedDateTime begPeriod = iBegPeriod == null ?
                GlobalContext.BEG_DATE :
                DateTimeTools.parseDate(iBegPeriod);
        ZonedDateTime endPeriod = iEndPeriod == null ?
                ZonedDateTime.now().truncatedTo(ChronoUnit.DAYS).plusDays(1) :
                DateTimeTools.parseDate(iEndPeriod);
        if (begPeriod != null && endPeriod != null)
            DateTimeTools.checkInterval(begPeriod, endPeriod);
        String[] identifiers = iIdentifier.split(",");
        String[] timeframes = iTimeFrame.split(",");
        for (int i = 0; i < identifiers.length; i++) {
            for (int j = 0; j < timeframes.length; j++) {
                Instrument instrument = instrumentService.getByFigiOrTicker(identifiers[i]);
                if (instrument == null)
                    throw new IllegalArgumentException(String.format(
                            "Unknown instrument identifier: %s, try update instruments first (ui)", identifiers[i]));
                Timeframe timeframe = timeFrameService.getByCode(timeframes[j]);
                if (timeframe == null)
                    throw new IllegalArgumentException(String.format("Unsupported timeframe: %s", timeframes[j]));
                dataService.updateCandlesticks(instrument, timeframe, begPeriod, endPeriod);
            }
        }
    }
}
