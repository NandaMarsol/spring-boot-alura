package br.com.alura.forum.controller.request;

import br.com.alura.forum.modelo.Curso;
import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.repository.CursoRepository;

public class TopicoRequest {
	
	private String titulo;
	private String mensagem;
	private String nomeCurso;
	
	
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public String getNomeCurso() {
		return nomeCurso;
	}
	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}


	// esse método devolve um objeto do tipo Topico
	public Topico converter(CursoRepository cursoRepository) { // quero carregar o Curso. Então o que vou receber como parâmetro vai ser um CursoRepository
		Curso curso = cursoRepository.findByNome(nomeCurso); // dado o curso, buscar pelo nome
		return new Topico(titulo, mensagem, curso);
	}
	
	
}
