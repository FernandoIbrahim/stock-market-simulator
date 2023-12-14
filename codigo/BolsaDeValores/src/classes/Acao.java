/**
 * Classe que representa uma ação na bolsa de valores 
 * 
 */
package classes;

public class Acao {
    
   private String id;
   private String nome;
   private String desc;
   /**
    * 
    * @return nome da ação
    
    */

   public String getSigla() {
       return id;
   }
   /**
    * Construtor da classe Acao
    * @param id sigla da ação
    * @param nome nome da ação
    * @param desc descrição da ação
    */
   public Acao(String id, String nome, String desc){
      this.id = id;
      this.desc = desc;
      this.nome = nome;
   }
   @Override
   public String toString() {
       return this.id;
   }

}
