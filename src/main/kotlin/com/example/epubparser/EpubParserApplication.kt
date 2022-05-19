package com.example.epubparser

import com.example.epubparser.parser.Plotter
import com.example.epubparser.parser.XMLService

// @SpringBootApplication
// class EpubParserApplication

fun main(args: Array<String>) {
    val plotter = Plotter()
    // parser.plot()
    val firstYear = 2015
    val lastYear = 2022
    val term = "asthma"
    val counts = XMLService.parseStats(term, firstYear, lastYear)
    plotter.plot(counts, (firstYear..lastYear).toList())
    // runApplication<EpubParserApplication>(*args)
}
