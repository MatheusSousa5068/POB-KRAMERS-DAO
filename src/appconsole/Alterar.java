package appconsole;

import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.query.Candidate;
import com.db4o.query.Evaluation;
import com.db4o.query.Query;

import models.Produto;
import models.TipoProduto;
import models.Venda;

public class Alterar {
	protected ObjectContainer manager;

	public Alterar() {
		try {
			this.manager = Util.conectarBanco();

			System.out.println("Venda de chocolate");

			Query q1 = this.manager.query();
			q1.constrain(Produto.class);
			q1.descend("nome").constrain("Chocolate");
			List<Produto> resultados1 = q1.execute();

			if (resultados1.size() > 0) {
				Produto chocolate = (Produto) resultados1.get(0);
				Venda venda1 = new Venda("31/08/2023", 60);
				int id = Util.gerarIdVenda();
				venda1.setId(id);
				venda1.adicionar(chocolate);
				this.manager.store(venda1);
				this.manager.commit();
			}

			System.out.println("Venda de coca-cola");

			Query q2 = this.manager.query();
			q2.constrain(Produto.class);
			q2.descend("nome").constrain("Coca-cola");
			List<Produto> resultados2 = q2.execute();

			if (resultados2.size() > 0) {
				Produto cocacola = (Produto) resultados2.get(0);
				Venda venda2 = new Venda("01/09/2023", 50);
				int id = Util.gerarIdVenda();
				venda2.setId(id);
				venda2.adicionar(cocacola);
				this.manager.store(venda2);
				this.manager.commit();
			}

			System.out.println("Venda de coca-cola e chocolate");

			Query q3 = this.manager.query();
			q3.constrain(Produto.class);
			q3.descend("nome").constrain("Coca-cola");
			List<Produto> resultados3 = q3.execute();

			Query q4 = this.manager.query();
			q4.constrain(Produto.class);
			q4.descend("nome").constrain("Chocolate");
			List<Produto> resultados4 = q4.execute();

			if (resultados3.size() > 0 && resultados4.size() > 0) {
				Produto cocacola2 = (Produto) resultados3.get(0);
				Produto chocolate2 = (Produto) resultados4.get(0);
				Venda venda3 = new Venda("03/09/2023", 50);
				int id = Util.gerarIdVenda();
				venda3.setId(id);
				venda3.adicionar(cocacola2);
				venda3.adicionar(chocolate2);
				this.manager.store(venda3);
				this.manager.commit();
			}

			System.out.println("Venda de cheetos, coca-cola e gelatina");

			Query q5 = this.manager.query();
			q5.constrain(Produto.class);
			q5.descend("nome").constrain("Cheetos");
			List<Produto> resultados5 = q5.execute();

			Query q6 = this.manager.query();
			q6.constrain(Produto.class);
			q6.descend("nome").constrain("Coca-cola");
			List<Produto> resultados6 = q6.execute();

			Query q7 = this.manager.query();
			q7.constrain(Produto.class);
			q7.descend("nome").constrain("Gelatina de Morango");
			List<Produto> resultados7 = q7.execute();

			if (resultados5.size() > 0 && resultados6.size() > 0 && resultados7.size() > 0) {
				Produto cheetos = (Produto) resultados5.get(0);
				Produto cocacola = (Produto) resultados6.get(0);
				Produto gelatina = (Produto) resultados7.get(0);

				Venda venda4 = new Venda("05/09/2023", 30);
				int id4 = Util.gerarIdVenda();
				venda4.setId(id4);
				venda4.adicionar(cheetos);
				venda4.adicionar(cocacola);
				venda4.adicionar(gelatina);
				this.manager.store(venda4);
				this.manager.commit();
			}

			System.out.println("Venda de pizza congelada e agua mineral");

			Query q9 = this.manager.query();
			q9.constrain(Produto.class);
			q9.descend("nome").constrain("Pizza Congelada");
			List<Produto> resultados9 = q9.execute();

			Query q10 = this.manager.query();
			q10.constrain(Produto.class);
			q10.descend("nome").constrain("Agua Mineral");
			List<Produto> resultados10 = q10.execute();

			if (resultados9.size() > 0 && resultados10.size() > 0) {
				Produto pizzaCongelada = (Produto) resultados9.get(0);
				Produto aguaMineral = (Produto) resultados10.get(0);
				Venda venda6 = new Venda("09/09/2023", 20);
				int id = Util.gerarIdVenda();
				venda6.setId(id);
				venda6.adicionar(pizzaCongelada);
				venda6.adicionar(aguaMineral);
				this.manager.store(venda6);
				this.manager.commit();
			}

			// Altera o tipo do produto chocolate
			Query q11 = this.manager.query();
			q11.constrain(Produto.class);
			q11.descend("nome").constrain("Chocolate");
			List<Produto> resultados11 = q11.execute();

			if (resultados11.size() > 0) {
				Produto chocolate = resultados11.get(0);

				// Pegar tipo produto "diversos"
				Query q12 = this.manager.query();
				q12.constrain(TipoProduto.class);
				q12.descend("nome").constrain("Diversos");
				List<TipoProduto> resultados12 = q12.execute();

				// Pegar tipo produto "doces"
				Query q13 = this.manager.query();
				q13.constrain(TipoProduto.class);
				q13.descend("nome").constrain("Doces");
				List<TipoProduto> resultados13 = q13.execute();

				if (resultados12.size() > 0) {
					TipoProduto diversos = resultados12.get(0);
					diversos.adicionar(chocolate);
					chocolate.setTipoproduto(resultados12.get(0));

					TipoProduto doces = resultados13.get(0);
					doces.remover(chocolate);

					this.manager.store(chocolate);
					this.manager.store(doces);
					this.manager.commit();
				}
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		Util.desconectar();
		System.out.println("\nfim do programa !");
	}

	public static void main(String[] args) {
		new Alterar();
	}
}