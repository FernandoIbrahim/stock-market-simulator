/**
 * Classe responsável por gerenciar as transações entre as ordens de compra e venda
 */

package classes;

import java.time.LocalDateTime;


import classes.*;
import classes.ordens.*;;


public class TransacaoManager {
    /**
     *   Metodo que cria uma transação caso seja possível
     * @param ordemCompra recebe uma ordem de compra
     * @param ordemVenda recebe uma ordem de venda
     * @param livro recebe um livro de ofertas
     * @return retorna uma transação
     */
    public static synchronized Transacao criarTransacao(OrdemConcrets ordemCompra, OrdemConcrets ordemVenda, LivroDeOfertas livro) {
        if (podeCriarTransacao(ordemCompra, ordemVenda)) {
            int quantidadeTransacao = Math.min(ordemCompra.getQuantidade(), ordemVenda.getQuantidade());
                
            double valorTransacao = ordemVenda.getValor();

            LocalDateTime dataHora = LocalDateTime.now();
            Acao acao = ordemCompra.getAcao();

            Transacao transacao =  new Transacao(ordemCompra, ordemVenda, dataHora, quantidadeTransacao, valorTransacao, acao);
            livro.addTransacao(transacao);
            atualizarOrdens(ordemCompra, ordemVenda, quantidadeTransacao);

            return transacao;
        }
        return null;
    }
    /**
     * Metodo que verifica se é possível criar uma transação
     * @param ordemCompra recebe uma ordem de compra
     * @param ordemVenda recebe uma ordem de venda
     * @return retorna um true se é possível criar uma transação false se não
     */
    private static synchronized boolean podeCriarTransacao(OrdemConcrets ordemCompra, OrdemConcrets ordemVenda) {
        return ordemCompra.getValor() >= ordemVenda.getValor();
    }

 
    /**
     * Metodo que atualiza as ordens de compra e venda
     * @param ordemCompra recebe uma ordem de compra
     * @param ordemVenda recebe uma ordem de venda 
     * @param quantidadeTransacao recebe uma quantidade de compra que foi feita de uma açao
     * @return atualiza as ordens de compra e venda 
     */
    private static synchronized void  atualizarOrdens(OrdemConcrets ordemCompra, OrdemConcrets ordemVenda, int quantidadeTransacao) {
        int comparacao = ordemCompra.compareTo(ordemVenda);

        if( comparacao > 0){
            ordemCompra.atualizar(ordemCompra.getQuantidade() - quantidadeTransacao);
            ordemVenda.alterarStatus();
        }
        
        else if(comparacao < 0){
            ordemVenda.atualizar(ordemVenda.getQuantidade() - quantidadeTransacao);
            ordemCompra.alterarStatus();
        }
        else{
            ordemCompra.alterarStatus();
            ordemVenda.alterarStatus(); 
        }

    }
    
}