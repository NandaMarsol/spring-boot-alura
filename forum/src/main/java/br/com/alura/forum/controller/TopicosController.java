package br.com.alura.forum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.forum.controller.dto.TopicoDto;
import br.com.alura.forum.controller.request.TopicoRequest;
import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.repository.CursoRepository;
import br.com.alura.forum.repository.TopicoRepository;

@RestController // assume que todo método vai ter @ResponseBody
@RequestMapping("/topicos") // mapeamento de um endpoint
public class TopicosController {
	
	@Autowired // injeção de dependências
	private TopicoRepository topicoRepository;
	
	@Autowired
	private CursoRepository cursoRepository;
	
	@GetMapping
	public List<TopicoDto> lista(String nomeCurso) {
		
		if (nomeCurso == null) { // se estiver vindo o parâmetro nomeCurso não vou chamar o findAll
            List<Topico> topicos = topicoRepository.findAll(); // List<Topico> essa lista vem de topicos = topicoRepository. porque vou usar o Repository que foi injetado, seguindo, vou chamar o método findAll(), que é o método que faz uma consulta carregando todos os registros do banco de dados 
            return TopicoDto.converter(topicos); // na hora de chamar o TopicoDto.converter(), preciso passar uma lista chamada topicos
     } else {
    	 
    	 List<Topico> topicos = topicoRepository.findByCursoNome(nomeCurso); // faz a busca dos tópicos filtrando pelo nome do curso
         return TopicoDto.converter(topicos);
         
     	}

	}
	
	@PostMapping // a requisição é via método, então não mando os parâmetros via URL. os parâmetros vão no corpo da requisição.
	public void cadastrar(@RequestBody TopicoRequest topicoRequest) { // @RequestBody informa ao Spring que esse parâmetro é para pegar do corpo da requisição e não das URLs
		
		Topico topico = topicoRequest.converter(cursoRepository); // converte o TopicoRequest para um Topico
        topicoRepository.save(topico); // grava o objeto tópico no banco de dados
	}
}
