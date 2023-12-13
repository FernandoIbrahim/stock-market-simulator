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
    
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH");
    Map<OrderType, OperacaoFactory> fabricas;

    private static BolsaDeValores bolsaDeValores;

    
    private Map<String, LivroDeOfertas> livrosDeOfertas;

    private BolsaDeValores(){
        fabricas = new HashMap<>();
        fabricas.put(OrderType.COMPRA, new OrdemCompraFactory());
        fabricas.put(OrderType.VENDA, new OrdemVendaFactory());
        livrosDeOfertas  = new HashMap<>();
    }

    public void assinarAcao(String sigla, Broker broker){
        LivroDeOfertas livro = possuiLivroDeOfertas(sigla);
        livro.addObserver(broker);
    }

    public void removeAssinatura(String sigla, Broker broker){
        LivroDeOfertas livro = possuiLivroDeOfertas(sigla);
        livro.removeObserver(broker);
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
        return livroDeOfertas;
    }

    public Acao possuiAcao(String sigla) {
        return livrosDeOfertas.get(sigla).getAcao();
    }

    //! Finalizar ao Implementar os Factories para realizar a criação da Ordem

    public synchronized void addOperacaoCompraVenda(String operacao, Broker broker){

        String[] operacaoSlipt = operacao.split(";");
        String tipo = operacaoSlipt[0].toUpperCase(); 
        OrderType tipoOrder = OrderType.valueOf(tipo);
        Acao acao2 = possuiAcao(operacaoSlipt[1]); 
        int quantidade2 = Integer.parseInt(operacaoSlipt[2]);
        double preco2 = Double.parseDouble(operacaoSlipt[3]);

        OrdemConcrets ordem = fabricas.get(tipoOrder).criarOrdem(acao2, quantidade2, preco2, broker);

        LivroDeOfertas livro = possuiLivroDeOfertas(acao2.getSigla());
        livro.addOrdem(ordem);
    }   


    public synchronized  String pesquisarOrdemInfo(String operacao, Broker broker){
        String[] operacaoSlipt = operacao.split(";");
        Acao acao2 = possuiAcao(operacaoSlipt[1]);
        LocalDateTime data = LocalDateTime.parse(operacaoSlipt[2], dateFormatter);
        return sendOperacaoInfo(acao2, data, broker);
        
    }

    /* Faz a construção das Operacao info via métodos factory, passando seus atributos por parâmetro.
        após isso enviar essa OperacaoInfo para pesquisa das ordens no determinado horario;
    */
    public synchronized String sendOperacaoInfo( Acao acao, LocalDateTime localDateTime, Broker broker ){
        OrdemInfo ordem = new OrdemInfo(acao, localDateTime, broker);
        LivroDeOfertas livro = possuiLivroDeOfertas(acao.getSigla());
        return livro.pesquisarOperacaoInfo(ordem);
    }


}
