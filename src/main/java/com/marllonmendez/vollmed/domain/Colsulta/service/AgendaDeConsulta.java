package com.marllonmendez.vollmed.domain.Colsulta.service;

import com.marllonmendez.vollmed.domain.Colsulta.dto.AgendamentoConsultaDTO;
import com.marllonmendez.vollmed.domain.Colsulta.dto.DetalhadosConsultaDTO;
import com.marllonmendez.vollmed.domain.Colsulta.entity.Consulta;
import com.marllonmendez.vollmed.domain.Colsulta.repository.IConsultaRepository;
import com.marllonmendez.vollmed.domain.Colsulta.valid.AgendamentoConsultaValid;
import com.marllonmendez.vollmed.domain.Medico.entity.Medico;
import com.marllonmendez.vollmed.domain.Medico.repository.IMedicoRepository;
import com.marllonmendez.vollmed.domain.Paciente.repository.IPacienteRepository;
import com.marllonmendez.vollmed.infra.exception.ValidException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaDeConsulta {

    @Autowired
    private IConsultaRepository iConsultaRepository;

    @Autowired
    private IMedicoRepository iMedicoRepository;

    @Autowired
    private IPacienteRepository iPacienteRepository;

    @Autowired
    private List<AgendamentoConsultaValid> validadores;

    public DetalhadosConsultaDTO agendar(AgendamentoConsultaDTO dto) {
        if (!iPacienteRepository.existsById(dto.idPaciente())) {
            throw new ValidException("Id do paciente não existe!");
        }

        if (dto.idMedico() != null && !iMedicoRepository.existsById(dto.idMedico())) {
            throw new ValidException("Id o médico não existe!");
        }

        // Aplicando o SOLID
        // S: Cada classe foi criada para trabalhar com um unico propósito.
        // O: esse forEach percorre as clases, todas estão fechadas para mundança, porém está aberta para extensões, exemplo, criar novas classes.
        // D: Esta classe "AgendaDeConsulta" depende de uma abstração "AgendamentoConsultaValid"
        validadores.forEach(v -> v.validar(dto));

        var medico = escolherMedico(dto);
        if (medico == null) {
            throw new ValidException("Não existe um médico disponível nessa data!");
        }
        var paciente = iPacienteRepository.getReferenceById(dto.idPaciente());
        var consulta = new Consulta(null, medico, paciente, dto.data(),true);
        iConsultaRepository.save(consulta);

        return new DetalhadosConsultaDTO(consulta);
    }

    public Page<DetalhadosConsultaDTO> listarConsultas(Pageable pageable) {
        return iConsultaRepository.findAllByDisponivelTrue(pageable).map(DetalhadosConsultaDTO::new);
    }

    private Medico escolherMedico(AgendamentoConsultaDTO dto) {
        if (dto.idMedico() != null) {
            return iMedicoRepository.getReferenceById(dto.idMedico());
        }

        if (dto.ramo() == null) {
            throw new ValidException("Ramo de atução é obrigatório quando médico não for escolhido!");
        }

        return iMedicoRepository.medicoLivreAleatorio(dto.ramo(), dto.data());
    }

}
