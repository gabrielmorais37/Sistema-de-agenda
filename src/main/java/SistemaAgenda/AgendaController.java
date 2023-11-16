package SistemaAgenda;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class AgendaController {
        
    AgendaRepository repository;

    @GetMapping("/agenda/listarContatos")
    public ResponseEntity<?> obterTodaAgenda(){
        try {
            List<Agenda> listaContatos = repository.findAll();
    
            if (listaContatos.isEmpty()) {
                return ResponseEntity.ok("A lista de contatos está vazia.");
            } else {
                return ResponseEntity.ok(listaContatos);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erro ao obter a lista de contatos: " + e.getMessage());
        }
    }

    @GetMapping("/agenda/buscarPorId/{id}")
    public ResponseEntity<?> obterPorId(@PathVariable Long id){
    try {
        Optional<Agenda> agendaOptional = repository.findById(id);

        if (agendaOptional.isPresent()) {
            return ResponseEntity.ok(agendaOptional.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("O ID: '" + id + "' não foi encontrado.");
        }
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("Erro ao buscar por ID: " + e.getMessage());
    }
    }

    @GetMapping("/agenda/buscarPorNome/{nome}")
    public ResponseEntity<?> obterPorNome(@PathVariable String nome){
        try {
            Agenda agenda = repository.findByNome(nome);
    
            if (agenda != null) {
                return ResponseEntity.ok(agenda);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("O nome '" + nome + "' não foi encontrado.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erro ao buscar por nome: " + e.getMessage());
        }
    }

    @PostMapping("/agenda/salvarContato")
    public ResponseEntity<?> salvarAgenda(@RequestBody Agenda agenda){
        try {
            Agenda savedAgenda = repository.save(agenda);
            return ResponseEntity.ok(savedAgenda);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erro ao salvar a agenda: " + e.getMessage());
        }
    }

    @PutMapping("/agenda/atualizarContato/{id}")
    public ResponseEntity<?> atualizarContato(@PathVariable Long id, @RequestBody Agenda novaAgenda){
        try {
            Optional<Agenda> agendaOptional = repository.findById(id);

            if (agendaOptional.isPresent()) {
                Agenda agenda = agendaOptional.get();
                
                if (novaAgenda.getNome() != null) {
                    agenda.setNome(novaAgenda.getNome());
                }
                if (novaAgenda.getCelular() != null) {
                    agenda.setCelular(novaAgenda.getCelular());
                }
                if (novaAgenda.getEmail() != null) {
                    agenda.setEmail(novaAgenda.getEmail());
                }

                Agenda agendaAtualizada = repository.save(agenda);
                return ResponseEntity.ok("Contato com ID " + id + " foi atualizado!" + agendaAtualizada);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("O contatocom ID: '" + id + "' não foi encontrado.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erro ao atualizar o contato: " + e.getMessage());
        }
    }

    @DeleteMapping("/agenda/deletarPorId/{id}")
    public ResponseEntity<String> deletarContato(@PathVariable Long id){
        try {
            repository.deleteById(id);
            return ResponseEntity.ok("O contato com ID: " + id + " foi excluída com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erro ao excluir a agenda com ID " + id + ": " + e.getMessage());
        }
    }
}
