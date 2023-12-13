package testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.List;
import classes.*;

public class TesteBolsaDeValores {

    BolsaDeValores bolsa;
    Acao acao1;
    Acao acao2;
    Acao acao3;
    Acao acao4;
    Acao acao5;
    Broker broker;

    @BeforeEach
    void criarClasses(){
        bolsa = BolsaDeValores.getInstance();
        acao1 = new Acao("AAPL", "Apple Inc.", "Empresa de tecnologia");
        acao2 = new Acao("GOOGL", "Alphabet Inc.", "Empresa-mãe do Google");
        acao3 = new Acao("MSFT", "Microsoft Corporation", "Empresa de tecnologia");
        acao4 = new Acao("AMZN", "Amazon.com Inc.", "Empresa de comércio eletrônico");
        acao5 = new Acao("TSLA", "Tesla Inc.", "Fabricante de veículos elétricos");
        broker = new Broker(bolsa);
    }

    @Test
    void assinarBrokerParaAcao_DeveAdicionarBrokerComoObserver() {
        bolsa.addAcao(acao1);
        bolsa.assinar("AAPL", broker);  // Use the correct ticker symbol
        LivroDeOfertas livro = bolsa.possuiLivroDeOfertas("AAPL");

        // Assert
        assertTrue(livro.temObserver(broker));
    }
    /* 
    @Test
    void criarOrdemCompra_DeveAdicionarOrdemAoLivroDeOfertasCompra() {
        // Arrange
        BolsaDeValores bolsa = BolsaDeValores.getInstance();
        Acao acao = new Acao("PETR4", "Petrobras", "Descrição");
        Broker broker = new Broker(bolsa);
        bolsa.assinar("PETR4", broker);

        // Act
        bolsa.addOperacaoCompraVenda("COMPRA;PETR4;100;26.46;BKR", broker);
        LivroDeOfertas livroCompra = bolsa.possuiLivroDeOfertas("PETR4");

        // Assert
        assertEquals(1, livroCompra.getOrdensCompra().size());
    }

    @Test
    void criarOrdemVenda_DeveAdicionarOrdemAoLivroDeOfertasVenda() {
        // Arrange
        BolsaDeValores bolsa = BolsaDeValores.getInstance();
        Acao acao = new Acao("PETR4", "Petrobras", "Descrição");
        Broker broker = new Broker(bolsa);
        bolsa.assinar("PETR4", broker);

        // Act
        bolsa.addOperacaoCompraVenda("VENDA;PETR4;100;26.46;BKR", broker);
        LivroDeOfertas livroVenda = bolsa.possuiLivroDeOfertas("PETR4");

        // Assert
        assertEquals(1, livroVenda.getOrdensVenda().size());
    }

    @Test
    void sendOperacaoInfo_DeveRetornarListaDeOrdensNoHorarioEspecifico() {
        // Arrange
        BolsaDeValores bolsa = BolsaDeValores.getInstance();
        Acao acao = new Acao("PETR4", "Petrobras", "Descrição");
        Broker broker = new Broker(bolsa);
        bolsa.assinar("PETR4", broker);

        // Act
        LocalDateTime localDateTime = LocalDateTime.now();
        List<Ordem> ordens = bolsa.sendOperacaoInfo(OrderType.INFO, acao, localDateTime, broker);

        // Assert
        assertNotNull(ordens);
    }
    */
}
