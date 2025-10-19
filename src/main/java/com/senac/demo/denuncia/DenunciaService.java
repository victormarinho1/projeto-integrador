package com.senac.demo.denuncia;

import com.senac.demo.dashboard.DenunciasPorUsuarioDTO;
import com.senac.demo.usuario.Usuario;
import com.senac.demo.usuario.UsuarioService;
import jdk.jshell.Snippet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Service
public class DenunciaService {

    @Autowired
    private DenunciaRepository denunciaRepository;

    @Autowired
    private UsuarioService usuarioService;
    public Denuncia criar(Denuncia denuncia) {
        denuncia.setProtocolo(gerarProtocolo());
        return denunciaRepository.save(denuncia);
    }

    public Denuncia buscarPorId(Long id) {
        return denunciaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Denúncia não encontrada com ID: " + id));
    }

    public List<Denuncia> buscarPorIdUsuarioResponsavel(Long id) {
        Usuario usuario = usuarioService.buscarPorId(id);
        return denunciaRepository.findByUsuarioResponsavel(usuario);
    }

    public List<Denuncia> buscarPorIdUsuarioDenunciante(Long id) {
        Usuario usuario = usuarioService.buscarPorId(id);
        return denunciaRepository.findByUsuarioDenunciante(usuario);
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

    public String gerarProtocolo() {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));

        String uuidPart = UUID.randomUUID().toString().substring(0, 8).toUpperCase();

        return "DEN-" + timestamp + "-" + uuidPart;
    }

    public Denuncia atenderDenuncia(Long id){
        Denuncia denuncia = buscarPorId(id);
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Usuario usuario = this.usuarioService.buscarPorEmail(username);
        denuncia.setUsuarioResponsavel(usuario);
        denuncia.setStatus(StatusDenuncia.EM_ANDAMENTO);
        return denunciaRepository.save(denuncia);
    }

    public List<Denuncia> buscarPorStatus(StatusDenuncia status){
        return this.denunciaRepository.findByStatus(status);
    }

    public List<DenunciasPorUsuarioDTO> getDenunciasAtendidas() {
        return denunciaRepository.countDenunciasAtendidasByUsuario();
    }
}


