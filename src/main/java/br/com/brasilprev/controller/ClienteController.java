package br.com.brasilprev.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.brasilprev.dto.ClienteDTO;
import br.com.brasilprev.model.Cliente;
import br.com.brasilprev.service.ClienteService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@PostMapping
	@ApiOperation(value = "Cadastra cliente")
	public ResponseEntity<?> cadastrar(@Valid @RequestBody Cliente cliente) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(ClienteDTO.transformaEmDTO(clienteService.adiciona(cliente)));
	}
	
	@PutMapping("/{cpf}")
	@ApiOperation(value = "Atualiza cliente")
	public ResponseEntity<?> atualizar(@PathVariable String cpf, @Valid @RequestBody Cliente cliente) {
		return ResponseEntity
				.ok(ClienteDTO.transformaEmDTO(clienteService.atualiza(cpf, cliente)));
	}
	
	@GetMapping
	@ApiOperation(value = "Lista todos")
	public ResponseEntity<?> listarTodos() {
		return ResponseEntity.ok(clienteService.listaTodos());
	}
	
	@GetMapping("/{cpf}")
	@ApiOperation(value = "Busca cliente por CPF")
	public ResponseEntity<?> buscarPorCpf(@PathVariable String cpf) {
		return ResponseEntity.ok(ClienteDTO.transformaEmDTO(clienteService.buscaClientePorCpf(cpf)
				.orElseThrow(() -> new ResponseStatusException(
						HttpStatus.NOT_FOUND, String.format("CPF %s não encontrado", cpf)))));
	}
	
	@GetMapping("/nome")
	@ApiOperation(value = "Busca clientes por nome")
	public ResponseEntity<?> buscarPorNome(@RequestParam String value) {
		List<Cliente> clientes = clienteService.buscaClientePorNome(value);

		List<ClienteDTO> clientesDTO = clientes.stream()
				.map((p) -> ClienteDTO.transformaEmDTO(p))
				.collect(Collectors.toList());
		return ResponseEntity.ok(clientesDTO);
	}
	
	@DeleteMapping("/{cpf}")
	@ApiOperation(value = "Remove cliente")
	public ResponseEntity<?> remover(@PathVariable String cpf) {
		return ResponseEntity.ok(clienteService.remove(cpf));
	}
	
}
