package br.com.thomasribeiro.gestao_vagas.modules.candidate.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity
@Table(name = "candidate")
public class CandidateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Schema(example = "Jon Snow", requiredMode = RequiredMode.REQUIRED, description = "Nome do candidato")
    private String name;

    @Schema(example = "jonsnow", requiredMode = RequiredMode.REQUIRED, description = "Username do candidato")
    @NotBlank()
    @Pattern(regexp = "\\S+", message = "O campo [username] não deve estar vazio nem conter espaços")
    private String username;

    @Schema(example = "jonsnow@gmail.com", requiredMode = RequiredMode.REQUIRED, description = "E-mail do candidato")
    @Email(message = "O campo [email] deve conter um e-mail válido")
    private String email;

    @Schema(example = "admin@1234", minLength = 10, maxLength = 100, requiredMode = RequiredMode.REQUIRED, description = "Senha do candidato")
    @Length(min = 10, max = 100, message = "A senha deve conter entre 10 e 100 caracteres")
    private String password;

    @Schema(example = "Desenvolvedor python", requiredMode = RequiredMode.REQUIRED, description = "Breve descrição do candidato")
    private String description;

    private String curriculum;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
