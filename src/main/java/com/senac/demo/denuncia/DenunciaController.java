package com.senac.demo.denuncia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/denuncias")
public class DenunciaController {

    @Autowired
    private DenunciaService denunciaService;

    @PostMapping
    public ResponseEntity<Denuncia> criar(@RequestBody Denuncia denuncia) {
        Denuncia criada = denunciaService.criar(denuncia);
        return ResponseEntity.status(201).body(criada);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Denuncia> buscarPorId(@PathVariable Long id) {
        Denuncia denuncia = denunciaService.buscarPorId(id);
        return ResponseEntity.ok(denuncia);
    }

    @GetMapping("/usuario-responsavel/{id}")
    public ResponseEntity<List<Denuncia>> buscarPorIdUsuarioResponsavel(@PathVariable Long id) {
        List<Denuncia> denuncias = denunciaService.buscarPorIdUsuarioResponsavel(id);
        return ResponseEntity.ok(denuncias);
    }

    @GetMapping("/usuario-denunciante/{id}")
    public ResponseEntity<List<Denuncia>> buscarPorIdUsuarioDenunciante(@PathVariable Long id) {
        List<Denuncia> denuncias = denunciaService.buscarPorIdUsuarioDenunciante(id);
        return ResponseEntity.ok(denuncias);
    }


    @GetMapping("/protocolo/{protocolo}")
    public ResponseEntity<Denuncia> buscarPorProtocolo(@PathVariable String protocolo) {
        Denuncia denuncia = denunciaService.buscarPorProtocolo(protocolo);
        return ResponseEntity.ok(denuncia);
    }

    @GetMapping
    public ResponseEntity<List<Denuncia>> listarTodas() {
        List<Denuncia> denuncias = denunciaService.listarTodas();
        return ResponseEntity.ok(denuncias);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Denuncia> atualizar(@PathVariable Long id, @RequestBody Denuncia denuncia) {
        Denuncia atualizada = denunciaService.atualizar(id, denuncia);
        return ResponseEntity.ok(atualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        denunciaService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/atender")
    public ResponseEntity<Denuncia> atenderDenuncia(@PathVariable Long id) {

        Denuncia denunciaAtendida = denunciaService.atenderDenuncia(id);

        return ResponseEntity.ok(denunciaAtendida);
    }

}
