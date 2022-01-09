package br.com.alura.forum.controller.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import br.com.alura.forum.modelo.Topico;

public class TopicoDto {
	
	private Long id;
	private String titulo;
	private String mensagem;
	private LocalDateTime dataCriacao;
	
	
	// construtor, toda vez que der new no topicoDto, passo como parâmetro um objeto do tipo topico
	public TopicoDto(Topico topico) {
		this.id = topico.getId();
		this.titulo = topico.getTitulo();
		this.mensagem = topico.getMensagem();
		this.dataCriacao = topico.getDataCriacao();
	}
	
	
	public Long getId() {
		return id;
	}
	public String getTitulo() {
		return titulo;
	}
	public String getMensagem() {
		return mensagem;
	}
	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	// método para fazer a conversão de <Topico> para <TopicoDto>
	// encapsula dentro da própria classe DTO
	// esse método recebe a lista de topicos e precisa devolver uma lista de topicoDto, isto é, precisa fazer a conversão
	// a função do mapeamento será TopicoDto::new, pq ele vai chamar o construtor que recebe o próprio tópico como parâmetro. no final, tem que transformar isso em uma lista, então vamos encadear a chamada para o método collect(), passando collectors.toList() para transformar numa lista
	public static Page<TopicoDto> converter(Page<Topico> topicos) {
		return topicos.map(TopicoDto::new);
	}
	

}
