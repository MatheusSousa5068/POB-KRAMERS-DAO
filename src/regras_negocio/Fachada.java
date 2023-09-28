package regras_negocio;

import java.util.ArrayList;
import java.util.List;

import daodb4o.DAO;
import daodb4o.DAOProduto;
import daodb4o.DAOTipoProduto;
import daodb4o.DAOVenda;
import daodb4o.DAOUsuario;
import models.Produto;
import models.TipoProduto;
import models.Venda;
import models.Usuario;

public class Fachada {
	private Fachada() {
	}

	private static DAOProduto daoproduto = new DAOProduto();
	private static DAOTipoProduto daotipoproduto = new DAOTipoProduto();
	private static DAOVenda daovenda = new DAOVenda();
	private static DAOUsuario daousuario = new DAOUsuario();
	public static Usuario logado;

	public static void inicializar() {
		DAO.open();
	}

	public static void finalizar() {
		DAO.close();
	}

	public static void cadastrarProduto(String nome, double preco, String nometipoproduto) throws Exception {
		DAO.begin();
		Produto produto = daoproduto.read(nome);
		TipoProduto tipoproduto = daotipoproduto.read(nometipoproduto);
		if (produto != null)
			throw new Exception("Produto já cadastrado:" + nome);
		if (tipoproduto == null)
			throw new Exception("TipoProduto " + nometipoproduto + " inexistente");
		produto = new Produto(nome, preco, tipoproduto);

		daoproduto.create(produto);
		DAO.commit();
	}

	public static void cadastrarTipoProduto(String nome) throws Exception {
		DAO.begin();
		TipoProduto tipoproduto = daotipoproduto.read(nome);
		if (tipoproduto != null)
			throw new Exception("TipoProduto já cadastrado:" + nome);
		tipoproduto = new TipoProduto(nome);

		daotipoproduto.create(tipoproduto);
		DAO.commit();
	}

	public static Venda cadastrarVenda(String data, double desconto) throws Exception {
		DAO.begin();

		Venda venda = new Venda(data, desconto);

		daovenda.create(venda);
		DAO.commit();

		return venda;
	}

	public static void adicionarProdutoEmVenda(int idVenda, String nomeProduto) throws Exception {
		DAO.begin();
		Venda venda = daovenda.read(idVenda);
		if (venda == null)
			throw new Exception("Venda com id" + idVenda + "não existe");

		Produto produto = daoproduto.read(nomeProduto);
		if (produto == null)
			throw new Exception("Produto não existe");

		venda.adicionar(produto);
		daovenda.update(venda);
		DAO.commit();
	}

	public static void removerProdutoDeVenda(int idVenda, String nomeProduto) throws Exception {
		DAO.begin();
		Venda venda = daovenda.read(idVenda);
		if (venda == null)
			throw new Exception("Venda com id" + idVenda + "não existe");

		for (Produto p : venda.getProdutos()) {
			if (p.getNome().equals(nomeProduto)) {
				venda.remover(p);
				daovenda.update(venda);
				return;
			}
		}

		DAO.commit();

		throw new Exception("Produto não existe em venda");
	}

	public static List<Venda> vendaDataX(String data) {
		DAO.begin();
		List<Venda> vendas = daovenda.vendasDataX(data);
		DAO.commit();

		return vendas;
	}

	public static List<Venda> vendasComMaisDeNProdutos(int Qntd) {
		DAO.begin();
		List<Venda> vendas = daovenda.vendasComMaisDeNProdutos(Qntd);
		DAO.commit();

		return vendas;
	}

	public static List<Venda> vendasComProdutoDePrecoX(double preco) {
		DAO.begin();
		List<Venda> vendas = daovenda.vendasComProdutoDePrecoX(preco);
		DAO.commit();
		DAO.close();

		return vendas;
	}

	public static void excluirProduto(String nomeProduto) throws Exception {
		DAO.begin();
		
		Produto produto = daoproduto.read(nomeProduto);
		
		if (produto == null) {
			throw new Exception("Produto não existe: " + nomeProduto);
		}

		List<Venda> vendas = daovenda.vendasComProdutoP(nomeProduto);
		for(Venda v: vendas) {
			v.remover(produto);
			daovenda.update(v);
		}
	
		TipoProduto tipoProduto = produto.getTipoproduto();
		tipoProduto.remover(produto);
		daotipoproduto.update(tipoProduto);
		daoproduto.delete(produto);
		
		DAO.commit();
		DAO.close();
		
	}

	public static void excluirTipoProduto(String nomeTipoProduto) throws Exception {
		DAO.begin();
		TipoProduto tipoProduto = daotipoproduto.read(nomeTipoProduto);
		if (tipoProduto == null)
			throw new Exception("Tipo de produto não existe: " + nomeTipoProduto);

		TipoProduto secundario = daotipoproduto.read("Diversos");

		List<Produto> produtosParaMover = new ArrayList<>(tipoProduto.getProdutos());

		// lista temporária
		for (Produto p : produtosParaMover) {
			p.setTipoproduto(secundario);
			tipoProduto.remover(p);
			daotipoproduto.update(tipoProduto);

			daotipoproduto.update(secundario);
		}

		daotipoproduto.delete(tipoProduto);
		DAO.commit();
		DAO.close();
	}


	
	
	public static List<Produto> listarProdutos() {
		DAO.begin();
		List<Produto> resultados = daoproduto.readAll();
		DAO.commit();
		return resultados;
	}

	public static List<TipoProduto> listarTipoProdutos() {
		DAO.begin();
		List<TipoProduto> resultados = daotipoproduto.readAll();
		DAO.commit();
		return resultados;
	}

	public static List<Venda> listarVendas() {
		DAO.begin();
		List<Venda> resultados = daovenda.readAll();
		DAO.commit();
		return resultados;
	}

	public static List<Usuario> listarUsuarios() {
		DAO.begin();
		List<Usuario> resultados = daousuario.readAll();
		DAO.commit();
		return resultados;
	}

	// ------------------Usuario------------------------------------
	public static Usuario cadastrarUsuario(String nome, String senha) throws Exception {
		DAO.begin();
		Usuario usu = daousuario.read(nome);
		if (usu != null)
			throw new Exception("Usuario ja cadastrado:" + nome);
		usu = new Usuario(nome, senha);

		daousuario.create(usu);
		DAO.commit();
		return usu;
	}

	public static Usuario localizarUsuario(String nome, String senha) {
		Usuario usu = daousuario.read(nome);
		if (usu == null)
			return null;
		if (!usu.getSenha().equals(senha))
			return null;
		return usu;
	}
}