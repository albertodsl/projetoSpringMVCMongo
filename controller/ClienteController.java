package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import entity.Cliente;
import persistence.ClienteDao;

@Controller
public class ClienteController {
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public ModelAndView iniciar() {
		return new ModelAndView("index");
	}
	
	@RequestMapping(value="/cadastrar", method=RequestMethod.POST)
	public ModelAndView cadastrar(Cliente c) {
		
		ModelAndView mv = new ModelAndView("index");
		
		try {
			
			new ClienteDao().save(c);
			mv.addObject("msg", "Dados gravados com sucesso");
			
			//Limpar objeto e formulário
			c = new Cliente();
			mv.addObject(c);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			mv.addObject("msg", "Erro: " + ex.getMessage());
		}
		
		return mv;
		
	}
	
	@RequestMapping(value="/listar", method=RequestMethod.GET)
	public ModelAndView listar() {
		
		ModelAndView mv = new ModelAndView("sistema");
		
		try {
			
			mv.addObject("lista", new ClienteDao().findAll());
			
		} catch (Exception ex) {
			ex.printStackTrace();
			mv.addObject("msg", "Erro: " + ex.getMessage());
		}
		
		return mv;
		
	}
	
	@RequestMapping(value="/listarCod/{cod}", method=RequestMethod.GET)
	public ModelAndView listarCod(@PathVariable("cod") String cod) {
		
		ModelAndView mv = new ModelAndView("sistema");
		
		try {
			
			mv.addObject("lista", new ClienteDao().findByCode(new Double(cod)));
			
		} catch (Exception ex) {
			ex.printStackTrace();
			mv.addObject("msg", "Erro: " + ex.getMessage());
		}
		
		return mv;
		
	}
	
	@RequestMapping(value="/deletar/{cod}", method=RequestMethod.GET)
	public ModelAndView deletar(@PathVariable("cod") String cod) {
		
		ModelAndView mv = new ModelAndView("sistema");
		
		try {
			
			Cliente c = new Cliente();
			c.setId(new Double(cod));
			
			new ClienteDao().delete(c);
			mv.addObject("msg", "Dados deletados");
			mv.addObject("lista", new ClienteDao().findAll());
			
		} catch (Exception ex) {
			ex.printStackTrace();
			mv.addObject("msg", "Erro: " + ex.getMessage());
		}
		
		return mv;
		
	}
	
	@RequestMapping(value="/editar/{cod}", method=RequestMethod.GET)
	public ModelAndView editar(@PathVariable("cod") String cod) {
		
		ModelAndView mv = new ModelAndView("editar");
		
		try {
					
			mv.addObject(new ClienteDao().findByCode(new Double(cod)));
			
		} catch (Exception ex) {
			ex.printStackTrace();
			mv.addObject("msg", "Erro: " + ex.getMessage());
		}
		
		return mv;
		
	}
	
	@RequestMapping(value="/alterar", method=RequestMethod.POST)
	public ModelAndView alterar(Cliente c) {
		
		ModelAndView mv = new ModelAndView("sistema");
		
		try {
			
			new ClienteDao().edit(c);
			mv.addObject("msg", "Dados alterados");
			
			mv.addObject("lista", new ClienteDao().findAll());
			
		} catch (Exception ex) {
			ex.printStackTrace();
			mv.addObject("msg", "Erro: " + ex.getMessage());
		}
		
		return mv;
		
	}

}
