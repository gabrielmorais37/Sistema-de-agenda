package SistemaAgenda;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface  AgendaRepository extends JpaRepository<Agenda, Long>{
    @Query("SELECT a FROM Agenda a WHERE LOWER(a.nome) = LOWER(:nome)")
    Optional<Agenda> findByNome(@Param("nome") String nome);
}
