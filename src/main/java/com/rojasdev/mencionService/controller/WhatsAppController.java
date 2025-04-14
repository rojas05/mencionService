package com.rojasdev.mencionService.controller;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WhatsAppController {

    // Configura las credenciales de Twilio

    @Value("${TWILIO_ACCOUNT_SID}")
    private String accountSid;

    @Value("${TWILIO_AUTH_TOKEN}")
    private String authToken;

    @Value("${TWILIO_NUMBER}")
    private String twilioNumber;

    // Ruta para recibir mensajes de WhatsApp
    @PostMapping("/webhook")
    public String handleMessage(
            @RequestParam("Body") String body,
            @RequestParam("From") String sender) {

        // Si alguien escribe "@all"
        if (body.trim().equalsIgnoreCase("@all")) {
            // Aquí puedes personalizar la lógica para mencionar a todos
            String[] groupMembers = {
                    "whatsapp:+573187469791",  // Reemplaza con los números del grupo
                    "whatsapp:+573152805018"
            };

            for (String member : groupMembers) {
                Message.creator(
                        new PhoneNumber(member),
                        new PhoneNumber(twilioNumber),
                        "Hola, han mencionado a todos en el grupo. ¡Revisen el chat!, example 1"
                ).create();
            }

            return "Se ha notificado a todos los miembros del grupo.";
        }

        return "Comando no reconocido.";
    }
}