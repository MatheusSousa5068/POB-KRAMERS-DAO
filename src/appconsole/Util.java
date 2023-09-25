package appconsole;

import java.util.List;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.config.EmbeddedConfiguration;
import com.db4o.query.Query;

import models.Produto;
import models.TipoProduto;
import models.Venda;

public class Util {
	private static ObjectContainer manager;
	
	public static ObjectContainer conectarBanco(){
		if (manager != null)
			return manager;	
		
		//---------------------------------------------------------------
		//configurar, criar e conectar banco local (na pasta do projeto
		//---------------------------------------------------------------
		
		EmbeddedConfiguration config =  Db4oEmbedded.newConfiguration(); 
		config.common().messageLevel(0);  // mensagens na tela 0(desliga),1,2,3...
		
		// habilitar cascata na altera��o, remo��o e leitura
		config.common().objectClass(Produto.class).cascadeOnDelete(false);
		config.common().objectClass(Produto.class).cascadeOnUpdate(true);
		config.common().objectClass(Produto.class).cascadeOnActivate(true);
		
		config.common().objectClass(Venda.class).cascadeOnDelete(false);
		config.common().objectClass(Venda.class).cascadeOnUpdate(true);
		config.common().objectClass(Venda.class).cascadeOnActivate(true);
		
		config.common().objectClass(TipoProduto.class).cascadeOnDelete(false);
		config.common().objectClass(TipoProduto.class).cascadeOnUpdate(true);
		config.common().objectClass(TipoProduto.class).cascadeOnActivate(true);
		
		manager = Db4oEmbedded.openFile(config, "banco.db4o");
		return manager;
	}
	
	public static void desconectar() {
		if(manager!=null) {
			manager.close();
			manager=null;
		}
	}
	
	
	public static int gerarIdVenda() {
		if(manager.query(Venda.class).size()==0) 
			return 1;
		
		Query q = manager.query();
		q.constrain(Venda.class);
		q.descend("id").orderDescending();
		List<Venda> resultados = q.execute();

		if(resultados.size()>0) {
			Venda venda = resultados.get(0);
			return venda.getId() + 1;
		}
		else
			return 1;
	}

}
