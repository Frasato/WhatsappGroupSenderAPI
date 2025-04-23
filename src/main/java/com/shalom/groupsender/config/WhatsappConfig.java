package com.shalom.groupsender.config;

import com.shalom.groupsender.services.WhatsAppConnectorService;
import it.auties.whatsapp.api.Whatsapp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WhatsappConfig {

    @Autowired
    private WhatsAppConnectorService whatsAppConnectorService;

    @Bean
    public Whatsapp whatsappClient(){
        return Whatsapp.webBuilder()
                .lastConnection()
                .unregistered(qr -> whatsAppConnectorService.setQrcode(qr))
                .addLoggedInListener(api -> System.out.println("Conectado ao whatsapp " + api))
                .addDisconnectedListener(reason -> System.out.println("Desconectado: " + reason))
                .connect()
                .join();
    }

}
