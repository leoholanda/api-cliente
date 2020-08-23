package br.com.brasilprev.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.brasilprev.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	
	Optional<Cliente> findByCpf(String cpf);
	
	/**
     * search by name and order by asc
     * @param nome
     * @return
     */
    List<Cliente> findAllByNomeContainingIgnoreCaseOrderByNomeAsc(String nome);

}
