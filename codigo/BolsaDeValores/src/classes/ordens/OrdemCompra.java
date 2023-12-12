package classes.ordens;

import classes.Acao;
import classes.Broker;

public class OrdemCompra extends OrdemConcrets {


    public OrdemCompra(Acao acao, int quantidade, Broker broker, boolean ativo){
        super(acao, quantidade, broker, ativo);
    }

    

    public void atualizar(int quantidade){

    }
    
    public void alterarStatus(){

    }
    public boolean getAtivo(){


    }

    
}
