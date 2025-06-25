package org.notaria.tutorblock.controller;

import lombok.RequiredArgsConstructor;
import org.notaria.tutorblock.data.Ganancia;
import org.notaria.tutorblock.data.GananciaRepository;
import org.notaria.tutorblock.data.Transaccion;
import org.notaria.tutorblock.data.TransaccionRepository;
import org.notaria.tutorblock.data.UserDetail;
import org.notaria.tutorblock.data.UserDetailRepository;
import org.notaria.tutorblock.service.TransaccionService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class TransferenciaController {
    private final UserDetailRepository userDetailRepository;
    private final TransaccionService transaccionService;
    private final TransaccionRepository transaccionRepository;
    private final GananciaRepository gananciaRepository;

    @GetMapping("/transferencia")
    public String mostrarFormularioTransferencia(Model model, Authentication authentication) {
        List<UserDetail> usuarios = userDetailRepository.findAll();
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("username", authentication.getName());
        return "transferencia";
    }

    @PostMapping("/transferencia")
    public String procesarTransferencia(
            @RequestParam Long receptorId,
            @RequestParam BigDecimal cantidad,
            Authentication authentication, // ✅ AÑADIR ESTO
            Model model) {

        String username = authentication.getName();
        UserDetail emisor = userDetailRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        String mensaje = transaccionService.transferir(emisor.getId(), receptorId, cantidad);
        model.addAttribute("mensaje", mensaje);
        model.addAttribute("username", authentication.getName());
        model.addAttribute("usuarios", userDetailRepository.findAll());

        return "transferencia";
    }

    @GetMapping("/admin/transferencias")
    public String verTransferencias(Model model) {
        List<Transaccion> transacciones = transaccionRepository.findAll();
        model.addAttribute("transacciones", transacciones);
        return "transferencias";
    }

    @GetMapping("/admin/ganancias")
    public String verGanancias(Model model) {
        List<Ganancia> ganancias = gananciaRepository.findAll();
        model.addAttribute("ganancias", ganancias);
        return "ganancias";
    }
}

