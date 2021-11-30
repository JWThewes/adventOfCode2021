package de.thewes.aoc2021

import de.thewes.aoc2021.days.DayOfAdvent
import org.springframework.beans.factory.NoSuchBeanDefinitionException
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean
import kotlin.system.exitProcess

@SpringBootApplication
class Aoc2021Application {

    @Bean
    fun commandLineRunner(applicationContext: AnnotationConfigApplicationContext): CommandLineRunner {
        return CommandLineRunner {
            if (it == null || it.isEmpty()) {
                println("No day given...")
                runSelectedDayOfAdvent(applicationContext, selectDayOfAdvent())
            } else {
                runSelectedDayOfAdvent(applicationContext, it[0])
            }
        }
    }

    private fun runSelectedDayOfAdvent(
        applicationContext: AnnotationConfigApplicationContext, selectDayOfAdvent: String
    ) {
        try {
            val bean = applicationContext.getBean(selectDayOfAdvent, DayOfAdvent::class.java)
            bean.run()
            println("Day $selectDayOfAdvent finished.")
        } catch (e: NoSuchBeanDefinitionException) {
            println("Day of Advent $selectDayOfAdvent not found. Please select a valid one...")
            runSelectedDayOfAdvent(applicationContext, selectDayOfAdvent())
        }
    }

    private fun selectDayOfAdvent(): String {
        var selectedDay: String? = readLine()
        while (selectedDay == null || selectedDay.isEmpty()) {
            println("Which day do you want to run?")
            selectedDay = readLine()
        }
        if ("exit" == selectedDay) {
            exitProcess(0)
        }
        return selectedDay
    }

}

fun main(args: Array<String>) {
    runApplication<Aoc2021Application>(*args)
}
