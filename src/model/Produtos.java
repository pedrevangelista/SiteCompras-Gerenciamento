package model;
public class Produtos {
    
    private int id;
    private String nome;
    private String sexo;
    private String descricao;

    public Produtos(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Produtos(String nome, String sexo, String descricao) {
        this.nome = nome;
        this.sexo = sexo;
        this.descricao = descricao;
    }

    public Produtos(int id, String nome, String sexo, String descricao) {
        this.id = id;
        this.nome = nome;
        this.sexo = sexo;
        this.descricao = descricao;
        
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }


    public Produtos() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
}
