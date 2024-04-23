package br.com.thomasribeiro.gestao_vagas.modules.company.controllers;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import br.com.thomasribeiro.gestao_vagas.modules.company.dto.CreateJobDTO;
import br.com.thomasribeiro.gestao_vagas.modules.company.entities.CompanyEntity;
import br.com.thomasribeiro.gestao_vagas.modules.company.repositories.CompanyRepository;
import br.com.thomasribeiro.gestao_vagas.utils.TestUtils;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class CreateJobControllerTest {

        private MockMvc mvc;

        @Autowired
        private WebApplicationContext context;

        @Autowired
        private CompanyRepository companyRepository;

        @Before
        public void setup() {
                mvc = MockMvcBuilders
                                .webAppContextSetup(context)
                                .apply(SecurityMockMvcConfigurers.springSecurity())
                                .build();
        }

        @Test
        public void shouldBeAbleToCreateANewJob() throws Exception {

                var company = CompanyEntity.builder()
                                .description("COMPANY_DESCRIPTION")
                                .email("email@company.com")
                                .password("1234567890")
                                .username("COMPANY_USERNAME")
                                .name("COMPANY_NAME").build();

                // salvar imediatamente sem esperar que toda requisição seja feita
                company = this.companyRepository.saveAndFlush(company);

                var createJobDTO = CreateJobDTO.builder()
                                .benefits("BENEFITS_TEST")
                                .description("DESCRIPTION_TEST")
                                .level("LEVEL_TEST")
                                .build();

                var result = mvc.perform(
                                MockMvcRequestBuilders.post("/company/job/")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(TestUtils.objectToJson(createJobDTO))
                                                .header("Authorization",
                                                                TestUtils.generateToken(company.getId(),
                                                                                "JAVAGAS_@123#")))
                                .andExpect(MockMvcResultMatchers.status().isOk());

                System.out.println(result);
        }

        @Test
        public void shouldNotBeAbleToCreateANewJobIfCompanyNotFound() throws Exception {
                var createJobDTO = CreateJobDTO.builder()
                                .benefits("BENEFITS_TEST")
                                .description("DESCRIPTION_TEST")
                                .level("LEVEL_TEST")
                                .build();

                mvc.perform(MockMvcRequestBuilders.post("/company/job/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(TestUtils.objectToJson(createJobDTO))
                                .header("Authorization",
                                                TestUtils.generateToken(UUID.randomUUID(),
                                                                "JAVAGAS_@123#")))
                                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        }

}
