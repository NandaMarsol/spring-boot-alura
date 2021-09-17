package br.com.alura.forum.config.validacao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/*
 * Toda vez que acontecer uma exception, em qualquer método de qualquer Controller, o Spring automaticamente vai chamar esse interceptador, e nós faremos o tratamento apropriado. 
 * Esse interceptador é chamado de Controller Advice. A ideia é criarmos uma classe e transformá-la em um Controller Advice, onde vamos fazer o tratamento do erro
 * Toda vez que acontecer alguma exception desse tipo em qualquer RestController do projeto, o Spring vai chamar esse método handle() passando como parâmetro a exception que aconteceu.
 */

@RestControllerAdvice // dizendo para o Spring que a classe ErroDeValidacaoHandler é um Controller Advice
public class ErroDeValidacaoHandler { // essa classe que vai fazer o Handler, o tratamento dos erros de validação

	@Autowired
    private MessageSource messageSource;
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST) // devolvendo erro 400
	@ExceptionHandler(MethodArgumentNotValidException.class) // diz para o Spring que esse método deve ser chamado quando houver uma exceção dentro de algum Controller
						// passar como parâmetro que tipo de exceção que, quando acontecer dentro do Controller, o Spring vai direcionar para o método handler()
    
	public List<ErroDeFormularioDto> handle(MethodArgumentNotValidException exception) { // recebe como parâmetro um objeto do tipo MethodArgumentNotValidException, que é o mesmo Handler que estou utilizando no @ExceptionHandler()
																						// devolve uma lista com as mensagens de erro
		
		List<ErroDeFormularioDto> dto = new ArrayList<>(); // quero devolver uma lista de ErroDeFormulárioDto
		
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
	    fieldErrors.forEach(e -> { // usando lambda, para cada erro ->
	            String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
	        ErroDeFormularioDto erro = new ErroDeFormularioDto(e.getField(), mensagem); 
	                dto.add(erro);
	     });

	     return dto;
	  }
		
    }
	

