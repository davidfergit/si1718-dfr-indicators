package web.scraping.jsoup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TestJsoupIndicators {
	
	public static List<Indicator> getIndicators(String user) throws IOException{
		/* Obtengo el investigador de Google Scholar */
		String BASE_URL = "https://scholar.google.es/citations?user=" + user + "&hl=en";
		//Document investigador = Jsoup.parse(new URL(BASE_URL), 10000);
		Document investigador = Jsoup.connect("https://scholar.google.es/citations?user=" + user + "&hl=en").get();
		Element tablaIndicadores = investigador.getElementById("gsc_rsb_st");
		Elements thead = tablaIndicadores.getElementsByTag("thead");
		Elements tbody = tablaIndicadores.getElementsByTag("tbody");
		
		String columns = thead.iterator().next().getElementsByClass("gsc_rsb_sth").text();
		List<Indicator> indicators = new ArrayList<Indicator>();
		
		for(Iterator<Element> iterator = tbody.iterator(); iterator.hasNext(); ) { 
			 Element element = iterator.next();
			 Elements tds = element.getElementsByTag("tr");
			 
			 for(Iterator<Element> it = tds.iterator(); it.hasNext(); ) {
				 
				 Element td = it.next();
				 
				 String indicator = td.getElementsByClass("gsc_rsb_f").text();
				 String values = td.getElementsByClass("gsc_rsb_std").text();
				 indicators.add(new Indicator(indicator, columns, values));
			 }
			
		}
		return indicators;
	}
}
