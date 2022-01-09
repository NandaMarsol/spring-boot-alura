package br.com.alura.forum.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.forum.modelo.Topico;

/*
 * Quando você herda dessa interface, percebe que ela tem um generics que você tem que passar dois tipos. 
 * O primeiro é a entidade com que o JpaRepository vai trabalhar (no nosso caso é Topico). 
 * E o segundo é qual o tipo do atributo do ID, da chave primária dessa entidade. No nosso caso, estamos usando o Long
 */

public interface TopicoRepository extends JpaRepository<Topico, Long>{

	// o nome do método seguindo esse padrão de nomenclatura o Spring Data consegue gerar a Query automaticamente
	// o padrão de nomenclatura seria findBy() e o nome do atributo na entidade (do atributo que você quer filtrar)
	Page<Topico> findByCursoNome(String nomeCurso, Pageable paginacao); // Curso é a entidade de relacionamento e Nome é o atributo dentro dessa entidade de relacionamento

}
