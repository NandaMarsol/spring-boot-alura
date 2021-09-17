package br.com.alura.forum.config.validacao;

/*
 * Essa classe ErroDeFormularioDto que vai representar um erro de validação. 
 * O JSON, que vai ser devolvido para o cliente, não vai ser mais aquele imenso do Spring. 
 * Vai ser o JSON representado por essa classe
 * 
 */

public class ErroDeFormularioDto {
	
	public String campo;
    private String erro;
    
    // declarando um construtor
    public ErroDeFormularioDto(String campo, String erro) {
        this.campo = campo;
            this.erro = erro;
  }

    public String getCampo() {
        return campo;
    }

    public String getErro() {
        return erro;
    }

}
