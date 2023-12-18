
import static org.junit.jupiter.api.DynamicTest.stream;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import classes.*;
import classes.ordens.*;

public class App {


    public static void main(String[] args) throws InterruptedException {

        Locale.setDefault(Locale.US);
        BolsaDeValores bolsa = BolsaDeValores.getInstance();


        Acao acao1 = new Acao("B3SA3", "B3 S.A. - Brasil, Bolsa, Balcão", "Bolsa de valores");
        Acao acao2 = new Acao("ITSA4", "Itaú Unibanco Holding S.A.", "Banco");
        Acao acao3 = new Acao("VALE3", "Vale S.A.", "Mineradora");
        Acao acao4 = new Acao("BBDC3", "Banco Bradesco S.A.", "Banco");
        Acao acao5 = new Acao("PETR4", "Petróleo Brasileiro S.A.", "Petróleo e gás");
        Acao acao6 = new Acao("MGLU3", "Magazine Luiza S.A.", "Varejo");
        Acao acao7 = new Acao("BBAS3", "Banco do Brasil S.A.", "Banco");
        Acao acao8 = new Acao("SANB11", "Banco Santander Brasil S.A.", "Banco");
        Acao acao9 = new Acao("ABEV3", "Ambev S.A.", "Bebidas");
        Acao acao10 = new Acao("NTCO3", "Natura &Co.", "Cosméticos");
        Acao acao11 = new Acao("ITUB4", "Banco Itaú Unibanco Holding S.A.", "Banco");
        Acao acao12 = new Acao("JBSS3", "JBS S.A.", "Alimentos");
        Acao acao13 = new Acao("WEGE3", "Weg S.A.", "Eletrônica");
        Acao acao14 = new Acao("TIMS3", "TIM Participações S.A.", "Telecomunicações");
        Acao acao15 = new Acao("ELET3", "Eletrobras S.A.", "Eletricidade");
        Acao acao16 = new Acao("COGN3", "Cognitiva Tecnologia S.A.", "Tecnologia");
        Acao acao17 = new Acao("EMBR3", "Embraer S.A.", "Aeronáutica");
        Acao acao18 = new Acao("RADL3", "Raia Drogasil S.A.", "Varejo farmacêutico");
        Acao acao19 = new Acao("MRFG3", "MRF S.A.", "Cimento");
        Acao acao20 = new Acao("FLRY3", "Florybal Alimentos S.A.", "Alimentos");

        bolsa.addAcao(acao1);
        bolsa.addAcao(acao2);
        bolsa.addAcao(acao3);
        bolsa.addAcao(acao4);
        bolsa.addAcao(acao5);
        bolsa.addAcao(acao6);
        bolsa.addAcao(acao7);
        bolsa.addAcao(acao8);
        bolsa.addAcao(acao9);
        bolsa.addAcao(acao10);
        bolsa.addAcao(acao11);
        bolsa.addAcao(acao12);
        bolsa.addAcao(acao13);
        bolsa.addAcao(acao14);
        bolsa.addAcao(acao15);
        bolsa.addAcao(acao16);
        bolsa.addAcao(acao17);
        bolsa.addAcao(acao18);
        bolsa.addAcao(acao19);
        bolsa.addAcao(acao20);

        Broker broker1 = new Broker(bolsa.getInstance());
        Broker broker2 = new Broker(bolsa.getInstance());
        Broker broker3 = new Broker(bolsa.getInstance());
        Broker broker4 = new Broker(bolsa.getInstance());



        broker1.assinarAcao("B3SA3");
        broker1.assinarAcao("VALE3");

        broker2.assinarAcao("PETR4");
        broker2.assinarAcao("NTCO3");
        broker2.assinarAcao("BBDC3");

        System.out.println("O broker de ID: 0 assinou a acao: B3SA3 e a VALE3");
        System.out.println("O broker de ID: 1 assinou a acao: PETR4, NTCO3 e BBDC3\n\n");

        ExecutorService executor = Executors.newFixedThreadPool(2);

        Runnable brokerUm = () -> {
            try {
                while (true) {
                        int quantidade = gerarQuantidadeRandomica(30, 100);
                        double preco = gerarValorRandomico(30.0, 100.0);                
                        // Escolhe aleatoriamente entre COMPRA e VENDA

                         OrderType tipoOrdem = OrderType.VENDA;              
                        // Escolhe aleatoriamente uma sigla (por exemplo, AAPL)

                         String[] siglas = {"B3SA3", "ITSA4", "VALE3", "BBDC3", "PETR4", "MGLU3", "BBAS3", "SANB11", "ABEV3", "NTCO3", "ITUB4", "JBSS3", "WEGE3", "TIMS3", "ELET3", "COGN3", "EMBR3", "RADL3", "MRFG3", "FLRY3"};
                         String sigla = siglas[new Random().nextInt(siglas.length)];                
                        // Formata a ordem
                         String ordem = String.format("%s;%s;%d;%.2f;BKR", tipoOrdem, sigla, quantidade, preco);  

                        // Adiciona a ordem à bolsa
                        broker1.enviarOrdem(ordem);
                        Thread.sleep(2000);
                 }
                
                    
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Thread ThreadBrokerUm = new Thread(brokerUm);
        

        Runnable brokerDois = () -> {
            try {
        
                while (true) {
                        int quantidade = gerarQuantidadeRandomica(30, 100);
                        double preco = gerarValorRandomico(30.0, 100.0);                
                        // Escolhe aleatoriamente entre COMPRA e VENDA

                         OrderType tipoOrdem = OrderType.COMPRA;              
                        // Escolhe aleatoriamente uma sigla (por exemplo, AAPL)

                         String[] siglas = {"B3SA3", "ITSA4", "VALE3", "BBDC3", "PETR4", "MGLU3", "BBAS3", "SANB11", "ABEV3", "NTCO3", "ITUB4", "JBSS3", "WEGE3", "TIMS3", "ELET3", "COGN3", "EMBR3", "RADL3", "MRFG3", "FLRY3"};
                         String sigla = siglas[new Random().nextInt(siglas.length)];                
                        // Formata a ordem
                         String ordem = String.format("%s;%s;%d;%.2f", tipoOrdem, sigla, quantidade, preco);  

                        // Adiciona a ordem à bolsa
                        broker2.enviarOrdem(ordem);
                        Thread.sleep(2000);
                    }

                 
            }catch (InterruptedException e) {
                e.printStackTrace();
            }


        
        };

        Thread ThreadBrokerDois  = new Thread(brokerDois);


        Runnable brokerTres = () -> {
                try {
                    while (true) {
                            Thread.sleep(10000);
                            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH");
                            LocalDateTime data = LocalDateTime.now();

                            String dataFormatada = data.format(dateFormatter);

                             OrderType tipoOrdem = OrderType.INFO;              
                            // Escolhe aleatoriamente uma sigla (por exemplo, AAPL)

                             String[] siglas = {"B3SA3", "ITSA4", "VALE3", "BBDC3", "PETR4", "MGLU3", "BBAS3", "SANB11", "ABEV3", "NTCO3", "ITUB4", "JBSS3", "WEGE3", "TIMS3", "ELET3", "COGN3", "EMBR3", "RADL3", "MRFG3", "FLRY3"};
                             String sigla = siglas[new Random().nextInt(siglas.length)];                
                            // Formata a ordem
                             String ordem = String.format("%s;%s;%s", tipoOrdem, sigla, dataFormatada);  
                            // Adiciona a ordem à bolsa
                            broker3.enviarOrdemInfo(ordem);
                            Thread.sleep(2000);
                    }

                 
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Thread ThreadBrokerTres  = new Thread(brokerTres);
        
        ThreadBrokerUm.start();
        ThreadBrokerDois.start();
        ThreadBrokerTres.start();
        

    }

    private static int gerarQuantidadeRandomica(int min, int max) {
        return new Random().nextInt(max - min) + min + 1; 
    }
    
    private static double gerarValorRandomico(double min, double max) {
        double valor = new Random().nextDouble() * (max - min) + min;
        return Math.max(valor, 0.01); 
    }

}
