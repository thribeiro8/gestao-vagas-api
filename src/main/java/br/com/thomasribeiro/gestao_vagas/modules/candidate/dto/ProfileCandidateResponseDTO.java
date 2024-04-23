package br.com.thomasribeiro.gestao_vagas.modules.candidate.dto;

import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileCandidateResponseDTO {

    @Schema(example = "Desenvolvedor python")
    private String description;

    @Schema(example = "john")
    private String username;

    @Schema(example = "john@gmail.com")
    private String email;

    private UUID id;

    @Schema(example = "John Smith")
    private String name;
}
