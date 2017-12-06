package web.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import web.scraping.jsoup.Indicator;
import web.scraping.jsoup.TestJsoupCanadaPatents;

@RestController
public class ServingLayerController {

	@RequestMapping("/indicators")
	public List<Indicator> serve(
			@RequestParam(value = "user", required = true, defaultValue = "null") String user)
			throws JsonParseException, JsonMappingException, IOException {
		return TestJsoupCanadaPatents.getIndicators(user);
	}
	
}