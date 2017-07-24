package ua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
	public ModelAndView enter(@ModelAttribute("person") Person person) {
		return new ModelAndView("index", "person", person);
	}

	@PostMapping("/save")
	public String save(@RequestParam String name, @RequestParam String lastName, @RequestParam String gender,
			@RequestParam String email) {
		Person person = new Person();
		person.setName(name);
		person.setLastName(lastName);
		person.setGender(gender);
		person.setEmail(email);
		personsRepository.save(person);
		return "main";
	}

	@GetMapping("/index")
	public String index(Model model) {
		model.addAttribute("persons", personsRepository.findAll());
		return "index";
	}

}
