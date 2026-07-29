package br.com.erudio.environment;

import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

@Service
public class InstanceInformationService implements ApplicationListener<WebServerInitializedEvent> {

    private String port;

    /**
     * Buscando a porta que está sendo utilizada para subir a aplicação
    **/
    @Override
    public void onApplicationEvent(WebServerInitializedEvent event) {
        this.port = String.valueOf(event.getWebServer().getPort());
    }

    public String retrieverServerPort() {
        return port;
    }
}
