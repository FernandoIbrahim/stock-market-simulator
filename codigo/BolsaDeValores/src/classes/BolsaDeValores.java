package classes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import classes.ordens.*;
import classes.*;

public class BolsaDeValores {

    OrdemCompraFactory ordemCompraFactory;
    OrdemVendaFactory ordemVendaFactory;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");


    private static BolsaDeValores bolsaDeValores;

    private Map<String, LivroDeOfertas> livrosDeOfertas;

    private BolsaDeValores(){
        livrosDeOfertas  = new HashMap<>();
        ordemCompraFactory = new OrdemCompraFactory();
        ordemVendaFactory = new OrdemVendaFactory();
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

    public void addOperacao(String operacao, Broker broker){

        String[] operacaoSlipt = operacao.split(";");
        String tipo = operacaoSlipt[0].toUpperCase(); 

        switch (tipo) {
            case "COMPRA":
                Acao acao = possuiAcao(operacaoSlipt[1]); 
                int quantidade = Integer.parseInt(operacaoSlipt[2]);
                double preco = Double.parseDouble(operacaoSlipt[3]);
                captureOrdem(tipo, acao, quantidade, preco, broker, ordemCompraFactory);
                break;
            case "VENDA":

                Acao acao2 = possuiAcao(operacaoSlipt[1]); 
                int quantidade2 = Integer.parseInt(operacaoSlipt[2]);
                double preco2 = Double.parseDouble(operacaoSlipt[3]);
                captureOrdem(tipo, acao2, quantidade2, preco2, broker,  ordemVendaFactory);
                break;

            case "INFO":
                Acao acao3 = possuiAcao(operacaoSlipt[1]); 
                LocalDateTime localDateTime = LocalDateTime.parse(operacaoSlipt[2], formatter);
                sendOperacaoInfo(tipo, acao3, localDateTime, ordemInfoFactory, broker);
                break;
        }

    }


    /* Faz a construção das Ordem "COMPRA/VENDA" via métodos factory, passando seus atributos por parâmetro.
        após isso enviar essa ordem ao livroDeOfertas da determinada acao;
    */
    private void captureOrdem(String tipo, Acao acao, int quantidade,double preco, Broker broker, OperacaoFactory operacaoFactory ){

        Ordem ordem = operacaoFactory.criarOrdem(tipo, acao, quantidade, preco, broker);
        
        LivroDeOfertas livro = possuiLivroDeOfertas(acao.getSigla());
        livro.addOrdem(ordem);
    }

    /* Faz a construção das Operacao info via métodos factory, passando seus atributos por parâmetro.
        após isso enviar essa OperacaoInfo para pesquisa das ordens no determinado horario;
    */
    public List<Ordem> sendOperacaoInfo(String tipo, Acao acao, LocalDateTime localDateTime, Broker broker, OperacaoFactory operacaoFactory ){
        
        OrdemInfo ordem = operacaoFactory.criarOrdem(tipo, acao, localDateTime, broker);
        
        LivroDeOfertas livro = possuiLivroDeOfertas(acao.getSigla());
        return livro.pesquisarOperacaoInfo(ordem);
    }


}
