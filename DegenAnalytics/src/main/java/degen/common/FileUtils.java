package degen.common;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

@Component
public class FileUtils {
	public Document getHtmlPage(String url) {
		Document doc;
		try {
			doc = Jsoup.connect(url).maxBodySize(Integer.MAX_VALUE).timeout(600000).get();
		} catch (IOException e) {
			return null; 
		}	
		return doc; 
	}
}
