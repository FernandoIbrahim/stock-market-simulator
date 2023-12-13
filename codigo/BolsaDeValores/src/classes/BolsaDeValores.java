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

    public void addAcao(Acao acao ){
        LivroDeOfertas livro = new LivroDeOfertas(acao);
        livrosDeOfertas.put(acao.getSigla(), livro);
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

    public Acao possuiAcao(String sigla) {
        return livrosDeOfertas.get(sigla).getAcao();
    }

    //! Finalizar ao Implementar os Factories para realizar a criação da Ordem

    public void addOperacaoCompraVenda(String operacao, Broker broker){

        String[] operacaoSlipt = operacao.split(";");
        String tipo = operacaoSlipt[0].toUpperCase(); 
        OrderType tipoOrder = OrderType.valueOf(tipo);
        Acao acao2 = possuiAcao(operacaoSlipt[1]); 
        int quantidade2 = Integer.parseInt(operacaoSlipt[2]);
        double preco2 = Double.parseDouble(operacaoSlipt[3]);

        OperacaoFactory  operacaoFactory;

        switch (tipoOrder) {
            case VENDA:
                captureOrdem(OrderType.valueOf(tipo), acao2, quantidade2, preco2, broker,  ordemVendaFactory);
                break;

            case COMPRA:
                captureOrdem(OrderType.valueOf(tipo), acao2, quantidade2, preco2, broker,  ordemCompraFactory);
                break;
        
            default:
                break;
        }

        /* 
        "INFO":
        Acao acao3 = possuiAcao(operacaoSlipt[1]); 
        LocalDateTime localDateTime = LocalDateTime.parse(operacaoSlipt[2], formatter);
        sendOperacaoInfo(tipo, acao3, localDateTime, ordemInfoFactory, broker);
        break;
        }
        */

    }


    /* Faz a construção das Ordem "COMPRA/VENDA" via métodos factory, passando seus atributos por parâmetro.
        após isso enviar essa ordem ao livroDeOfertas da determinada acao;
    */
    private void captureOrdem(OrderType tipo, Acao acao, int quantidade,double preco, Broker broker, OperacaoFactory operacaoFactory ){

        OrdemConcrets ordem = operacaoFactory.criarOrdem( acao, quantidade, preco, broker); //! precisamos de um factory que abstraia de ConcreteOrdem;
        
        LivroDeOfertas livro = possuiLivroDeOfertas(acao.getSigla());
        livro.addOrdem(ordem);
    }

    /* Faz a construção das Operacao info via métodos factory, passando seus atributos por parâmetro.
        após isso enviar essa OperacaoInfo para pesquisa das ordens no determinado horario;
    */
    public List<Ordem> sendOperacaoInfo(OrderType tipo, Acao acao, LocalDateTime localDateTime, Broker broker ){
        OrdemInfo ordem = new OrdemInfo(acao, localDateTime, broker);
        LivroDeOfertas livro = possuiLivroDeOfertas(acao.getSigla());
        return livro.pesquisarOperacaoInfo(OrdemInfo ordem);
    }


}
