package net.protsenko.core;

import lombok.extern.slf4j.Slf4j;
import ru.tinkoff.invest.openapi.OpenApi;
import ru.tinkoff.invest.openapi.model.rest.SandboxRegisterRequest;
import ru.tinkoff.invest.openapi.okhttp.OkHttpOpenApi;

@Slf4j
public class ApiConnector {
    private final Parameters parameters;
    private OpenApi openApi;

    public ApiConnector(Parameters parameters) {
        this.parameters = parameters;
    }

    public OpenApi getOpenApi() throws Exception {
        if (openApi == null) {
            close();
            log.info("Create Tinkoff API connection");
            openApi = new OkHttpOpenApi(parameters.getToken(), parameters.isSandBoxMode());
            if (openApi.isSandboxMode()) {
                openApi.getSandboxContext().performRegistration(new SandboxRegisterRequest()).join();
            }
        }
        return openApi;
    }

    public void close() throws Exception {
        if (openApi != null) {
            openApi.close();
            log.info("Tinkoff API connection has been closed");
        }
    }
}
