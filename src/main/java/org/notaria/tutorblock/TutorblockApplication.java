package org.notaria.tutorblock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.notaria.tutorblock.data.UserDetail;
import org.notaria.tutorblock.data.UserDetailRepository;
import org.notaria.tutorblock.data.Miner;
import org.notaria.tutorblock.data.MinerRepository;
import java.math.BigDecimal;
import java.util.List;

@SpringBootApplication
public class TutorblockApplication {

	public static void main(String[] args) {
		SpringApplication.run(TutorblockApplication.class, args);
	}

	@Bean
	CommandLineRunner initData(UserDetailRepository userRepo, MinerRepository minerRepo) {
		return args -> {
			if (userRepo.count() == 0) {
				userRepo.saveAll(List.of(
						UserDetail.builder()
								.username("admin")
								.password("$2a$12$5AclOZUB22EaUdyYCORv6OkftlZ66Q9XWyXc2550rmaNG6cUvLste")
								.rol("ADMIN")
								.nomCompleto("Administrador")
								.dni("10000001")
								.saldo(new BigDecimal("1000.00"))
								.firmaDigital("firmaAdmin")
								.email("admin@correo.com")
								.build(),
						UserDetail.builder()
								.username("user1")
								.password("$2a$12$26jFafgSvn2WigULFfXzB.KQJX0.SEI4Smi0chneHyBK0pjMDUL3a")
								.rol("USER")
								.nomCompleto("Usuario Uno")
								.dni("10000002")
								.saldo(new BigDecimal("500.00"))
								.firmaDigital("firmaUser1")
								.email("user1@correo.com")
								.build(),
						UserDetail.builder()
								.username("user2")
								.password("$2a$12$26jFafgSvn2WigULFfXzB.KQJX0.SEI4Smi0chneHyBK0pjMDUL3a")
								.rol("USER")
								.nomCompleto("Usuario Dos")
								.dni("10000003")
								.saldo(new BigDecimal("300.00"))
								.firmaDigital("firmaUser2")
								.email("user2@correo.com")
								.build(),
						UserDetail.builder()
								.username("user3")
								.password("$2a$12$26jFafgSvn2WigULFfXzB.KQJX0.SEI4Smi0chneHyBK0pjMDUL3a")
								.rol("USER")
								.nomCompleto("Usuario Tres")
								.dni("10000004")
								.saldo(new BigDecimal("400.00"))
								.firmaDigital("firmaUser3")
								.email("user3@correo.com")
								.build(),
						UserDetail.builder()
								.username("user4")
								.password("$$2a$12$26jFafgSvn2WigULFfXzB.KQJX0.SEI4Smi0chneHyBK0pjMDUL3a")
								.rol("USER")
								.nomCompleto("Usuario Cuatro")
								.dni("10000005")
								.saldo(new BigDecimal("250.00"))
								.firmaDigital("firmaUser4")
								.email("user4@correo.com")
								.build(),
						UserDetail.builder()
								.username("user5")
								.password("$2a$12$26jFafgSvn2WigULFfXzB.KQJX0.SEI4Smi0chneHyBK0pjMDUL3a") // reemplaza tú con hash real
								.rol("USER")
								.nomCompleto("Usuario Cinco")
								.dni("10000006")
								.saldo(new BigDecimal("600.00"))
								.firmaDigital("firmaUser5")
								.email("user5@correo.com")
								.build()
				));
			}
			if (minerRepo.count() == 0) {
				minerRepo.saveAll(List.of(
					Miner.builder().nombre("Minero Uno").dni("20000001").clave("clave1").build(),
					Miner.builder().nombre("Minero Dos").dni("20000002").clave("clave2").build(),
					Miner.builder().nombre("Minero Tres").dni("20000003").clave("clave3").build()
				));
			}
		};
	}
}
