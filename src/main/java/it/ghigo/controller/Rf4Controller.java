package it.ghigo.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.ghigo.model.parameter.CatchSearchParameter;
import it.ghigo.service.CatchService;
import it.ghigo.service.FishService;

@Controller
public class Rf4Controller {

	@Autowired
	private FishService fishService;

	@Autowired
	private CatchService catchService;

	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("fishList", fishService.findAll());
		return "index";
	}

	@GetMapping("/catchList")
	public String catchList(@RequestParam(defaultValue = "") String fishParam, Model model) {
		CatchSearchParameter catchSearchParameter = new CatchSearchParameter();
		if (StringUtils.isNotBlank(fishParam))
			catchSearchParameter.setFishName(fishParam);
		model.addAttribute("catchSearchParameter", catchSearchParameter);
		model.addAttribute("catchList",
				catchService.findByLocationContainingIgnoreCaseAndFishContainingIgnoreCaseAndLureContainingIgnoreCase(
						catchSearchParameter));
		return "catchList";
	}

	@PostMapping("/catchList")
	public String catchListSubmit(@ModelAttribute CatchSearchParameter catchSearchParameter, Model model) {
		model.addAttribute("catchSearch", catchSearchParameter);
		model.addAttribute("catchList",
				catchService.findByLocationContainingIgnoreCaseAndFishContainingIgnoreCaseAndLureContainingIgnoreCase(
						catchSearchParameter));
		return "catchList";
	}

	@GetMapping("/contact")
	public String contact() {

		return "contact";
	}
}
