package appconsole;

import regras_negocio.Fachada;

public class Deletar2 {
	public Deletar2() {
		try {
			Fachada.inicializar();
            Fachada.excluirProduto("Chocolate");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		Fachada.finalizar();
		System.out.println("\nfim do programa !");
	}

	public static void main(String[] args) {
		new Deletar2();
	}
}
