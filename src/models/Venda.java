package models;

import java.util.ArrayList;
import java.util.List;

public class Venda {
    private Integer id;
    private String data;
    private List<Produto> produtos = new ArrayList<>();
    private double desconto;
    private double valortotal;
	private double valorpago;

    public Venda(String data, double desconto) {
        this.desconto = desconto;
        this.data = data;
        this.valortotal = 0.0;
        this.valorpago = 0.0;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public double getValortotal() {
	return valortotal;
    }

    public void setValortotal(double valortotal) {
	this.valortotal = valortotal;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    public double getValorpago() {
        return valorpago;
    }

    public void setValorpago(double valorpago) {
        this.valorpago = valorpago;
    }

    public void adicionar(Produto p){
        produtos.add(p);
        this.valortotal += p.getPreco();
        this.valorpago = this.valortotal - (this.valortotal * (this.desconto/100));
    }
    public void remover(Produto p){
        produtos.remove(p);
        this.valortotal -= p.getPreco();

        this.valorpago = this.valortotal - (this.valortotal * (this.desconto/100));
    }

    public Produto localizar(String nome){
        for(Produto p: produtos)
            if (p.getNome().equals(nome))
                return p;
        return null;
    }
    
    public List<Produto> getProdutos() {
		return produtos;
	}

    @Override
    public String toString() {
        return "Venda [id=" + id + ", data=" + data + ", produtos=" + produtos + ", desconto=" + desconto
                + ", valortotal=" + valortotal + ", valorpago=" + valorpago + "]";
    }
}
