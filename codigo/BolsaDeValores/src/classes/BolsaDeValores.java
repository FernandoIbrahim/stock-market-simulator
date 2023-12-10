package classes;

import java.util.HashMap;
import java.util.Map;

public class BolsaDeValores {

    private static BolsaDeValores bolsaDeValores;

    private Map<String, LivroDeOfertas> acoes;

    private BolsaDeValores(){
        acoes  = new HashMap<>();
    }

    public void assinar(String sigla, Broker broker){
        if(acoes.containsKey(sigla)){
            
        }
    }

    public static BolsaDeValores getInstance() {
        if (bolsaDeValores == null) {
            bolsaDeValores = new BolsaDeValores();
        }
        return bolsaDeValores;
    }

    public LivroDeOfertas possuiAcao(String sigla) {
        LivroDeOfertas livroDeOfertas =  acoes.get(sigla);
        if(livroDeOfertas == null){
            //throw new Exception()
        }
        else{
            return livroDeOfertas;
        }
    }



}
