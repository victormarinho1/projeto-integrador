package com.senac.demo.denuncia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Service
public class DenunciaService {

    @Autowired
    private DenunciaRepository denunciaRepository;

    public Denuncia criar(Denuncia denuncia) {
        denuncia.setProtocolo(gerarProtocolo());
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

    public  String gerarProtocolo() {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));

        String uuidPart = UUID.randomUUID().toString().substring(0, 8).toUpperCase();

        return "DEN-" + timestamp + "-" + uuidPart;
    }
}
