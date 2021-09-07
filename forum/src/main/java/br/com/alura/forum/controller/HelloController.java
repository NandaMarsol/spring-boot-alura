package br.com.alura.forum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
	
	@ResponseBody // devolve a string direto para o navegador ao invés de considerar que o retorno (a String) é uma página 
	@RequestMapping("/") // anotação para dizer qual é a URL quando o Spring chamar esse método
	public String hello () { // método que retorna uma String
		return "Testando o Hello Controller :)";
	}

}
