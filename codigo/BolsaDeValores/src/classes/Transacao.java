package classes;

import java.time.LocalDateTime;

import classes.ordens.Ordem;

public class Transacao {
    private Ordem ordem ;
    private LocalDateTime dataHora;
    private int quantidade;
    private double valor;
    private Acao acao;

    public Transacao(Ordem ordem, LocalDateTime datahora, int quantidade, double valor , Acao acao){

        this.ordem = ordem;
        this.dataHora = datahora;
        this.quantidade = quantidade;
        this.valor = valor;
        this.acao = acao;
    }


}
