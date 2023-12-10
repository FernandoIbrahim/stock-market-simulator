package classes;

import java.util.HashMap;
import java.util.Map;

import classes.ordens.Ordem;

public class BolsaDeValores {

    private static BolsaDeValores bolsaDeValores;

    private Map<String, LivroDeOfertas> livrosDeOfertas;

    private BolsaDeValores(){
        livrosDeOfertas  = new HashMap<>();
    }

    public void assinar(String sigla, Broker broker){
        LivroDeOfertas livro = possuiLivroDeOfertas(sigla);
        livro.addObserver(broker);
    }

    public static BolsaDeValores getInstance() {
        if (bolsaDeValores == null) {
            bolsaDeValores = new BolsaDeValores();
        }
        return bolsaDeValores;
    }

    public LivroDeOfertas possuiLivroDeOfertas(String sigla) {
        LivroDeOfertas livroDeOfertas =  livrosDeOfertas.get(sigla);
        if(livroDeOfertas == null){
            //throw new Exception()
        }
        return livroDeOfertas;
    }

    //! solicitar implementação getAcao()
    private Acao possuiAcao(String sigla){
        return livrosDeOfertas.get(sigla).getAcao();
    }

    //! Finalizar ao Implementar os Factories para realizar a criação da Ordem
    public void addOperacao(String operacao){

        String[] operacaoSlipt = operacao.split(";");

        LivroDeOfertas livro = possuiLivroDeOfertas(operacaoSlipt[1]);

        String tipo = operacaoSlipt[0].toUpperCase(); 
        Acao acao = possuiAcao(operacaoSlipt[1]); 
        int quantidade = Integer.parseInt(operacaoSlipt[2]);
        double preco = Double.parseDouble(operacaoSlipt[3]);

        Ordem ordem;
        
        switch (tipo) {
            case "COMPRA":
                
                break;

            case "VENDA":
                
                break;    
        
            default:
                break;
        }

        livro.addOrdem(ordem);
    }





}
