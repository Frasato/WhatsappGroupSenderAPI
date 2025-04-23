package com.shalom.groupsender.controller;

import com.shalom.groupsender.dtos.RequestMessageDtos;
import com.shalom.groupsender.services.WhatsAppConnectorService;
import com.shalom.groupsender.services.WhatsappGroupeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/whatsapp")
@CrossOrigin(origins = "*")
public class WhatsappGroupeController {

    @Autowired
    private WhatsappGroupeService whatsappGroupeService;
    @Autowired
    private WhatsAppConnectorService whatsAppConnectorService;

    @PostMapping("/groups/message")
    public ResponseEntity<?> send(@RequestBody RequestMessageDtos requestMessageDtos){
        whatsappGroupeService.sendGroupMessage(
                requestMessageDtos.groupId(),
                requestMessageDtos.message()
        );

        return ResponseEntity.ok().build();
    }

    @GetMapping("/groups")
    public ResponseEntity<?> listGroups(){
        return ResponseEntity.ok(whatsappGroupeService.listAllGroupsSimplified());
    }

    @GetMapping("/qrcode")
    public ResponseEntity<String> getQrCode(){
        String qrCode = whatsAppConnectorService.getQrcode();
        return ResponseEntity.ok(qrCode);
    }

}
