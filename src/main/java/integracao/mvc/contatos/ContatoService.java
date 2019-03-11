package integracao.mvc.contatos;

import java.util.List;

public interface ContatoService {

	void inserir(Contato contato);

	void remover(Long id);

	List<Contato> buscarContatos();

	Contato buscarContato(Long id);

}
