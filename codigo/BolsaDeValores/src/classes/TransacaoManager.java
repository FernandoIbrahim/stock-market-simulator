package classes;

import java.time.LocalDateTime;


import classes.*;
import classes.ordens.*;;


public class TransacaoManager {

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

    private static synchronized boolean podeCriarTransacao(OrdemConcrets ordemCompra, OrdemConcrets ordemVenda) {
        return ordemCompra.getValor() >= ordemVenda.getValor();
    }

 

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