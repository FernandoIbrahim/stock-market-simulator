
package classes.ordens;

import java.time.LocalDateTime;

import classes.Acao;

public class InfoFactory implements OperacaoFactory{


    @Override
    public Ordem criarOredem(Acao acao , LocalDateTime dateTime) {
        return new OrdemInfo(acao, dateTime);
    }
    

}