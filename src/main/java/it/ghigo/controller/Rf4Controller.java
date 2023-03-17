package it.ghigo.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import it.ghigo.service.StatsService;

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

	@Autowired
	private StatsService statsService;

	private static final Logger log = LoggerFactory.getLogger(Rf4Controller.class);

	//

	@GetMapping("/")
	public String index(Model model) {
		return fishList(model);
	}

	@GetMapping("/fishList")
	public String fishList(Model model) {
		long inizio = System.currentTimeMillis();
		log.info("Inizio /fishList");
		model.addAttribute("fishList", fishService.findAll());
		log.info("Fine /fishList -> " + (System.currentTimeMillis() - inizio));
		return "fishList";
	}

	@GetMapping("/locationList")
	public String locationList(Model model) {
		long inizio = System.currentTimeMillis();
		log.info("Inizio /locationList");
		model.addAttribute("locationList", locationService.findAll());
		log.info("Fine /locationList -> " + (System.currentTimeMillis() - inizio));
		return "locationList";
	}

	@GetMapping("/lureList")
	public String lureList(Model model) {
		long inizio = System.currentTimeMillis();
		log.info("Inizio /lureList");
		model.addAttribute("lureList", lureService.findAll());
		log.info("Fine /lureList -> " + (System.currentTimeMillis() - inizio));
		return "lureList";
	}

	@GetMapping("/catchList")
	public String catchList(@RequestParam(defaultValue = "") String fishParam,
			@RequestParam(defaultValue = "") String locationParam, @RequestParam(defaultValue = "") String lureParam,
			Model model) throws Exception {
		long inizio = System.currentTimeMillis();
		log.info("Inizio /catchList");
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
		log.info("Fine /catchList -> " + (System.currentTimeMillis() - inizio));
		return "catchList";
	}

	@PostMapping("/catchList")
	public String catchListSubmit(@ModelAttribute CatchSearchParameter catchSearchParameter, Model model)
			throws Exception {
		long inizio = System.currentTimeMillis();
		log.info("Inizio /catchList");
		model.addAttribute("catchSearch", catchSearchParameter);
		model.addAttribute("catchList", catchService.findByCatchSearchParameter(catchSearchParameter));
		log.info("Fine /catchList -> " + (System.currentTimeMillis() - inizio));
		return "catchList";
	}

	@GetMapping("/lureCatchList")
	public String lureCatchList(Model model) {
		long inizio = System.currentTimeMillis();
		log.info("Inizio /lureCatchList");
		model.addAttribute("lureCatchList", lureService.findLureFishCatchList());
		log.info("Fine /lureCatchList -> " + (System.currentTimeMillis() - inizio));
		return "lureCatchList";
	}

	@GetMapping("/fishCatchList")
	public String fishCatchList(Model model) {
		long inizio = System.currentTimeMillis();
		log.info("Inizio /fishCatchList");
		model.addAttribute("fishCatchList", fishService.findFishLureCatchList());
		log.info("Fine /fishCatchList -> " + (System.currentTimeMillis() - inizio));
		return "fishCatchList";
	}

	@GetMapping("/statsList")
	public String statsList(Model model) {
		long inizio = System.currentTimeMillis();
		log.info("Inizio /statsList");
		model.addAttribute("statsList", statsService.findAll());
		log.info("Fine /statsList -> " + (System.currentTimeMillis() - inizio));
		return "statsList";
	}
}