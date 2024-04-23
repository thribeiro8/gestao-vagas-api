package br.com.thomasribeiro.gestao_vagas.modules.candidate.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.thomasribeiro.gestao_vagas.exceptions.JobNotFoundException;
import br.com.thomasribeiro.gestao_vagas.exceptions.UserNotFoundException;
import br.com.thomasribeiro.gestao_vagas.modules.candidate.entities.ApplyJobEntity;
import br.com.thomasribeiro.gestao_vagas.modules.candidate.repositories.ApplyJobRepository;
import br.com.thomasribeiro.gestao_vagas.modules.candidate.repositories.CandidateRepository;
import br.com.thomasribeiro.gestao_vagas.modules.company.repositories.JobRepository;

@Service
public class ApplyJobCandidateService {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private ApplyJobRepository applyJobRepository;

    // ID do candidato
    // ID da vaga
    public ApplyJobEntity execute(UUID idCandidate, UUID idJob) {
        // Validar se candidato existe
        this.candidateRepository.findById(idCandidate)
                .orElseThrow(() -> {
                    throw new UserNotFoundException();
                });

        // Validar se vaga existe
        this.jobRepository.findById(idJob)
                .orElseThrow(() -> {
                    throw new JobNotFoundException();
                });

        // Candidato se inscrever na vaga
        var applyJob = ApplyJobEntity.builder()
                .candidateId(idCandidate)
                .jobId(idJob)
                .build();

        return this.applyJobRepository.save(applyJob);
    }

}
