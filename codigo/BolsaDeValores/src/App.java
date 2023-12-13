
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import classes.*;
import classes.ordens.*;

public class App {

    private static final Lock lock = new ReentrantLock();
    private static volatile boolean compra = true; // Alternância entre ordens de compra e venda

    public static void main(String[] args) throws InterruptedException {

        BolsaDeValores bolsa = BolsaDeValores.getInstance();
        Acao acao1 = new Acao("AAPL", "Apple Inc.", "Empresa de tecnologia");
        Acao acao2 = new Acao("GOOGL", "Alphabet Inc.", "Empresa-mãe do Google");
        Acao acao3 = new Acao("MSFT", "Microsoft Corporation", "Empresa de tecnologia");
        Acao acao4 = new Acao("AMZN", "Amazon.com Inc.", "Empresa de comércio eletrônico");
        Acao acao5 = new Acao("TSLA", "Tesla Inc.", "Fabricante de veículos elétricos");
        Acao acao6 = new Acao("PETR4", "Petra", "Empresa fabricante de carveja");
        bolsa.addAcao(acao1);
        bolsa.addAcao(acao2);
        bolsa.addAcao(acao3);
        bolsa.addAcao(acao4);
        bolsa.addAcao(acao5);
        bolsa.addAcao(acao6);
        Broker broker = new Broker(bolsa.getInstance());
        Broker broker1 = new Broker(bolsa.getInstance());
        Broker broker2 = new Broker(bolsa.getInstance());
        Broker broker3 = new Broker(bolsa.getInstance());


        
        ExecutorService executor = Executors.newFixedThreadPool(2);

        Runnable ordemCompra = () -> {
             try {
                 while (true) {
                     int quantidade = gerarQuantidadeRandomica(30, 100);
         double preco = gerarValorRandomico(30.0, 100.0);

         // Escolhe aleatoriamente entre COMPRA e VENDA
         OrderType tipoOrdem = (Math.random() < 0.5) ? OrderType.COMPRA : OrderType.VENDA;

         // Escolhe aleatoriamente uma sigla (por exemplo, AAPL)
         String[] siglas = {"AAPL", "GOOGL", "MSFT", "AMZN", "TSLA"};
         String sigla = siglas[new Random().nextInt(siglas.length)];

         // Formata a ordem
         String ordem = String.format("%s;%s;%d;%.2f;BKR", tipoOrdem, sigla, quantidade, preco);    
        // Adiciona a ordem à bolsa
        broker.enviarOrdem(ordem);
                    Thread.sleep(1000);
                    
                
            
        }}
         catch (InterruptedException e) {
                e.printStackTrace();
            }};}

    /*
     * Runnable ordemVenda = () -> {
     * try {
     * while (true) {
     * lock.lock();
     * if (!compra) {
     * criarOrdem("Ordem de Venda");
     * compra = true; // Troca para próxima thread
     * }
     * lock.unlock();
     * Thread.sleep(1000);
     * }
     * } catch (InterruptedException e) {
     * e.printStackTrace();
     * }
     * };
     * 
     * Thread tOrdemCompra = new Thread(ordemCompra);
     * Thread tOrdemVenda = new Thread(ordemVenda);
     * 
     * tOrdemCompra.start();
     * tOrdemVenda.start();
     * 
     * // executor.shutdown(); // Não é necessário para threads infinitas
     * }
     */
    private static int gerarQuantidadeRandomica(int min, int max) {
        return new Random().nextInt(max - min + 1) + min;
    }

    private static double gerarValorRandomico(double min, double max) {
        return new Random().nextDouble() * (max - min) + min;
    }

}
