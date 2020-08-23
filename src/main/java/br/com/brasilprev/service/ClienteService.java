package br.com.brasilprev.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.brasilprev.model.Cliente;
import br.com.brasilprev.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;
	
	/**
	 * find all
	 * @return
	 */
	public List<Cliente> listaTodos() {
		return repository.findAll();
	}
	
	
	/**
	 * new client
	 * @param cliente
	 * @return
	 */
	public Cliente adiciona(Cliente cliente) {
		Optional<Cliente> cpfExistente = buscaClientePorCpf(cliente.getCpf());
		
		if(cpfExistente.isPresent()) {
			throw new ResponseStatusException(
					HttpStatus.CONFLICT, String.format("CPF %s já existe na base de dados.", cliente.getCpf()));
		}
		return repository.save(cliente);
	}
	
	/**
	 * update client
	 * @param cliente
	 * @return
	 */
	public Cliente atualiza(String cpf, Cliente cliente) {
		Optional<Cliente> existente = buscaClientePorCpf(cpf);
		
		if(existente.isEmpty()) {
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, String.format("CPF %s não encontrado", cpf));
		} 
		//TODO quando o cpf informado for diferente do que esta cadastrado faz a verificação de existência
		else if(!cliente.getCpf().equals(cpf)
				&& existente.isPresent()) {
			throw new ResponseStatusException(
					HttpStatus.CONFLICT, String.format("CPF %s já existe na base de dados.", cliente.getCpf()));
		}
		
		BeanUtils.copyProperties(cliente, existente, "id");
		return repository.save(existente.get());
	}
	
	/**
	 * find by cpf
	 * @param cpf
	 * @return
	 */
	public Optional<Cliente> buscaClientePorCpf(String cpf) {
		return repository.findByCpf(cpf);
	}
	
	/**
	 * find by name
	 * @param nome
	 * @return
	 */
	public List<Cliente> buscaClientePorNome(String nome) {
		List<Cliente> clientes = repository.findAllByNomeContainingIgnoreCaseOrderByNomeAsc(nome);
		if(clientes.isEmpty()) {
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, String.format("Nenhum resultado encontrado para %s", nome));
		}
		return clientes;
	}
	
	/**
	 * remove client
	 * @param cpf
	 * @return
	 */
	public Cliente remove(String cpf) {
		Optional<Cliente> cliente = this.buscaClientePorCpf(cpf);
		if(cliente.isEmpty()) {
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, String.format("CPF %s não encontrado", cpf));
		} 
		repository.delete(cliente.get());
		return cliente.get();
	}
}
