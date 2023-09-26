package appconsole;

import regras_negocio.Fachada;

public class CadastrarUsuario {

	public CadastrarUsuario() {
		try {
			Fachada.inicializar();

			System.out.println("cadastrando usuarios...");
			Fachada.cadastrarUsuario("matheus", "1234");
			Fachada.cadastrarUsuario("marcela", "5678");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		Fachada.finalizar();
		System.out.println("\nfim do programa !");
	}



	public static void main(String[] args) {
		new CadastrarUsuario();
	}
}