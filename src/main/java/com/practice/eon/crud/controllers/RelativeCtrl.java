package com.practice.eon.crud.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.practice.eon.crud.model.Relative;
import com.practice.eon.crud.repository.RelativeRepository;

@RestController
@RequestMapping("/relatives")
public class RelativeCtrl {

	@Autowired
	private RelativeRepository relativeRepository;

	/** Busqueda de todos los registros de Familiares.
	 * @return Una lista con todos los registros encontrados.
	 * */
	@GetMapping
	public List<Relative> findAll() {
		return relativeRepository.findAll();
	}
	
	/** Busqueda por nombre de compañero de trabajo.
	 * @param name El nombre de la persona a buscar.
	 * @return Una lista con todos los registros encontrados.
	 * */
	@PostMapping("/search")
	public ModelAndView findByName(@RequestParam("searchNameF") String name) {
		ModelAndView mav = new ModelAndView("home");
		mav.addObject("relativeList", relativeRepository.findByFirstName(name));
		return mav;
	}

	/**
	 * Busca la información del familiar por su Id y redirecciona al formulario para editar la informacion.
	 * @param id el identificador unico del familiar.
	 * @return la vista y el objeto en caso de que se encuentre en la base de datos.
	 * */
	@GetMapping("/relativeChangeForm/{id}")
    public ModelAndView relativeChangeForm(@PathVariable Long id) {
    	ModelAndView mav = new ModelAndView("editRelative");
    	
    	Optional<Relative> opt = relativeRepository.findById(id);
		if (opt.isPresent()){
			Relative rel = opt.get();
			mav.addObject("relative", rel);
		}
    	
        return mav;
    }
	
	/**
	 * Método para dar de alta al familiar.
	 * @param relative la información del familiar que se dara de alta
	 * @param bindingResult objeto para validacion de datos del formulario.
	 * @return La vista a la que se debe redireccionar en caso de que el formulario sea correcto.
	 * */
	@PostMapping("/addRelative")
	public ModelAndView addRelative(@Valid @ModelAttribute("relative") Relative relative, BindingResult bindingResult) {
		ModelAndView mav = new ModelAndView();
		if (bindingResult.hasErrors()) {
			mav.setViewName("addRelative");
		} else {
			relativeRepository.save(relative);
			mav.setViewName("redirect:/");
		}

		return mav;
	}

	
	/**
	 * Método para eliminar registros de familiares en la base de datos.
	 * @param id el identificador unico del familar.
	 * @return Objeto con la vista a la que se debe redireccionar.
	 * */
	@DeleteMapping("/deleteRelative/{id}")
	public ModelAndView deleteRelative(@PathVariable Long id) {
		relativeRepository.deleteById(id);
		ModelAndView mav = new ModelAndView("redirect:/");

		return mav;
	}

	
	/**
	 * Método para modificar la información completa del usuario.
	 * @param rel la informacion del familiar para modificar.
	 * @return Objeto con la vista a la que se debe redireccionar.
	 * */
//	@PutMapping("/updateRelative/{id}")
	@RequestMapping(value = "/updateRelative", method = RequestMethod.POST, headers = "content-type=application/x-www-form-urlencoded")
	public ModelAndView updateRelative(@Valid @ModelAttribute("relative") Relative rel, BindingResult bindingResult) {
		ModelAndView mav = new ModelAndView();
		if (bindingResult.hasErrors()) {
			mav.setViewName("editRelative");
		} else {
			relativeRepository.save(rel);
			mav.setViewName("redirect:/");
		}

		return mav;
	}

	/**
	 * @return the relativeRepository
	 */
	public RelativeRepository getRelativeRepository() {
		return relativeRepository;
	}

	/**
	 * @param relativeRepository
	 *            the relativeRepository to set
	 */
	public void setRelativeRepository(RelativeRepository relativeRepository) {
		this.relativeRepository = relativeRepository;
	}

}
