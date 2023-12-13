package classes;

import java.time.LocalDateTime;

import classes.ordens.Ordem;
import classes.ordens.OrdemConcrets;

public class Transacao {
    private OrdemConcrets ordemCompra ;
    private OrdemConcrets ordemVenda ;
    private LocalDateTime dataHora;
    private int quantidade;
    private double valor;
    private Acao acao;

    public Transacao(OrdemConcrets ordemCompra, OrdemConcrets ordemVenda, LocalDateTime datahora, int quantidade, double valor , Acao acao){

        this.ordemCompra = ordemCompra;
        this.ordemVenda = ordemVenda;
        this.dataHora = datahora;
        this.quantidade = quantidade;
        this.valor = valor;
        this.acao = acao;
    }


}
