package appconsole;

import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.query.Query;

import models.Produto;
import models.TipoProduto;
import models.Venda;

public class Listar {
	protected ObjectContainer manager;
	
	public Listar() {
		try {
			manager = Util.conectarBanco();
			
			System.out.println("\n---Listagem de produtos: ");
			Query qProduto = manager.query();
			qProduto.constrain(Produto.class);
			
			List<Produto> produtos = qProduto.execute();
			for(Produto p: produtos)
				System.out.println(p);
			
			System.out.println("\n---Listagem de vendas: ");
			Query qVenda = manager.query();
			qVenda.constrain(Venda.class);
			
			List<Venda> vendas = qVenda.execute();
			for(Venda v: vendas)
				System.out.println(v);
			
			System.out.println("\n---Listagem de tipos de produtos: ");
			Query qTipos = manager.query();
			qTipos.constrain(TipoProduto.class);
			
			List<TipoProduto> tipos = qTipos.execute();
			for(TipoProduto t: tipos)
				System.out.println(t);
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		Util.desconectar();
		System.out.println("\nfim do programa !");
	}
	
	public static void main(String[] args) {
		new Listar();
	}
}
