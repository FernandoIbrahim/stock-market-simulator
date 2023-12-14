
/**
 * Classe BolsaDeValores que representa uma única bolsa de valores
 */
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
    
    /**
     * Construtor da classe BolsaDeValores
     */
    private BolsaDeValores(){
        fabricas = new HashMap<>();
        fabricas.put(OrderType.COMPRA, new OrdemCompraFactory());
        fabricas.put(OrderType.VENDA, new OrdemVendaFactory());
        livrosDeOfertas  = new HashMap<>();
    }
        /**
         * Adiciona um broker a uma ação
         * @param sigla Recebe uma sigla
         * @param broker Recebe um broker
         */

    public void assinarAcao(String sigla, Broker broker){
        LivroDeOfertas livro = possuiLivroDeOfertas(sigla);
        livro.addObserver(broker);
    }
    /**
     *  Remove uma assinatura de uma ação
     * @param sigla Recebe uma sigla
     * @param broker Recebe um broker
     */
    public void removeAssinatura(String sigla, Broker broker){
        LivroDeOfertas livro = possuiLivroDeOfertas(sigla);
        livro.removeObserver(broker);
    }
    /**
     * 
     * @param acao Recebe uma ação
     */
    public void addAcao(Acao acao ){
        LivroDeOfertas livro = new LivroDeOfertas(acao);
        livrosDeOfertas.put(acao.getSigla(), livro);
    }
    /**
     * 
     * @return Retorna uma instância de BolsaDeValores, caso nao criada cria no próprio get
     */
    public static BolsaDeValores getInstance() {
        if (bolsaDeValores == null) {
            bolsaDeValores = new BolsaDeValores();
        }
        return bolsaDeValores;
    }
    /**
     * Pesquisa se o livro de ofertas já existe
     * @param sigla Recebe uma sigla
     * @return Retorna um livro de ofertas ou nulo
     */
    public LivroDeOfertas possuiLivroDeOfertas(String sigla) {
        LivroDeOfertas livroDeOfertas =  livrosDeOfertas.get(sigla);
        return livroDeOfertas;
    }
    /**
     * Pesquisa se a ação já existe no livro de ofertas
     * @param sigla Recebe uma sigla
     * @return Retorna uma ação ou nulo
     */
    public Acao possuiAcao(String sigla) {
        return livrosDeOfertas.get(sigla).getAcao();
    }

    /**
     * Adiciona uma operação de compra ou venda no livro de ofertas
     * @param operacao Recebe uma operação
     * @param broker Recebe um broker
     */

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
    /**
     * Pesquisa uma ordem de informação no livro de ofertas
     * @param operacao Recebe uma operação
     * @param broker Recebe um broker
     * @return Retorna um envio de uma OperacaoInfo
     */

    public synchronized  String pesquisarOrdemInfo(String operacao, Broker broker){
        String[] operacaoSlipt = operacao.split(";");
        Acao acao2 = possuiAcao(operacaoSlipt[1]);
        LocalDateTime data = LocalDateTime.parse(operacaoSlipt[2], dateFormatter);
        return sendOperacaoInfo(acao2, data, broker);
        
    }

    /**
     * Faz a construção das Operacao info via métodos factory, passando seus atributos por parâmetro.
        após isso enviar essa OperacaoInfo para pesquisa das ordens no determinado horario;
     * @param acao recebe uma ação
     * @param localDateTime recebe uma data e hora
     * @param broker recebe um broker 
     * @return  retorna uma spesquisa de uma ordem em um livro de ofertas
     */
    public synchronized String sendOperacaoInfo( Acao acao, LocalDateTime localDateTime, Broker broker ){
        OrdemInfo ordem = new OrdemInfo(acao, localDateTime, broker);
        LivroDeOfertas livro = possuiLivroDeOfertas(acao.getSigla());
        return livro.pesquisarOperacaoInfo(ordem);
    }


}
