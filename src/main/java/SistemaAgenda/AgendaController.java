package SistemaAgenda;

import java.net.URI;
import java.util.ArrayList;
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
    public ResponseEntity<?> obterTodaAgenda() {
        try {
            List<Agenda> listaContatos = repository.findAll();

            if (listaContatos.isEmpty()) {
                return ResponseEntity.ok("A lista de contatos está vazia.");
            } else {
                List<String> contatosAtivos = new ArrayList<>();
                List<String> contatosInativos = new ArrayList<>();

                for (Agenda contato : listaContatos) {
                    String status = (contato.getAtivo() == 1) ? "Ativo" : "Inativo";
                    String infoContato = "ID: " + contato.getId() + 
                                    " | Nome: " + contato.getNome() +
                                    " | Celular: " + contato.getCelular() +
                                    " | Email: " + contato.getEmail() + 
                                    " | Status: " + status + "\n";

                    if (status.equals("Ativo")) {
                        contatosAtivos.add(infoContato);
                    } else {
                        contatosInativos.add(infoContato);
                    }
                }

                int numAtivos = contatosAtivos.size();
                int numInativos = contatosInativos.size();

                String linhaSeparadoraAtivos = "======= Contatos Ativos ======= Número de contatos ativos: " + numAtivos + "=======\n";
                String linhaSeparadoraInativos = "======= Contatos Inativos ======= Número de contatos inativos: " + numInativos + " =======\n";

                List<String> resultadoFinal = new ArrayList<>();
                resultadoFinal.add(linhaSeparadoraAtivos);
                resultadoFinal.addAll(contatosAtivos);
                resultadoFinal.add(linhaSeparadoraInativos);
                resultadoFinal.addAll(contatosInativos);

                String resultado = String.join(System.lineSeparator(), resultadoFinal);

                return ResponseEntity.ok(resultado);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erro ao obter a lista de contatos: " + e.getMessage());
        }
    }

    @GetMapping("/agenda/buscarPorId/{id}")
    public ResponseEntity<?> obterPorId(@PathVariable Long id) {
        try {
            Optional<Agenda> agendaOptional = repository.findById(id);

            if (agendaOptional.isPresent()) {
                Agenda contato = agendaOptional.get();
                String status = (contato.getAtivo() == 1) ? "Ativo" : "Inativo";

                String infoContato = "ID: " + contato.getId() +
                        " | Nome: " + contato.getNome() +
                        " | Celular: " + contato.getCelular() +
                        " | Email: " + contato.getEmail() +
                        " | Status: " + status;
                
                return ResponseEntity.ok(infoContato);
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
            Optional<Agenda> agendaOptional = repository.findByNome(nome);
    
            if (agendaOptional.isPresent()) {
                Agenda contato = agendaOptional.get();
                String status = (contato.getAtivo() == 1) ? "Ativo" : "Inativo";
    
                String infoContato = "ID: " + contato.getId() +
                        " | Nome: " + contato.getNome() +
                        " | Celular: " + contato.getCelular() +
                        " | Email: " + contato.getEmail() +
                        " | Status: " + status;
    
                return ResponseEntity.ok(infoContato);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("O contato com nome '" + nome + "' não foi encontrado.");
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
            URI location = new URI("/agenda/salvarContato/" + savedAgenda.getId());
            return ResponseEntity.created(location)
                .body("Novo contato criado com sucesso! \n\n ID: " + savedAgenda.getId() +  
                                                                "\n Nome: "+ savedAgenda.getNome() + 
                                                                "\n Celular: " + savedAgenda.getCelular() + 
                                                                "\n Email: " + savedAgenda.getEmail());
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

                if (agenda.getAtivo() == 1) {
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
                    return ResponseEntity.ok("Contato com ID " + id + " foi atualizado! \n\n Nome: " + agendaAtualizada.getNome() + 
                                                                                    "\n Celular: " + agendaAtualizada.getCelular() + 
                                                                                    "\n Email: " + agendaAtualizada.getEmail());
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("O contato com ID: '" + id + "' está inativo e não pode ser atualizado.");
                }
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("O contato com ID: '" + id + "' não foi encontrado.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erro ao atualizar o contato: " + e.getMessage());
        }
    }

    @DeleteMapping("/agenda/desativarPorId/{id}")
    public ResponseEntity<?> desativarPorId(@PathVariable Long id){
        try {
            Optional<Agenda> agendaOptional = repository.findById(id);
    
            if (agendaOptional.isPresent()) {
                Agenda agenda = agendaOptional.get();
    
                if (agenda.getAtivo() == 0) {
                    return ResponseEntity.badRequest().body("O contato com ID: '" + id + "' já está desativado.");
                }
    
                agenda.setAtivo(0); 
    
                Agenda contatoDesativado = repository.save(agenda);
                return ResponseEntity.ok("Contato com ID " + id + " foi desativado!");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("O contato com ID: '" + id + "' não foi encontrado.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erro ao desativar o contato: " + e.getMessage());
        }
    }    

    @PutMapping("/agenda/ativarContatoPorId/{id}")
    public ResponseEntity<?> ativarContato(@PathVariable Long id){
        try {
            Optional<Agenda> agendaOptional = repository.findById(id);

            if (agendaOptional.isPresent()) {
                Agenda agenda = agendaOptional.get();

                if (agenda.getAtivo() == 1) {
                    return ResponseEntity.badRequest().body("O contato com ID: '" + id + "' já está ativado.");
                }

                agenda.setAtivo(1); 

                Agenda contatoAtivado = repository.save(agenda);
                return ResponseEntity.ok("Contato com ID " + id + " foi ativado!");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("O contato com ID: '" + id + "' não foi encontrado.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erro ao ativar o contato: " + e.getMessage());
        }
    }    
}
