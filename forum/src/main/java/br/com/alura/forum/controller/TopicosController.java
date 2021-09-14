package br.com.alura.forum.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.forum.controller.dto.TopicoDto;
import br.com.alura.forum.modelo.Curso;
import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.repository.TopicoRepository;

@RestController
public class TopicosController {
	
	@Autowired // injeção de dependências
	private TopicoRepository topicoRepository;
	
	@RequestMapping("/topicos")
	public List<TopicoDto> lista() {
		
		/*
		 * List<Topico> essa lista vem de topicos = topicoRepository. porque vou usar o Repository que foi injetado. 
		 * seguindo, vou chamar o método findAll(), que é o método que faz uma consulta carregando todos os registros do banco de dados 
		 */
		List<Topico> topicos = topicoRepository.findAll();
		return TopicoDto.converter(topicos); // na hora de chamar o TopicoDto.converter(), preciso passar uma lista chamada topicos
	}

}
