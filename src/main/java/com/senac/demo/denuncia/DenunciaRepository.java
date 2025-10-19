package com.senac.demo.denuncia;

import com.senac.demo.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DenunciaRepository extends JpaRepository<Denuncia, Long> {
    Denuncia findByProtocolo(String protocolo);

    List<Denuncia> findByUsuarioResponsavel(Usuario responsavel);

    List<Denuncia> findByUsuarioDenunciante(Usuario denunciante);

}
