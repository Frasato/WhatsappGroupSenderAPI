package com.shalom.groupsender.services;

import org.springframework.stereotype.Service;

@Service
public class WhatsAppConnectorService {

    private volatile String qrcode;

    public String getQrcode(){
        return qrcode;
    }

    public void setQrcode(String qrcode){
        this.qrcode = qrcode;
    }

}
