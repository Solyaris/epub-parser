package com.example.epubparser.parser

import jetbrains.letsPlot.export.ggsave
import jetbrains.letsPlot.geom.*
import jetbrains.letsPlot.letsPlot

class Plotter {
    fun plot(counts: List<Int>, years: List<Int>) {
        // определяем "облако" из точек

        // готовим данные для letsPlot в виду key-value хранилища
        val data = mapOf<String, Any>("Years" to years, "Articles" to counts)

        // график = данные + один слой + другой слой
        val plot = letsPlot(data) +
            // здесь цвет - просто свойство точек
            geomPoint(
                color = "dark-green", size = 3.0
            ) { x = "Years"; y = "Articles" } +
            geomLine { x = "Years"; y = "Articles" } 

        // сохраняем в файл
        ggsave(plot, "Trends.png")
        println("bye!")
    }
}
