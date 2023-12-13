
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

    
    private static Lock lock = new ReentrantLock();
    public static void main(String[] args) throws InterruptedException {
        LocalDateTime agora = LocalDateTime.now();

    
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        
        String dataFormatada = agora.format(formato);
        ExecutorService executor = Executors.newFixedThreadPool(2);

             Runnable ordemCompra = () ->{

                try{
                    lock.lock();
                    
                    BolsaDeValores bolsa = BolsaDeValores.getInstancia();
                    Broker broker = new Broker(bolsa);
                    broker.assinar("PETR4");
                    Acao acao1 = new Acao("10","PETR4","Petrobras");
                    Ordem ordem = new OrdemCompra(acao1, 100, 26.46, broker);
                    LivroDeOfertas livroDeOfertas = bolsa.possuiLivroDeOfertas("PETR4");
                   
                    System.out.println("Ordem criada: " + ordem);
                    bolsa.addAcao(acao1);
                    bolsa.addOperacaoCompraVenda(ordem.toString(), broker);
                    
                    System.out.println("Ordem criada: " + ordem);
                    
                    Thread.sleep(1000);
                    lock.unlock();
                } catch (InterruptedException e) {
                    e.printStackTrace();

                }
             }

                Runnable ordemVenda = () ->{
                    try{
                        lock.lock();
                        BolsaDeValores bolsa = BolsaDeValores.getInstancia();
                        bolsa.adicionarOrdem(ordem);
                    
                        System.out.println("Ordem criada: " + ordem);
                        Thread.sleep(1000);
                        lock.unlock();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
    
                    }
                }
          
            Thread tOrdemCompra = new Thread(ordemCompra);
            Thread tOrdemVenda = new Thread(ordemVenda);
            executor.submit(tOrdemVenda);
            executor.submit(tOrdemCompra);

    
                
            
        }

        
}
