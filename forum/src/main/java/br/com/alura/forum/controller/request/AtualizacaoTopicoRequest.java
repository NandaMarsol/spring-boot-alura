package br.com.alura.forum.controller.request;

import com.sun.istack.NotNull;

import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.repository.TopicoRepository;

public class AtualizacaoTopicoRequest {
	
	@NotNull
	private String titulo;
	
	@NotNull
	private String mensagem;

	// método para buscar o Topico pelo id no banco de dados e atualizar os campos que foram modificados
	public Topico atualizar(Long id, TopicoRepository topicoRepository) {
		Topico topico = topicoRepository.getById(id); // pegando o tópico que veio do banco de dados com os campos desatualizados
		
		// sobreescrevendo pelo que veio no JSON
		topico.setTitulo(this.titulo);
		topico.setMensagem(this.mensagem);
		
		return topico;
	}

}
