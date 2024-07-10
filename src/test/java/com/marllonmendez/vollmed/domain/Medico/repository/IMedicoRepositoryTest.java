package com.marllonmendez.vollmed.domain.Medico.repository;

import com.marllonmendez.vollmed.domain.Colsulta.entity.Consulta;
import com.marllonmendez.vollmed.domain.Endereco.dto.EnderecoDTO;
import com.marllonmendez.vollmed.domain.Medico.dto.MedicoDTO;
import com.marllonmendez.vollmed.domain.Medico.entity.Medico;
import com.marllonmendez.vollmed.domain.Paciente.dto.CadastroPacienteDTO;
import com.marllonmendez.vollmed.domain.Paciente.entity.Paciente;
import com.marllonmendez.vollmed.enums.Ramo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
@Transactional
class MedicoRepositoryTest {

    @Autowired
    private IMedicoRepository iMedicoRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("Deveria devolver null quando único médico cadastrado não está disponível na data")
    void medicoLivreCenario1() {

        //given ou arrange
        var proxSeg = LocalDateTime.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .withHour(10)
                .withMinute(0);

        var medico = cadastrarMedico("Medico", "medico@voll.med", "225566", Ramo.CARDIOLOGIA);
        var paciente = cadastrarPaciente("Paciente", "paciente@voll.med", "44455599900");
        cadastrarConsulta(medico, paciente, proxSeg);

        //when ou act
        var medicoLivre = iMedicoRepository.medicoLivreAleatorio(Ramo.CARDIOLOGIA, proxSeg);
        //then ou assert
        assertThat(medicoLivre).isNull();
    }

    @Test
    @DisplayName("Deveria devolver médico quando ele estiver disponível na data")
    void medicoLivreCenario2() {

        //given ou arrange
        var proxSeg = LocalDateTime.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .withHour(10)
                .withMinute(0);

        var medico = cadastrarMedico("Medico", "medico@voll.med", "225566", Ramo.CARDIOLOGIA);

        //when ou act
        var medicoLivre = iMedicoRepository.medicoLivreAleatorio(Ramo.CARDIOLOGIA, proxSeg);
        //then ou assert
        assertThat(medicoLivre).isEqualTo(medico);
    }

    private void cadastrarConsulta(Medico medico, Paciente paciente, LocalDateTime data) {
        em.persist(new Consulta(null, medico, paciente, data, true));
    }

    private Medico cadastrarMedico(String nome, String email, String crm, Ramo ramo) {
        var medico = new Medico(dadosMedico(nome, email, crm, ramo));
        em.persist(medico);
        return medico;
    }

    private Paciente cadastrarPaciente(String nome, String email, String cpf) {
        var paciente = new Paciente(dadosPaciente(nome, email, cpf));
        em.persist(paciente);
        return paciente;
    }

    private MedicoDTO dadosMedico(String nome, String email, String crm, Ramo ramo) {
        return new MedicoDTO(
                nome,
                email,
                "61999999999",
                crm,
                ramo,
                dadosEndereco()
        );
    }

    private CadastroPacienteDTO dadosPaciente(String nome, String email, String cpf) {
        return new CadastroPacienteDTO(
                nome,
                email,
                "61999999999",
                cpf,
                dadosEndereco()
        );
    }

    private EnderecoDTO dadosEndereco() {
        return new EnderecoDTO(
                "rua xp",
                "77",
                "00000000",
                "bairro xp",
                "Brasilia",
                "DF",
                "01002000"
        );
    }
}
