package com.shalom.groupsender.services;

import it.auties.whatsapp.api.Whatsapp;
import it.auties.whatsapp.model.chat.Chat;
import it.auties.whatsapp.model.jid.Jid;
import it.auties.whatsapp.model.jid.JidProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class WhatsappGroupeService {

    @Autowired
    private Whatsapp api;

    public void sendGroupMessage(String groupJid, String message){
        var chat = api.store()
                .findChatByJid(Jid.of(groupJid))
                .filter(Chat::isGroup)
                .orElseThrow(() -> new IllegalArgumentException("Grupo não encontrado: " + groupJid));

        api.sendMessage(chat, message);
    }

    public List<Chat> listAllGroups(){
        return api.store()
                .chats()
                .stream()
                .filter(Chat::isGroup)
                .collect(Collectors.toList());
    }

    public List<Map<String, String>> listAllGroupsSimplified(){
        return listAllGroups().stream()
                .map(chat -> Map.of(
                        "name", chat.name(),
                        "jid", chat.jid().toString()
                ))
                .collect(Collectors.toList());
    }
}
