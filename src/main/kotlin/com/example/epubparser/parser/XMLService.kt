package com.example.epubparser.parser

import kotlinx.coroutines.*
import org.w3c.dom.Document
import javax.xml.parsers.DocumentBuilderFactory

class XMLService {
    companion object {
        fun parseStats(term: String, firstYear: Int = 2022, lastYear: Int = firstYear): List<Int> {
            val database: String = "pubmed"

            val countList = mutableListOf<Int>()
            for (currentYear in firstYear..lastYear) {
                runBlocking {
                    launch {
                        val basicSearch =
                            "esearch.fcgi?db=$database&term=$term&mindate=$currentYear&maxdate=$currentYear&rettype[count]"
                        val url = BASE_URL + basicSearch
                        val doc: Document = withContext(Dispatchers.IO) {
                            DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(url)
                        }
                        // doc.documentElement.normalize()
                        countList.add(
                            Integer.parseInt(
                                doc.getElementsByTagName("Count").item(0).textContent
                            )
                        )
                        delay(300L)
                    }
                }
            }
            println(countList)
            return countList
        }
    }
}

private const val BASE_URL = "https://eutils.ncbi.nlm.nih.gov/entrez/eutils/"
