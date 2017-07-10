package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import entity.Memo;
import repository.MemoRepository;

@Controller
public class MemoController {

	@Autowired
	private MemoRepository memoRepository;

	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("memos", memoRepository.findAll());
		return "index";
	}

	@PostMapping("/")
	public String save(@RequestParam String text) {
		Memo memo = new Memo();
		memo.setText(text);
		memoRepository.save(memo);
		return "redirect:/";
	}

	@PostMapping("/a")
	public String delete(@RequestParam String text) {
		List<Memo> ar = memoRepository.getMemoByText(text);
		Memo memo = ar.get(0);
		memoRepository.delete(memo);

		return "redirect:/";

	}
}