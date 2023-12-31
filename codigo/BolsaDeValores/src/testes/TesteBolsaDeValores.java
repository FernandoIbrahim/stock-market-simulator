package testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import classes.*;
import classes.ordens.*;

public class TesteBolsaDeValores {

    BolsaDeValores bolsa;
    Acao acao1;
    Acao acao2;
    Acao acao3;
    Acao acao4;
    Acao acao5;
    Acao acao6;
    Broker broker;
    LivroDeOfertas  livroDeOfertas;

    @BeforeEach
    void criarClasses(){
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
        bolsa.addOperacaoCompraVenda("COMPRA;AAPL;100;150.50;BKR", broker);
        bolsa.addOperacaoCompraVenda("VENDA;GOOGL;50;2500.75;BKR", broker);
        bolsa.addOperacaoCompraVenda("COMPRA;MSFT;75;300.25;BKR", broker);
        bolsa.addOperacaoCompraVenda("VENDA;AMZN;30;3500.00;BKR", broker);
        bolsa.addOperacaoCompraVenda("COMPRA;TSLA;20;700.50;BKR", broker);
    }

    @Test
    void assinarBrokerParaAcao_DeveAdicionarBrokerComoObserver() {
        bolsa.addAcao(acao1);
        bolsa.assinarAcao("AAPL", broker);  // Use the correct ticker symbol
        LivroDeOfertas livro = bolsa.possuiLivroDeOfertas("AAPL");

        // Assert
        assertTrue(livro.temObserver(broker));
    }
    
    @Test
    void criarOrdemCompra_DeveAdicionarOrdemAoLivroDeOfertasCompra() {
        bolsa.addOperacaoCompraVenda("COMPRA;PETR4;100;26.46;BKR", broker);
        LivroDeOfertas livroCompra = bolsa.possuiLivroDeOfertas("PETR4");
        assertEquals(1, livroCompra.getOrdens().size());
    }

    

    @Test
    void sendOperacaoInfoDeveRetornarListaDeOrdensNoHorarioEspecifico() {
        bolsa.addOperacaoCompraVenda("COMPRA;PETR4;100;26.46;BKR", broker);
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH");
        String nowString = now.format(dateFormatter);
        String result = broker.pesquisarOrdemInfo("INFO;PETR4;"+nowString);
        System.out.println(result);
        assertNotNull(result);
    }

    
    
}
