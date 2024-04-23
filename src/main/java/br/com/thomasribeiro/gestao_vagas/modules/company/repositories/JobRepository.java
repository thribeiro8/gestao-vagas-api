package br.com.thomasribeiro.gestao_vagas.modules.company.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.thomasribeiro.gestao_vagas.modules.company.entities.JobEntity;

@Repository
public interface JobRepository extends JpaRepository<JobEntity, UUID> {

    // select * from job where description like %filter%
    List<JobEntity> findByDescriptionContainingIgnoreCase(String description);
}
