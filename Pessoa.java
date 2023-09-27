//Vinnicius Oliveira Rodrigues e Giselle Leandro S. De Araujo
public class Pessoa
{
  static int kp;
  String nome;
  char sexo;
  int idade; 

    public Pessoa()
    {
       kp++;
    }
    public Pessoa(String nome, char sexo, int idade)
    {
        this.nome = nome;
        this.sexo = sexo;
        this.idade = idade;  
        kp++;
    }
    public String getNome()
    {
        return this.nome;
    }
    public void setNome(String nome)
    {
        this.nome = nome;
    }
    public char getSexo(){
        return this.sexo;
    }
    public void setSexo(char sexo){
        this.sexo = sexo;
    }
    public int getIdade() {
    return idade;
    }
    public void setIdade(int idade){
        this.idade = idade; 
    }
    public static int getKp()
    {
      return kp; 
    }
    }