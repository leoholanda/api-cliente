package br.com.brasilprev.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
@Entity
public class Cliente implements Serializable {

	private static final long serialVersionUID = -4279911276057970046L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String nome;
	
	@CPF
	@NotBlank
	private String cpf;
	
	@NotBlank
	private String logradouro;
	
	@NotNull
	private Integer enderecoNumero;
	
	@NotBlank
	private String bairro;
	
	@NotBlank
	private String cep;

}
