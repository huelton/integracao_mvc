package integracao.mvc.agenda;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import integracao.mvc.contatos.Contato;
import integracao.mvc.contatos.ContatoService;

@Controller
@RequestMapping("/agenda")
public class AgendaController {

	@Autowired
	private ContatoService contatoService;

	@GetMapping("/")
	public ModelAndView getContatos(ModelAndView mav){
		mav.addObject("contatos",contatoService.buscarContatos());
		return mav;
	}

	@GetMapping("/contato/{id}")
	public ModelAndView getContato(@PathVariable("id") Long id,ModelAndView mav){
		mav.setViewName("agenda/contato");
		mav.addObject("contato",contatoService.buscarContato(id));
		return mav;
	}

	@GetMapping("/remover/{id}")
	public ModelAndView remover(@PathVariable("id") Long id, final RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("successMessage", "Contato removido com sucesso");
		contatoService.remover(id);
		return new ModelAndView("redirect:agenda/");
	}

	@GetMapping("/cadastro")
	public ModelAndView cadastro(ModelAndView mav) {
		mav.addObject("contato", new Contato());
		return mav;
	}

	@PostMapping("/inserir")
	public ModelAndView inserir(@Valid Contato contato,BindingResult bindingResult, ModelAndView mav) {
		if (!bindingResult.hasErrors()) {
			mav.addObject("successMessage", "Contato cadastrado com sucesso");
			contatoService.inserir(contato);
		}
		return mav;
	}

}
