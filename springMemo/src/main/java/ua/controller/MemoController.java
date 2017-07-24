package ua.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ua.entity.Memo;
import ua.repository.MemoRepository;

@Controller
public class MemoController {

	@Autowired
	private MemoRepository memoRepository;

	
	@InitBinder("memo")
    public void initBinder(WebDataBinder binder) 
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, "date", new CustomDateEditor(
                dateFormat, true));
    }
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("memos", memoRepository.findAll());
		return "index";
	}

	@PostMapping("/")
	public String save(@RequestParam String text, Date date) {
		Memo memo = new Memo();
		memo.setText(text);
		memo.setDate(new java.util.Date());
		memoRepository.save(memo);
		return "main/";
	}

//	@PostMapping("/a")
//	public String delete(@RequestParam Integer id) {
//		Memo ar = memoRepository.findMemoById(id);
//		memoRepository.delete(ar);
//		return "redirect:/";
//
//	}
	
	@GetMapping("/delete/{id}")
	public String delete1 (@PathVariable Integer id){
		Memo ar = memoRepository.findMemoById(id);
		memoRepository.delete(ar);

		return "redirect:/";
	}
	
	
}