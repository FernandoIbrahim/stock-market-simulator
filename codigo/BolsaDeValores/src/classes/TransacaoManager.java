package classes;

import java.time.LocalDateTime;


import classes.*;
import classes.ordens.*;;


public class TransacaoManager {

    public static boolean criarTransacao(OrdemConcrets ordemCompra, OrdemConcrets ordemVenda, LivroDeOfertas livro) {
        if (podeCriarTransacao(ordemCompra, ordemVenda)) {
            int quantidadeTransacao = Math.min(ordemCompra.getQuantidade(), ordemVenda.getQuantidade());
            double valorTransacao = ordemVenda.getValor();

            LocalDateTime dataHora = LocalDateTime.now();
            Acao acao = ordemCompra.getAcao();

            livro.addTransacao(criarTransacao(ordemCompra,ordemVenda, dataHora, quantidadeTransacao, valorTransacao, acao));
            atualizarOrdens(ordemCompra, ordemVenda, quantidadeTransacao);

            return true;
        }
        return false;
    }

    private static boolean podeCriarTransacao(OrdemConcrets ordemCompra, OrdemConcrets ordemVenda) {
        return ordemCompra.getValor() >= ordemVenda.getValor();
    }

    private static Transacao criarTransacao(OrdemConcrets ordemCompra, OrdemConcrets ordemVenda, LocalDateTime dataHora, int quantidade, double valor, Acao acao) {
        return new Transacao(ordemCompra, ordemVenda, dataHora, quantidade, valor, acao);
        // Add logic to handle the created transaction as needed
    }

    private static void atualizarOrdens(OrdemConcrets ordemCompra, OrdemConcrets ordemVenda, int quantidadeTransacao) {
        if (ordemCompra.getQuantidade() > quantidadeTransacao) {
            ordemCompra.atualizar(ordemCompra.getQuantidade() - quantidadeTransacao);
        } else {
            ordemCompra.alterarStatus();
        }

        if (ordemVenda.getQuantidade() > quantidadeTransacao) {
            ordemVenda.atualizar(ordemVenda.getQuantidade() - quantidadeTransacao);
        } else {
            ordemVenda.alterarStatus();
        }
    }
    
}