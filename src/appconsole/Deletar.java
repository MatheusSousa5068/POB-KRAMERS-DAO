package appconsole;

import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.query.Candidate;
import com.db4o.query.Evaluation;
import com.db4o.query.Query;

import models.Produto;
import models.TipoProduto;
import models.Venda;

public class Deletar {
	protected ObjectContainer manager;

	public Deletar() {
		manager = Util.conectarBanco();
		apagar();
//		apagarTudo();
		Util.desconectar();
	}

	public void apagar() {
		// Deletar o produto cujo nome é chocolate
		Query qChocolate = manager.query();
		qChocolate.constrain(Produto.class);
		qChocolate.descend("nome").constrain("Chocolate");
		List<Produto> resultados1 = qChocolate.execute();
		
		if (resultados1.size() > 0) {
			Produto c = resultados1.get(0);
			
			c.setTipoproduto(null);
			
			Query qTipoProdutoComChocolate = manager.query();
			qTipoProdutoComChocolate.constrain(TipoProduto.class);
			qTipoProdutoComChocolate.constrain(new FiltroPossuiProdutoemTipo(c));
			List<TipoProduto> resultadosTipo = qTipoProdutoComChocolate.execute();
			for(TipoProduto tp: resultadosTipo) {
				tp.remover(c);
				manager.store(tp);
			}
			
			
			Query qVendasComChocolate = manager.query();
			qVendasComChocolate.constrain(Venda.class);
			qVendasComChocolate.constrain(new FiltroPossuiProduto(c));
			List<Venda> resultadosVenda = qVendasComChocolate.execute();
			for(Venda v: resultadosVenda) {
				v.remover(c);
				manager.store(v);
			}
			
			
			manager.delete(c);
			manager.commit();

			
			System.out.println("apagou chocolate");
		} 
		else
			System.out.println("inexistente");
		
		// Deletar venda de ID 5
		Query qVenda = manager.query();
		qVenda.constrain(Venda.class);
		qVenda.descend("id").constrain(5);
		List<Venda> resultados2 = qVenda.execute();
		
		
		if (resultados2.size() > 0) {
			manager.delete(resultados2.get(0));
			manager.commit();

			
			System.out.println("apagou venda 5");
		} 
		else
			System.out.println("inexistente");
	}
	
	public void apagarTudo() {
		// Deletar todos os produtos
		Query qProdutos = manager.query();
		qProdutos.constrain(Produto.class);
		List<Produto> resultadosProdutos = qProdutos.execute();
		
		if (resultadosProdutos.size() > 0) {
			for (Produto p: resultadosProdutos) {
				manager.delete(p);
				manager.commit();
			}
			System.out.println("apagou todos os produtos");
		} else {
			System.out.println("produtos inexistentes");
		}
		
		// Deletar todos os tipos de produtos
		Query qTiposProdutos = manager.query();
		qTiposProdutos.constrain(TipoProduto.class);
		List<TipoProduto> resultadosTiposProdutos = qTiposProdutos.execute();
				
		if (resultadosTiposProdutos.size() > 0) {
			for (TipoProduto tp: resultadosTiposProdutos) {
				manager.delete(tp);
				manager.commit();
			}
			System.out.println("apagou todos os tipos de produtos");
		} else {
			System.out.println("tipos de produtos inexistentes");
		}
		
		// Deletar todas as vendas
		Query qVendas = manager.query();
		qVendas.constrain(Venda.class);
		List<Venda> resultadosVendas = qVendas.execute();
				
		if (resultadosVendas.size() > 0) {
			for (Venda v: resultadosVendas) {
				manager.delete(v);
				manager.commit();
			}
			System.out.println("apagou todas as vendas");
		} else {
			System.out.println("vendas inexistentes");
		}
		
	}

	// =================================================
	public static void main(String[] args) {
		new Deletar();
	}
}


class FiltroPossuiProduto implements Evaluation {
	private Produto p;

	public FiltroPossuiProduto(Produto p) {
		this.p = p;
	}

	public void evaluate(Candidate candidate) {
		Venda v = (Venda) candidate.getObject();
		candidate.include(false);
		for (Produto pr : v.getProdutos()) {
			if (pr.getNome().equals(p.getNome())) {
				candidate.include(true);
			}
		}

	}
}

class FiltroPossuiProdutoemTipo implements Evaluation {
	private Produto p;

	public FiltroPossuiProdutoemTipo(Produto p) {
		this.p = p;
	}

	public void evaluate(Candidate candidate) {
		TipoProduto tp = (TipoProduto) candidate.getObject();
		candidate.include(false);
		for (Produto pr : tp.getProdutos()) {
			if (pr.getNome().equals(p.getNome())) {
				candidate.include(true);
			}
		}

	}
}
