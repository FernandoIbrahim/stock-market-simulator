package classes;

public class Acao {
    
   private String id;
   private String nome;
   private String desc;

   public String getSigla() {
       return id;
   }

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
