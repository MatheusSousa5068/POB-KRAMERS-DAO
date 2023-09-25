package appconsole;

import com.db4o.ObjectContainer;

import models.Produto;
import models.TipoProduto;

public class Cadastrar {
	protected ObjectContainer manager;
	
	public Cadastrar() {
		try {
			manager = Util.conectarBanco();

			System.out.println("cadastrando...");
			TipoProduto tipoproduto1 = new TipoProduto("Diversos");
			manager.store(tipoproduto1);
			manager.commit();
			
			TipoProduto tipoproduto2 = new TipoProduto("Bebidas");
			manager.store(tipoproduto2);
			manager.commit();
			
			TipoProduto tipoproduto3 = new TipoProduto("Doces");
			manager.store(tipoproduto3);
			manager.commit();

			TipoProduto tipoproduto4 = new TipoProduto("Carnes");
			manager.store(tipoproduto4);
			manager.commit();

			TipoProduto tipoproduto5 = new TipoProduto("Frutas");
			manager.store(tipoproduto5);
			manager.commit();

			TipoProduto tipoproduto6 = new TipoProduto("Congelados");
			manager.store(tipoproduto6);
			manager.commit();
			
			Produto produto1_1 = new Produto("Cheetos", 3.50, tipoproduto1);
			tipoproduto1.adicionar(produto1_1);
			manager.store(produto1_1);
			manager.commit();

			Produto produto1_2 = new Produto("Doritos", 4.00, tipoproduto1);
			tipoproduto1.adicionar(produto1_2);
			manager.store(produto1_2);
			manager.commit();

			Produto produto1_3 = new Produto("Ruffles", 5.00, tipoproduto1);
			tipoproduto1.adicionar(produto1_3);
			manager.store(produto1_3);
			manager.commit();
			
			Produto produto2_1 = new Produto("Coca-cola", 5.00, tipoproduto2);
			tipoproduto2.adicionar(produto2_1);
			manager.store(produto2_1);
			manager.commit();

			Produto produto2_2 = new Produto("Pepsi", 4.50, tipoproduto2);
			tipoproduto2.adicionar(produto2_2);
			manager.store(produto2_2);
			manager.commit();
			
			Produto produto2_3 = new Produto("Agua Mineral", 2.00, tipoproduto2);
			tipoproduto2.adicionar(produto2_3);
			manager.store(produto2_3);
			manager.commit();
			
			Produto produto3_1 = new Produto("Chocolate", 4.00, tipoproduto3);
			tipoproduto3.adicionar(produto3_1);
			manager.store(produto3_1);
			manager.commit();

			Produto produto3_2 = new Produto("Gelatina de Morango", 3.25, tipoproduto3);
			tipoproduto3.adicionar(produto3_2);
			manager.store(produto3_2);
			manager.commit();
			
			Produto produto3_3 = new Produto("Goiabada", 2.50, tipoproduto3);
			tipoproduto3.adicionar(produto3_3);
			manager.store(produto3_3);
			manager.commit();

			Produto produto4_1 = new Produto("Carne de Boi", 12.50, tipoproduto4);
			tipoproduto4.adicionar(produto4_1);
			manager.store(produto4_1);
			manager.commit();
			
			Produto produto4_2 = new Produto("Frango Grelhado", 9.00, tipoproduto4);
			tipoproduto4.adicionar(produto4_2);
			manager.store(produto4_2);
			manager.commit();
			
			Produto produto4_3 = new Produto("Costela Suina", 11.75, tipoproduto4);
			tipoproduto4.adicionar(produto4_3);
			manager.store(produto4_3);
			manager.commit();
			
			Produto produto5_1 = new Produto("Macas", 2.00, tipoproduto5);
			tipoproduto5.adicionar(produto5_1);
			manager.store(produto5_1);
			manager.commit();
			
			Produto produto5_2 = new Produto("Bananas", 1.50, tipoproduto5);
			tipoproduto5.adicionar(produto5_2);
			manager.store(produto5_2);
			manager.commit();
			
			Produto produto5_3 = new Produto("Morangos", 3.25, tipoproduto5);
			tipoproduto5.adicionar(produto5_3);
			manager.store(produto5_3);
			manager.commit();
			
			Produto produto6_1 = new Produto("Pizza Congelada", 8.50, tipoproduto6);
			tipoproduto6.adicionar(produto6_1);
			manager.store(produto6_1);
			manager.commit();
			
			Produto produto6_2 = new Produto("Peixe Congelado", 7.75, tipoproduto6);
			tipoproduto6.adicionar(produto6_2);
			manager.store(produto6_2);
			manager.commit();
			
			Produto produto6_3 = new Produto("Legumes Congelados", 4.50, tipoproduto6);
			tipoproduto6.adicionar(produto6_3);
			manager.store(produto6_3);
			manager.commit();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		Util.desconectar();
		System.out.println("\nfim do programa !");
	}
	
	public static void main(String[] args) {
		new Cadastrar();
	}
}
