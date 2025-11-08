package com.senac.demo.denuncia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/denuncias")
public class DenunciaController {
    private final String uploadDir = "src/main/resources/assets/images/";


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





    @PostMapping("/image")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        // Verificar se o arquivo foi enviado
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Por favor, envie um arquivo.");
        }

        try {
            // Obter o nome original do arquivo
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());

            // Verifique se o nome do arquivo é válido
            if (fileName.contains("..")) {
                return ResponseEntity.badRequest().body("Nome de arquivo inválido.");
            }

            // Crie o diretório de destino, se não existir
            Path path = Paths.get(uploadDir + fileName);
            Files.createDirectories(path.getParent());  // cria a pasta assets/images

            // Salve o arquivo no diretório
            file.transferTo(path);

            return ResponseEntity.ok("Imagem salva com sucesso: " + path.toString());
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Erro ao salvar o arquivo: " + e.getMessage());
        }
    }

}
