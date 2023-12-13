package classes;

import java.time.LocalDateTime;

import classes.ordens.OrdemCompra;
import classes.ordens.OrdemVenda;


public class TransacaoManager {

    public static boolean criarTransacao(OrdemCompra ordemCompra, OrdemVenda ordemVenda) {
        if (podeCriarTransacao(ordemCompra, ordemVenda)) {
            int quantidadeTransacao = Math.min(ordemCompra.getQuantidade(), ordemVenda.getQuantidade());
            double valorTransacao = ordemVenda.getValor();

            LocalDateTime dataHora = LocalDateTime.now();
            Acao acao = ordemCompra.getAcao();

            criarTransacao(ordemCompra,ordemVenda, dataHora, quantidadeTransacao, valorTransacao, acao);
            atualizarOrdens(ordemCompra, ordemVenda, quantidadeTransacao);

            return true;
        }
        return false;
    }

    private static boolean podeCriarTransacao(OrdemCompra ordemCompra, OrdemVenda ordemVenda) {
        return ordemCompra.getValor() >= ordemVenda.getValor();
    }

    private static void criarTransacao(OrdemCompra ordemCompra, OrdemVenda ordemVenda, LocalDateTime dataHora, int quantidade, double valor, Acao acao) {
        Transacao transacao = new Transacao(ordemCompra, ordemVenda, dataHora, quantidade, valor, acao);
        // Add logic to handle the created transaction as needed
    }

    private static void atualizarOrdens(OrdemCompra ordemCompra, OrdemVenda ordemVenda, int quantidadeTransacao) {
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