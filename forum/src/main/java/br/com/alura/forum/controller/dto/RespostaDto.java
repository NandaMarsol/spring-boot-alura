package br.com.alura.forum.controller.dto;

import java.time.LocalDateTime;

import br.com.alura.forum.modelo.Resposta;

public class RespostaDto {
	
	private Long id;
    private String mensagem;
    private LocalDateTime dataCriacao;
    private String nomeAutor;
    
    public RespostaDto(Resposta resposta) {
        this.id = resposta.getId();
        this.mensagem = resposta.getMensagem();
        this.dataCriacao = resposta.getDataCriacao();
        this.nomeAutor = resposta.getAutor().getNome();
    }

}

/*
 * o construtor recebe a Resposta, o objeto resposta, e a partir dele que pegamos os parâmetros this.id = resposta.getId(). 
 * mesma coisa para os outros campos: this.mensagem = resposta.getMensagem(); this.dataCriacao = resposta.getDataCriacao(); 
 * sobre o nome do autor, this.nomeAutor = resposta.getAutor().getNome(), não existe um campo "nomeAutor", mas tem o relacionamento para autor e pelo autor eu consigo chegar no nome do autor.
 *
 */

