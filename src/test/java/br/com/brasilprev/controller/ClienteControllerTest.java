package br.com.brasilprev.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.brasilprev.model.Cliente;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
public class ClienteControllerTest {
	
	@Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    
    @Test
    @Order(1)
    public void deveRetornarSucesso_QuandoCadastrarCliente() throws Exception {
        Cliente cliente = new Cliente(
        		100000L, 
        		"João das Couves deeeee Andrade Lima", 
        		"59442575040", 
        		"Rua X",
        		1000,
        		"Liberdade",
        		"69300000");
        
        mockMvc.perform(
        		post("/cliente")
        		.contentType(MediaType.APPLICATION_JSON)
        		.content(objectMapper.writeValueAsString(cliente)))
        .andExpect(status().isCreated());
    }
    
    @Test
    @Order(2)
	public void deveRetornarSucesso_QuandoPesquisarClientes() throws Exception {
		mockMvc.perform(
				get("/cliente")
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}
    
	@Test
	@Order(3)
	public void deveRetornarSucesso_QuandoPesquisarClientePeloCpf() throws Exception {
		mockMvc.perform(
				get("/cliente/{cpf}", "59442575040")
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}
	
	@Test
	@Order(4)
	public void deveRetornarNaoEncontrado_QuandoPesquisarClientePeloCpf() throws Exception {
		mockMvc.perform(
				get("/cliente/{cpf}", "99999999999")
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isNotFound());
	}
	
	@Test
	@Order(5)
	public void deveRetornarSucesso_QuandoPesquisarClientePeloNome() throws Exception {
		mockMvc.perform(
				get("/cliente/nome")
				.queryParam("value", "João das Couves")
				.accept(MediaType.APPLICATION_JSON))
		.andDo(print())
        .andExpect(status().isOk());
	}
	
	@Test
	@Order(6)
	public void deveRetornarNaoEncontrado_QuandoPesquisarClientePeloNome() throws Exception {
		mockMvc.perform(
				get("/cliente/nome")
				.queryParam("value", "XPTO^~>>")
				.accept(MediaType.APPLICATION_JSON))
		.andDo(print())
        .andExpect(status().isNotFound());
	}
	
	@Test
	@Order(7)
	public void deveRetornarSucesso_QuandoAtualizarCliente() throws Exception {
		Cliente clienteAtualizado = new Cliente(
        		100000L, 
        		"João das Couves Andrade Lima", 
        		"59442575040", 
        		"Rua X",
        		1000,
        		"Liberdade",
        		"69300000");
		
		mockMvc.perform(
        		put("/cliente/{cpf}", "59442575040")
        		.contentType(MediaType.APPLICATION_JSON)
        		.content(objectMapper.writeValueAsString(clienteAtualizado)))
        .andExpect(status().isOk());
	}
	
	@Test
	@Order(8)
	public void deveRetornarNaoEncontrado_QuandoAtualizarCliente() throws Exception {
		Cliente clienteAtualizado = new Cliente(
        		100000L, 
        		"João das Couves Andrade Lima", 
        		"59442575040", 
        		"Rua X",
        		1000,
        		"Liberdade",
        		"69300000");
		
		mockMvc.perform(
        		put("/cliente/{cpf}", "99999999999")
        		.contentType(MediaType.APPLICATION_JSON)
        		.content(objectMapper.writeValueAsString(clienteAtualizado)))
        .andExpect(status().isNotFound());
	}
	
	@Test
	@Order(9)
	public void deveRetornarSucesso_QuandoRemoverCliente() throws Exception {
		mockMvc.perform(
				delete("/cliente/{cpf}", "59442575040")
				.accept(MediaType.APPLICATION_JSON))
		.andDo(print())
        .andExpect(status().isOk());
	}
	
	@Test
	@Order(10)
	public void deveRetornarNaoEncontrado_QuandoRemoverCliente() throws Exception {
		mockMvc.perform(
				delete("/cliente/{cpf}", "59442575040")
				.accept(MediaType.APPLICATION_JSON))
		.andDo(print())
        .andExpect(status().isNotFound());
	}

}
