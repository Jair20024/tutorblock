package org.notaria.tutorblock.service;

import org.springframework.stereotype.Service;

@Service
public class EmailService {

    public void enviarCorreoSimulado(String destinatario, String asunto, String contenido) {
        System.out.println("\n======= CORREO SIMULADO =======");
        System.out.println("Para: " + destinatario);
        System.out.println("Asunto: " + asunto);
        System.out.println("Contenido:\n" + contenido);
        System.out.println("================================\n");
    }
}