package com.practice.eon.crud.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

import com.practice.eon.crud.model.Coworker;
import com.practice.eon.crud.repository.CoworkerRepository;

@RestController
@RequestMapping("/coworkers")
public class CoworkerCtrl {

	@Autowired
	private CoworkerRepository coworkerRepository;
	
	/** Busqueda de todos los registros de compañeros de trabajo.
	 * @return Una lista con todos los registros encontrados.
	 * */
	@GetMapping
	public List<Coworker> findAll() {
		return coworkerRepository.findAll();
	}
	
	
	/** Busqueda por nombre de compañero de trabajo.
	 * @param name El nombre de la persona a buscar.
	 * @return Una lista con todos los registros encontrados.
	 * */
	@PostMapping("/search")
	public ModelAndView findByNameCW(@RequestParam("searchNameCW") String name) {
		ModelAndView mav = new ModelAndView("home");
		mav.addObject("coworkerList", coworkerRepository.findByFirstName(name));
		return mav;
	}
	

	/**
	 * Busca la información de un compañero de trabajo por su Id y redirecciona al formulario para editar la informacion.
	 * @param id el identificador unico del compañero de trabajo.
	 * @return la vista y el objeto en caso de que se encuentre en la base de datos.
	 * */
	@GetMapping("/coworkerChangeForm/{id}")
    public ModelAndView coworkerChangeForm(@PathVariable Long id) {
    	ModelAndView mav = new ModelAndView("editCoworker");
    	
    	Optional<Coworker> opt = coworkerRepository.findById(id);
		if (opt.isPresent()){
			Coworker rel = opt.get();
			mav.addObject("coworker", rel);
		}
    	
        return mav;
    }
	
	/**
	 * Método para dar de alta al compañero de trabajo.
	 * @param cw la información del compañero de trabajo que se dara de alta
	 * @param bindingResult objeto para validacion de datos del formulario.
	 * @return La vista a la que se debe redireccionar en caso de que el formulario sea correcto.
	 * */
	@PostMapping("/addCoworker")
	public ModelAndView addCoworker(@Valid @ModelAttribute("coworkerInfo") Coworker cw, BindingResult bindingResult) {
		ModelAndView mav = new ModelAndView();
		if (bindingResult.hasErrors()) {
			mav.setViewName("addCoworker");
		} else {
			coworkerRepository.save(cw);
			mav.setViewName("redirect:/");
		}

		return mav;
	}

	
	/**
	 * Método para eliminar registros de familiares en la base de datos.
	 * @param id el identificador unico del familar.
	 * @return Objeto con la vista a la que se debe redireccionar.
	 * */
	@DeleteMapping("/deleteCoworker/{id}")
	public ModelAndView deleteCoworker(@PathVariable Long id) {
		coworkerRepository.deleteById(id);
		ModelAndView mav = new ModelAndView("redirect:/");

		return mav;
	}

	
	/**
	 * Método para modificar la información completa del usuario.
	 * @param cw la informacion del compañero de trabajo para modificar.
	 * @return Objeto con la vista a la que se debe redireccionar.
	 * */
//	@PutMapping("/updateCoworker/{id}")
	@RequestMapping(value = "/updateCoworker", method = RequestMethod.POST, headers = "content-type=application/x-www-form-urlencoded")
	public ModelAndView updateCoworker(@Valid @ModelAttribute("coworker") Coworker cw, BindingResult bindingResult) {
		ModelAndView mav = new ModelAndView();
		if (bindingResult.hasErrors()) {
			mav.setViewName("editCoworker");
		} else {
			coworkerRepository.save(cw);
			mav.setViewName("redirect:/");
		}

		return mav;
	}

	/**
	 * @return the coworkerRepository
	 */
	public CoworkerRepository getCoworkerRepository() {
		return coworkerRepository;
	}

	/**
	 * @param coworkerRepository the coworkerRepository to set
	 */
	public void setCoworkerRepository(CoworkerRepository coworkerRepository) {
		this.coworkerRepository = coworkerRepository;
	}
}
