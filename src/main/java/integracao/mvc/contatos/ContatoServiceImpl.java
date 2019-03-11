package integracao.mvc.contatos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContatoServiceImpl implements ContatoService{

	@Autowired
	private ContatoRepository contatoRepository;

	@Override
	public void inserir(Contato contato) {
		contatoRepository.save(contato);
	}

	@Override
	public void remover(Long id) {
		contatoRepository.deleteById(id);
	}

	@Override
	public List<Contato> buscarContatos() {
		return contatoRepository.findAll();
	}

	@Override
	public Contato buscarContato(Long id) {
		return contatoRepository.getOne(id);
	}

}
