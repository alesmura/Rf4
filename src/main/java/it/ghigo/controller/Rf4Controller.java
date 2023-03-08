package it.ghigo.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import it.ghigo.model.Catch;
import it.ghigo.repository.CatchRepository;

@Controller
public class Rf4Controller {

	@Autowired
	private CatchRepository repository;

	@GetMapping("/list")
	public String list(Model model) {
		model.addAttribute("catchSearch", new Catch());
		model.addAttribute("catchList", new ArrayList<>());
		return "list";
	}

	@PostMapping("/list")
	public String listSubmit(@ModelAttribute Catch catchSearch, Model model) {
		List<Catch> catchList = new ArrayList<>();
		if (StringUtils.isNotBlank(catchSearch.getLocation()) || StringUtils.isNotBlank(catchSearch.getFish())
				|| StringUtils.isNotBlank(catchSearch.getLure())) {
			catchList = repository
					.findByLocationContainingIgnoreCaseAndFishContainingIgnoreCaseAndLureContainingIgnoreCase(
							catchSearch.getLocation(), catchSearch.getFish(), catchSearch.getLure());
			Collections.sort(catchList, new Comparator<Catch>() {
				@Override
				public int compare(Catch o1, Catch o2) {
					int ret = o1.getLocation().compareTo(o2.getLocation());
					if (ret == 0)
						ret = o1.getFish().compareTo(o2.getFish());
					if (ret == 0)
						ret = -Double.compare(o1.getWeightKg(), o2.getWeightKg());
					if (ret == 0)
						ret = o1.getDt().compareTo(o2.getDt());
					if (ret == 0)
						ret = o1.getLure().compareTo(o2.getLure());
					return ret;
				}
			});
		}
		model.addAttribute("catchSearch", catchSearch);
		model.addAttribute("catchList", catchList);
		return "list";
	}

	@GetMapping("/")
	public String index() {

		return "index";
	}

	@GetMapping("/contact")
	public String contact() {

		return "contact";
	}
}
