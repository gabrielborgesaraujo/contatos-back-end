package com.gba.contatosbackend.controller;

import com.gba.contatosbackend.models.Contato;
import com.gba.contatosbackend.repository.ContatoRepository;
import com.gba.contatosbackend.utils.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Map;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class ContatoController {

    @Autowired
    private ContatoRepository contatoRepository;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/contatos")
    public List<Contato> getAllContatos() {
        return this.contatoRepository.findAll();
    }


    @PostMapping("/contatos")
    public ResponseEntity<Object> createContato(@RequestPart Contato contato, @RequestParam MultipartFile image) throws IOException {
        String fileName = StringUtils.cleanPath(image.getOriginalFilename());
        contato.setImgPath(fileName);
        System.out.println(contato.getNome());
        Contato addContato = this.contatoRepository.save(contato);
        String uploadDir = "profilePics/" + addContato.getId();

        FileUploadUtil.saveFile(uploadDir, fileName, image);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/contatos/{id}")
    public ResponseEntity<Contato> getContatoById(@PathVariable int id){
        return ResponseEntity.of(this.contatoRepository.findById(id));
    }

    @PutMapping("/contatos/{id}")
    public ResponseEntity<Contato> updateContato(@PathVariable int id, @RequestBody Contato contato){
        Contato contatoAtual = this.contatoRepository.findById(id).orElseThrow(() -> new RuntimeException("405: Bad Request"));
        contatoAtual.setNome(contato.getNome());
        contatoAtual.setEmail(contato.getEmail());
        contatoAtual.setTelefone(contato.getTelefone());
        contatoAtual.setDataNascimento(contato.getDataNascimento());
        contatoAtual.setImgPath(contato.getImgPath());

        Contato contatoAtualizado = this.contatoRepository.save(contatoAtual);
        return ResponseEntity.ok(contatoAtualizado);
    }

    @DeleteMapping("/contatos/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteContato(@PathVariable int id){
        Contato contato = this.contatoRepository.findById(id).orElseThrow(() -> new RuntimeException("Contato não encontrado"));
        this.contatoRepository.delete(contato);
        Map<String, Boolean> response = Map.of("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
