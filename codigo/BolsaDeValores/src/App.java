import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import classes.Transacao;

public class App {

    

    public static void main(String[] args) throws InterruptedException {


         for (int i = 0; i < 5; i++) {
            Runnable transacao = () -> {
                try {
                    // Lógica para gerar transações aleatórias
                Random random = new Random();
                int quantidade = random.nextInt(100);
                double preco = 100 + (200 - 100) * random.nextDouble(); // Preço entre 100 e 200
                
                Transacao t = new Transacao(acao, quantidade, preco, random.nextBoolean());
                // Lógica para processar a transação (ex: registrar, atualizar dados, etc.)
                System.out.println("Transação realizada: " + t);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };

            Thread thread = new Thread(transacao);
            thread.start();

    
                
            
        }

        
}
}