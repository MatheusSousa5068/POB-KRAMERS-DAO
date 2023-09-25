package appconsole;

import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.query.Candidate;
import com.db4o.query.Evaluation;
import com.db4o.query.Query;

import models.Venda;
import models.TipoProduto;
import models.Produto;

public class Consultar {
	protected ObjectContainer manager;

	public Consultar() {
		try {
			manager = Util.conectarBanco();

			System.out.println("consultas... \n");
			System.out.println("\nQuais as vendas da data X");
			Query q1 = manager.query();
			q1.constrain(Venda.class);
			q1.descend("data").constrain("01/09/2023");
			List<Venda> resultados = q1.execute();
			for (Venda v : resultados)
				System.out.println(v);

			System.out.println("\nQuais as vendas que contem um produto de preco X");
			Query q2 = manager.query();
			q2.constrain(Venda.class);
			q2.descend("produtos").descend("preco").constrain(3.5);
			List<Venda> resultados2 = q2.execute();
			for (Venda v : resultados2)
				System.out.println(v);

			System.out.println("\nQuais as vendas com mais de N produtos");
			Query q3 = manager.query();
			q3.constrain(Venda.class);
			q3.constrain(new FiltroQtndProdutosEmVenda());
			List<Venda> resultados3 = q3.execute();
			for (Venda v : resultados3)
				System.out.println(v);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		Util.desconectar();
		System.out.println("\nfim do programa !");
	}

	public static void main(String[] args) {
		new Consultar();
	}
}

class FiltroQtndProdutosEmVenda implements Evaluation {
	public void evaluate(Candidate candidate) {
		Venda v = (Venda) candidate.getObject();
		if (v.getProdutos().size() > 1)
			candidate.include(true);
		else
			candidate.include(false);
	}
}
