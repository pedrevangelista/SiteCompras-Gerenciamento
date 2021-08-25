package model;


public class Grade {
    
    private int id;
    private int id_grade;
    private String cor;
    private String tamanho;
    private float preco;
    private int qnt;
    private float promo;

    public float getPromo() {
        return promo;
    }

    public void setPromo(float promo) {
        this.promo = promo;
    }

    public Grade(String cor, String tamanho, float preco, int qnt, float promo) {
        this.cor = cor;
        this.tamanho = tamanho;
        this.preco = preco;
        this.qnt = qnt;
        this.promo = promo;
    }

    public Grade(int id, String cor, String tamanho, float preco, float promo) {
        this.id = id;
        this.cor = cor;
        this.tamanho = tamanho;
        this.preco = preco;
        this.promo = promo;
    }

    public Grade(int id, int id_grade, String cor, String tamanho, float preco, int qnt, float promo) {
        this.id = id;
        this.id_grade = id_grade;
        this.cor = cor;
        this.tamanho = tamanho;
        this.preco = preco;
        this.qnt = qnt;
        this.promo = promo;
    }

    public Grade(String cor, String tamanho, float preco, int qnt) {
        this.cor = cor;
        this.tamanho = tamanho;
        this.preco = preco;
        this.qnt = qnt;
    }

    public Grade(int id, String cor, String tamanho, float preco, int qnt, float promo) {
        this.id = id;
        this.cor = cor;
        this.tamanho = tamanho;
        this.preco = preco;
        this.qnt = qnt;
        this.promo = promo;
    }

    public Grade() {
    }

    public Grade(int id, int id_grade, String cor, String tamanho, float preco, int qnt) {
        this.id = id;
        this.id_grade = id_grade;
        this.cor = cor;
        this.tamanho = tamanho;
        this.preco = preco;
        this.qnt = qnt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_grade() {
        return id_grade;
    }

    public void setId_grade(int id_grade) {
        this.id_grade = id_grade;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public int getQnt() {
        return qnt;
    }

    public void setQnt(int qnt) {
        this.qnt = qnt;
    }
    
    
    
}