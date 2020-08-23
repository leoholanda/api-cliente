package br.com.brasilprev.dto;

import org.springframework.beans.BeanUtils;

import br.com.brasilprev.model.Cliente;
import lombok.Data;

@Data
public class ClienteDTO {
	
	private String nome;
	private String cpf;
	private String logradouro;
	private Integer enderecoNumero;
	private String bairro;
	private String cep;

	/**
     * Converte entidade em DTO
     * @param entity
     * @return
     */
    public static ClienteDTO transformaEmDTO(Cliente entity) {
    	ClienteDTO clienteDTO = new ClienteDTO();
        BeanUtils.copyProperties(entity, clienteDTO);
        
        return clienteDTO;
    }
}
