package models;

public class Produto {
    private String nome;
    private TipoProduto tipoproduto;
    private double preco;

    public Produto(String nome, double preco, TipoProduto tipoproduto) {
        this.nome = nome;
        this.preco = preco;
        this.tipoproduto = tipoproduto;
        this.tipoproduto.adicionar(this); // Relacionamento Inverso
    }

    public TipoProduto getTipoproduto() {
		return tipoproduto;
	}

    public void setTipoproduto(TipoProduto tipoproduto) {
        this.tipoproduto = tipoproduto;
        tipoproduto.adicionar(this);
    }
	
	public String getNome() {
	return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
	return "Produto [nome=" + nome + ", tipoproduto=" + tipoproduto.getNome() + ", preco=" + preco + "]";
    }
    
    
}
