package br.com.thomasribeiro.gestao_vagas.modules.candidate.services;

import static org.assertj.core.api.Assertions.assertThat; // para importar o assertThat()
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.thomasribeiro.gestao_vagas.exceptions.JobNotFoundException;
import br.com.thomasribeiro.gestao_vagas.exceptions.UserNotFoundException;
import br.com.thomasribeiro.gestao_vagas.modules.candidate.entities.ApplyJobEntity;
import br.com.thomasribeiro.gestao_vagas.modules.candidate.entities.CandidateEntity;
import br.com.thomasribeiro.gestao_vagas.modules.candidate.repositories.ApplyJobRepository;
import br.com.thomasribeiro.gestao_vagas.modules.candidate.repositories.CandidateRepository;
import br.com.thomasribeiro.gestao_vagas.modules.company.entities.JobEntity;
import br.com.thomasribeiro.gestao_vagas.modules.company.repositories.JobRepository;

@ExtendWith(MockitoExtension.class) // para poder fazer a inicialização dos objetos
public class ApplyJobCandidateServiceTest {

    @InjectMocks // injeta dados mockados (parecido com autowired só que para testes)
    private ApplyJobCandidateService applyJobCandidateService;

    @Mock // informar para classe que esse mock é uma dependência do InjectMock
    private CandidateRepository candidateRepository;

    @Mock
    private JobRepository jobRepository;

    @Mock
    private ApplyJobRepository applyJobRepository;

    @Test
    @DisplayName("Should not be able to apply job with candidate not found")
    public void shouldNotBeAbleToApplyJobWithCandidateNotFound() {
        try {
            this.applyJobCandidateService.execute(null, null);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(UserNotFoundException.class);
        }
    }

    @Test
    @DisplayName("Should not be able to apply job with job not found")
    public void shouldNotBeAbleToApplyJobWithJobNotFound() {
        var idCandidate = UUID.randomUUID();

        var candidate = new CandidateEntity();
        candidate.setId(idCandidate);

        when(this.candidateRepository.findById(idCandidate)).thenReturn(Optional.of(candidate));

        try {
            this.applyJobCandidateService.execute(idCandidate, null);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(JobNotFoundException.class);
        }
    }

    @Test
    public void shouldBeAbleToCreateANewApplyJob() {
        var idCandidate = UUID.randomUUID();
        var idJob = UUID.randomUUID();

        var applyJob = ApplyJobEntity.builder().candidateId(idCandidate)
                .jobId(idJob)
                .build();

        var applyJobCreated = ApplyJobEntity.builder().id(UUID.randomUUID()).build();

        when(this.candidateRepository.findById(idCandidate)).thenReturn(Optional.of(new CandidateEntity()));
        when(this.jobRepository.findById(idJob)).thenReturn(Optional.of(new JobEntity()));

        when(this.applyJobRepository.save(applyJob)).thenReturn(applyJobCreated);

        var result = this.applyJobCandidateService.execute(idCandidate, idJob);

        assertThat(result).hasFieldOrProperty("id");
        assertNotNull(result.getId());
    }

}
