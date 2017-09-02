package ua.controller;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sun.mail.handlers.handler_base;

import models.CountryModel;
import models.HobbieModel;
import ua.entity.Person;
import ua.repository.PersonsRepository;

@Controller

public class PersonController {
	@Autowired
	private PersonsRepository personsRepository;

	@GetMapping("/")
	public ModelAndView main(Model model) {

		return new ModelAndView("login", "person", new Person());
	}

	@PostMapping("/enter")
	public String enter(@Valid @ModelAttribute("person") Person person, BindingResult bindingResult,
			Model model) {
		if (bindingResult.hasErrors()) {
			return "login";
		}
		HobbieModel hm = new HobbieModel();
		CountryModel cm = new CountryModel();
		model.addAttribute("person", person);
		model.addAttribute("listHobbies", hm.findAll());
		model.addAttribute("listCountries", cm.findAll());
		return "form";
	}

	@PostMapping("/save")
	public ModelAndView save(@RequestParam String name, @RequestParam String lastName, @RequestParam String gender,
			@RequestParam String email, @RequestParam String password, @RequestParam String position,
			@RequestParam String description, @RequestParam String[] hobbie, @RequestParam String country,
			Model model) {

		Person person = new Person();
		person.setName(name);
		person.setLastName(lastName);
		person.setGender(gender);
		person.setEmail(email);
		person.setPassword(password);
		person.setPosition(position);
		person.setDescription(description);
		person.setHobbie(hobbie);
		person.setCountry(country);
		personsRepository.save(person);
		return new ModelAndView("redirect:/index");
	}

	@GetMapping("/index")
	public String index(Model model) {

		model.addAttribute("persons", personsRepository.findAll());
		return "list";
	}

	@GetMapping("/delete/{id}")
	public String delete1(@PathVariable Integer id) {
		Person per = personsRepository.getPersonById(id);
		personsRepository.delete(per);
		return "redirect:/index";
	}

}
