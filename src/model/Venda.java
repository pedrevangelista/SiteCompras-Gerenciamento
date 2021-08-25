
package model;

import java.sql.Date;


public class Venda {
    private int idVenda;
    private int idGrade;
    private String cpf;
    private Date dataCompra;
    private float preco;

    public Venda(int idVenda, int idGrade, String cpf, Date dataCompra, float preco) {
        this.idVenda = idVenda;
        this.idGrade = idGrade;
        this.cpf = cpf;
        this.dataCompra = dataCompra;
        this.preco = preco;
    }

    public Venda() {
    }

    public int getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(int idVenda) {
        this.idVenda = idVenda;
    }

    public int getIdGrade() {
        return idGrade;
    }

    public void setIdGrade(int idGrade) {
        this.idGrade = idGrade;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(Date dataCompra) {
        this.dataCompra = dataCompra;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }
    
}
