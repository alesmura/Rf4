package it.ghigo.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;

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
import it.ghigo.service.LocationService;
import it.ghigo.service.LureService;

@Controller
public class Rf4Controller {

	@Autowired
	private FishService fishService;

	@Autowired
	private LocationService locationService;

	@Autowired
	private LureService lureService;

	@Autowired
	private CatchService catchService;

	@GetMapping("/")
	public String index(Model model) {
		return fishList(model);
	}

	@GetMapping("/fishList")
	public String fishList(Model model) {
		model.addAttribute("fishList", fishService.findAll());
		return "fishList";
	}

	@GetMapping("/locationList")
	public String locationList(Model model) {
		model.addAttribute("locationList", locationService.findAll());
		return "locationList";
	}

	@GetMapping("/lureList")
	public String lureList(Model model) {
		model.addAttribute("lureList", lureService.findAll());
		return "lureList";
	}

	@GetMapping("/catchList")
	public String catchList(@RequestParam(defaultValue = "") String fishParam,
			@RequestParam(defaultValue = "") String locationParam, @RequestParam(defaultValue = "") String lureParam,
			Model model) throws Exception {
		CatchSearchParameter catchSearchParameter = new CatchSearchParameter();
		//
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdf.parse(sdf.format(cal.getTime())));
		if (StringUtils.isNotBlank(fishParam)) {
			catchSearchParameter.setFishName(fishParam);
			catchSearchParameter.setExactFishName(true);
			cal.add(Calendar.DAY_OF_YEAR, -3);
		}
		if (StringUtils.isNotBlank(locationParam)) {
			catchSearchParameter.setLocationName(locationParam);
			cal.add(Calendar.DAY_OF_YEAR, -3);
		}
		if (StringUtils.isNotBlank(lureParam)) {
			catchSearchParameter.setLureName(lureParam);
			catchSearchParameter.setExactLureName(true);
			cal.add(Calendar.DAY_OF_YEAR, -3);
		}
		catchSearchParameter.setDt(cal.getTime());
		model.addAttribute("catchSearchParameter", catchSearchParameter);
		model.addAttribute("catchList", catchService.findByCatchSearchParameter(catchSearchParameter));
		return "catchList";
	}

	@PostMapping("/catchList")
	public String catchListSubmit(@ModelAttribute CatchSearchParameter catchSearchParameter, Model model)
			throws Exception {
		model.addAttribute("catchSearch", catchSearchParameter);
		model.addAttribute("catchList", catchService.findByCatchSearchParameter(catchSearchParameter));
		return "catchList";
	}

	@GetMapping("/lureCatchList")
	public String lureCatchList(Model model) {
		long inizio = System.currentTimeMillis();
		model.addAttribute("lureCatchList", lureService.findLureFishCatchList());
		System.out.println(System.currentTimeMillis() - inizio);
		return "lureCatchList";
	}

	@GetMapping("/fishCatchList")
	public String fishCatchList(Model model) {
		long inizio = System.currentTimeMillis();
		model.addAttribute("fishCatchList", fishService.findFishLureCatchList());
		System.out.println(System.currentTimeMillis() - inizio);
		return "fishCatchList";
	}
}