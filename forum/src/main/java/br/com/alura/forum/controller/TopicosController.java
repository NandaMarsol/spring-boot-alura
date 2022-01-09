package br.com.alura.forum.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.forum.controller.dto.DetalhesTopicoDto;
import br.com.alura.forum.controller.dto.TopicoDto;
import br.com.alura.forum.controller.request.AtualizacaoTopicoRequest;
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
	public Page<TopicoDto> lista(@RequestParam(required = false) String nomeCurso, @RequestParam int pagina, @RequestParam int quantidade, @RequestParam String ordenacao) {
		
		Pageable paginacao = PageRequest.of(pagina, quantidade, Direction.ASC, ordenacao);
		
		if (nomeCurso == null) { // se estiver vindo o parâmetro nomeCurso não vou chamar o findAll
            Page<Topico> topicos = topicoRepository.findAll(paginacao); // List<Topico> essa lista vem de topicos = topicoRepository. porque vou usar o Repository que foi injetado, seguindo, vou chamar o método findAll(), que é o método que faz uma consulta carregando todos os registros do banco de dados 
            return TopicoDto.converter(topicos); // na hora de chamar o TopicoDto.converter(), preciso passar uma lista chamada topicos
     } else {
    	 
    	 Page<Topico> topicos = topicoRepository.findByCursoNome(nomeCurso, paginacao); // faz a busca dos tópicos filtrando pelo nome do curso
         return TopicoDto.converter(topicos);
         
     	}

	}
	
	@PostMapping // a requisição é via método, então não mando os parâmetros via URL. os parâmetros vão no corpo da requisição.
	public ResponseEntity<TopicoDto> cadastrar(@RequestBody TopicoRequest topicoRequest, UriComponentsBuilder uriBuilder) { // ResponseEntity retorna o código 201, informando a criação com sucesso de um novo recurso. é só declarar o UriComponentsBuilder como parâmetro que o Spring vai injetar no método 
		
		Topico topico = topicoRequest.converter(cursoRepository); // converte o TopicoRequest para um Topico
        topicoRepository.save(topico); // grava o objeto tópico no banco de dados
        
        URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri(); // passa o caminho do recurso dentro do método path, .buildAndExpand(), método que temos que chamar, passando como parâmetro um valor a ser substituído no espaço do {id}, que é dinâmico. 
        																				   // no caso, vou puxar o id do tópico que acabei de criar no banco de dados e ele vai substituir esse {id} e jogar na uri
        																				   // no final, tem um método .toUri(), que converte e transforma na URL completa, com endereço do servidor e com valores dinâmicos que posso passar como parâmetro no buildAndExpand
		return ResponseEntity.created(uri).body(new TopicoDto(topico)); // método created() recebe um parâmetro uri, Na sequência, .body(body), porque tenho que passar o corpo - com o 201, além de devolver a uri, preciso devolver um "body", corpo, na resposta. 
																		// a ideia é criarmos um DTO, então, (new TopicoDto(topico). lembrando que quando dou um new no TopicoDto() posso passar o topico como parâmetro. Dentro dele tem todas as informações que o DTO precisa
	}
	
	@GetMapping("/{id}") // 
	public ResponseEntity<DetalhesTopicoDto> detalhar(@PathVariable Long id) { // anotação, @PathVariable: o parâmetro Long id não virá numa interrogação e sim no barra ("/"), na URL. 
		Optional<Topico> topico = topicoRepository.findById(id); //optional: pode ser que haja um registro ou não
		
		if (topico.isPresent()) {
			return ResponseEntity.ok(new DetalhesTopicoDto(topico.get())); //se existir, ok
		} 
		return ResponseEntity.notFound().build(); //se não existir, 404
		
	}
	
	@PutMapping("/{id}")
	@Transactional // avisa para o Spring que é para commitar a transação no final do método
	public ResponseEntity<TopicoDto> atualizar(@PathVariable Long id, @RequestBody AtualizacaoTopicoRequest atualizacaoTopicoRequest) {
		Optional<Topico> optional = topicoRepository.findById(id); 
		
		if(optional.isPresent()) {
			Topico topico = atualizacaoTopicoRequest.atualizar(id, topicoRepository);
			return ResponseEntity.ok(new TopicoDto(topico)); //se existir, status 200 ok
		}
		
		return ResponseEntity.notFound().build(); //se não existir, erro 404
		
	}
	
	@DeleteMapping
	public ResponseEntity<TopicoDto> remover(@PathVariable Long id) {
		Optional<Topico> optional = topicoRepository.findById(id);
		
		if(optional.isPresent()) {
			topicoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build(); 	
	}
	
}

/*
 * toda vez que devolvo 201 para o cliente, além de devolver o código, tenho que devolver mais duas coisas: uma delas é um cabeçalho HTTP, chamado "Location", com a URL desse novo recurso que acabou de ser criado; 
 * a segunda coisa é que, no corpo da resposta, eu tenho que devolver uma representação desse recurso que acabei de criar. 
 * então, quando eu chamo o método created(), ele fica esperando a uri do recurso que criamos para adicioná-la no cabeçalho Location
 */
