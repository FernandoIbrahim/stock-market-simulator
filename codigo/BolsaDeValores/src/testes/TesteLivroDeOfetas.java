package testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import BolsaDeValores.src.classes.ordens.Ordem;
import BolsaDeValores.src.classes.ordens.OrderType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import classes.*;
import classes.ordens.*;

public class TesteLivroDeOfetas {

    BolsaDeValores bolsa;
    Acao acao1;
    Acao acao2;
    Acao acao3;
    Acao acao4;
    Acao acao5;
    Acao acao6;
    Broker broker;
    Broker broker1;
    Broker broker2;
    LivroDeOfertas  livroDeOfertas;

    @BeforeEach
    public void setUp() {
        bolsa = BolsaDeValores.getInstance();
        acao1 = new Acao("AAPL", "Apple Inc.", "Empresa de tecnologia");
        acao2 = new Acao("GOOGL", "Alphabet Inc.", "Empresa-mãe do Google");
        acao3 = new Acao("MSFT", "Microsoft Corporation", "Empresa de tecnologia");
        acao4 = new Acao("AMZN", "Amazon.com Inc.", "Empresa de comércio eletrônico");
        acao5 = new Acao("TSLA", "Tesla Inc.", "Fabricante de veículos elétricos");
        acao6 = new Acao("PETR4", "Petra", "Empresa fabricante de carveja");
        bolsa.addAcao(acao1);
        bolsa.addAcao(acao2);
        bolsa.addAcao(acao3);
        bolsa.addAcao(acao4);
        bolsa.addAcao(acao5);
        bolsa.addAcao(acao6);
        broker = new Broker(bolsa.getInstance());
        broker1 = new Broker(bolsa.getInstance());
        broker2 = new Broker(bolsa.getInstance());
        bolsa.addOperacaoCompraVenda("COMPRA;AAPL;100;1000.50;BKR", broker2);
        bolsa.addOperacaoCompraVenda("VENDA;AAPL;15;700.75;BKR", broker1);
        bolsa.addOperacaoCompraVenda("VENDA;AAPL;15;800.25;BKR", broker);
        bolsa.addOperacaoCompraVenda("VENDA;AAPL;30;300.00;BKR", broker1);
        bolsa.addOperacaoCompraVenda("VENDA;AAPL;20;700.50;BKR", broker);
        bolsa.addOperacaoCompraVenda("VENDA;AAPL;20;700.50;BKR", broker);
        bolsa.addOperacaoCompraVenda("VENDA;AAPL;20;1100.50;BKR", broker);
        bolsa.addOperacaoCompraVenda("COMPRA;AAPL;20;1200.50;BKR", broker);
    }

    @Test
    public void testVerificaOrdens() {
        LivroDeOfertas livroAAPL = bolsa.possuiLivroDeOfertas("AAPL");
        assertEquals(6, livroAAPL.verficarOrdens());
    }





}
