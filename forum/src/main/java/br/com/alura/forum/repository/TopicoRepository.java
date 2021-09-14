package br.com.alura.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.forum.modelo.Topico;

/*
 * Quando você herda dessa interface, percebe que ela tem um generics que você tem que passar dois tipos. 
 * O primeiro é a entidade com que o JpaRepository vai trabalhar (no nosso caso é Topico). 
 * E o segundo é qual o tipo do atributo do ID, da chave primária dessa entidade. No nosso caso, estamos usando o Long
 */

public interface TopicoRepository extends JpaRepository<Topico, Long>{

}
