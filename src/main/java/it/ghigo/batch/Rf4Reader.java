package it.ghigo.batch;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;

public class Rf4Reader implements ItemReader<String> {
	private static final Logger log = LoggerFactory.getLogger(Rf4Reader.class);
	private List<String> retList;
	private int lastIndex = 0;

	@Override
	public String read() throws Exception {
		if (retList == null) {
			retList = Collections.synchronizedList(new ArrayList<>());
			String[] regions = new String[] { "GL", "RU", "DE", "US", "FR", "CN", "PL", "KR", "JP", "ES", "IT", "EN" };
			Stream.of(regions).forEach(region -> {
				try {
					parse(region);
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
		}
		if (retList.isEmpty())
			return null;
		if (lastIndex >= retList.size()) {
			retList = null;
			lastIndex = 0;
			return null;
		}
		return retList.get(lastIndex++);
	}

	private void parse(String region) throws Exception {
		long inizio = System.currentTimeMillis();
		log.info("START parsing region " + region);
		// Recupera il contenuto della pagina web
		URL url = new URL("https://rf4game.com/records/weekly/region/" + region + "/");
		String content = Jsoup.parse(url, 30000).outerHtml();
		// Esegue il parsing del contenuto HTML
		Document doc = Jsoup.parse(content);
		// Seleziona i div che hanno la classe "foo"
		Elements elements = doc.selectFirst("div.records").select("div.row:not(.header)");
		for (Element element : elements) {
			Element fishElement = element.selectFirst("div.fish");
			Element fishTextElement = fishElement.selectFirst("div.text");
			if (fishTextElement == null)
				continue;
			String fish = fishTextElement.text();
			String fishIcon = Stream.of(fishElement.selectFirst("div.item_icon").attr("style").split(";"))//
					.filter(s -> s.contains("background-image:"))
					.map(s -> "https:" + StringUtils.substringBetween(s, "'")).findFirst().get();
			Elements records = element.children().select("div.row");
			for (Element record : records) {
				Element weightElement = record.select("div.weight").first();
				if (weightElement == null)
					continue;
				String location = record.select("div.location").first().text();
				Element baitElement = record.select("div.bait_icon").first();
				String bait = baitElement.attr("title");
				String baitIcon = Stream.of(baitElement.attr("style").split(";"))//
						.filter(s -> s.contains("background-image:"))
						.map(s -> "https:" + StringUtils.substringBetween(s, "'")).findFirst().get();
				String data = record.select("div.data").first().text();
				//
				StringBuilder sb = new StringBuilder();
				sb.append(region).append(Rf4Processor.SEP);
				sb.append(location).append(Rf4Processor.SEP);
				sb.append(fish).append(Rf4Processor.SEP);
				sb.append(fishIcon).append(Rf4Processor.SEP);
				sb.append(getWeightKg(weightElement)).append(Rf4Processor.SEP);
				sb.append(bait).append(Rf4Processor.SEP);
				sb.append(baitIcon).append(Rf4Processor.SEP);
				sb.append(data);
				//
				retList.add(sb.toString());
			}
		}
		log.info("END parsing region " + region + " in -> " + (System.currentTimeMillis() - inizio));
	}

	private String getWeightKg(Element weightElement) {
		String text = weightElement.text();
		String weightStr = StringUtils.substringBeforeLast(text, " ").replace(",", ".");
		String um = StringUtils.substringAfterLast(text, " ");
		double weight = Double.valueOf(weightStr);
		if (StringUtils.equalsIgnoreCase(um, "g"))
			weight = weight / 1000;
		return String.valueOf(weight);
	}
}