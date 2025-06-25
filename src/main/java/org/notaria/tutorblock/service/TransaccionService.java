package org.notaria.tutorblock.service;

import jakarta.transaction.Transactional;
import org.notaria.tutorblock.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@Service
public class TransaccionService {

    @Autowired
    private UserDetailRepository userDetailRepository;

    @Autowired
    private TransaccionRepository transaccionRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private BlockchainService blockchainService;

    @Transactional
    public String transferir(Long idEmisor, Long idReceptor, BigDecimal cantidad) {
        Optional<UserDetail> emisorOpt = userDetailRepository.findById(idEmisor);
        Optional<UserDetail> receptorOpt = userDetailRepository.findById(idReceptor);

        if (emisorOpt.isEmpty() || receptorOpt.isEmpty()) {
            return "Usuario no encontrado";
        }

        UserDetail emisor = emisorOpt.get();
        UserDetail receptor = receptorOpt.get();

        if (emisor.getSaldo().compareTo(cantidad) < 0) {
            return "Saldo insuficiente";
        }

        if (idEmisor.equals(idReceptor)) {
            return "No puedes transferirte a ti mismo.";
        }

        // Actualizar saldos
        emisor.setSaldo(emisor.getSaldo().subtract(cantidad));
        receptor.setSaldo(receptor.getSaldo().add(cantidad));
        userDetailRepository.save(emisor);
        userDetailRepository.save(receptor);

        // Guardar transacción
        Transaccion tx = Transaccion.builder()
                .emisor(emisor)
                .receptor(receptor)
                .cantidad(cantidad)
                .fecha(LocalDate.now())
                .hora(LocalTime.now())
                .estado("pendiente")
                .build();
        transaccionRepository.save(tx);

        // Simular envío de correo
        String contenido = String.format("""
                Hola %s,

                Se ha recibido una transferencia de %s soles de parte de %s.

                Fecha: %s
                Hora: %s
                Estado: %s

                Gracias por usar TutorBlock.
                """,
                receptor.getNomCompleto(),
                cantidad.toPlainString(),
                emisor.getNomCompleto(),
                tx.getFecha(),
                tx.getHora(),
                tx.getEstado()
        );
        emailService.enviarCorreoSimulado(
                receptor.getEmail(),
                "Confirmación de transferencia recibida",
                contenido
        );

        // Verificar si se debe minar
        blockchainService.verificarYMinarSiCorresponde();

        return "Transferencia realizada";
    }
}
