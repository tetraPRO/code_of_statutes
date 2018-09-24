package code.util

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements

class GetLegal {


    GetLegal(){

    }

    def law(String title, String section, String paragraph) {

        File input = new File("/home/philip/Projects/cornell_law_${title}_${section}.html")
        Document doc = Jsoup.parse(input, "UTF-8", "www.law.cornell.edu")

        Elements law = doc.getElementsByClass("section")

        law
    }
}
