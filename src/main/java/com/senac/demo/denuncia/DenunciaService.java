package com.senac.demo.denuncia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DenunciaService {

    @Autowired
    private DenunciaRepository denunciaRepository;

    public Denuncia criar(Denuncia denuncia) {
        return denunciaRepository.save(denuncia);
    }

    public Denuncia buscarPorId(Long id) {
        return denunciaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Denúncia não encontrada com ID: " + id));
    }

    public Denuncia buscarPorProtocolo(String protocolo) {
        return denunciaRepository.findByProtocolo(protocolo);
    }

    public List<Denuncia> listarTodas() {
        return denunciaRepository.findAll();
    }

    public Denuncia atualizar(Long id, Denuncia novaDenuncia) {
        Denuncia denuncia = buscarPorId(id);
        denuncia.setDescricao(novaDenuncia.getDescricao());
        denuncia.setEnderecoCompleto(novaDenuncia.getEnderecoCompleto());
        denuncia.setCidade(novaDenuncia.getCidade());
        denuncia.setEstado(novaDenuncia.getEstado());
        denuncia.setCep(novaDenuncia.getCep());
        denuncia.setLatitude(novaDenuncia.getLatitude());
        denuncia.setLongitude(novaDenuncia.getLongitude());
        denuncia.setStatus(novaDenuncia.getStatus());
        denuncia.setPrioridade(novaDenuncia.getPrioridade());
        denuncia.setUsuarioResponsavel(novaDenuncia.getUsuarioResponsavel());
        denuncia.setDataAtualizacao(LocalDateTime.now());

        return denunciaRepository.save(denuncia);
    }

    public void deletar(Long id) {
        denunciaRepository.deleteById(id);
    }
}
