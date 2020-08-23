package br.com.brasilprev.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class SwaggerController {

	@GetMapping
	public void documentacao(HttpServletResponse httpServletResponse) throws IOException {
		httpServletResponse.sendRedirect("swagger-ui.html#");
	}
	
	@GetMapping("/swagger")
	public void documentacaoSwagger(HttpServletResponse httpServletResponse) throws IOException {
		httpServletResponse.sendRedirect("swagger-ui.html#");
	}
	
}
