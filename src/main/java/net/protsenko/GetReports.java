package net.protsenko;

import net.protsenko.core.Parameters;
import net.protsenko.reports.*;
import net.protsenko.tcs.TcsApiConnector;
import net.protsenko.tcs.TcsContextProvider;
import ru.tinkoff.invest.openapi.model.rest.MarketInstrument;

import java.util.ArrayList;
import java.util.List;

public class GetReports {

    public static void main(String[] args) throws Exception {
        Parameters parameters = new Parameters(args[0], Boolean.parseBoolean(args[1]));
        TcsApiConnector apiConnector = new TcsApiConnector(parameters);
        TcsContextProvider contextProvider = new TcsContextProvider(apiConnector);
        List<CommonReport> reports = new ArrayList<>();
        reports.add(new AllBondsReport(contextProvider.getBonds().getInstruments()));
        reports.add(new AllCurrenciesReport(contextProvider.getCurrencies().getInstruments()));
        reports.add(new AllStocksReport(contextProvider.getStocks().getInstruments()));
        reports.add(new AllEtfsReport(contextProvider.getEtfs().getInstruments()));
        reports.forEach(CommonReport<MarketInstrument>::doExport);
    }
}
