package de.thewes.aoc2021.days.dummy

import de.thewes.aoc2021.days.DayOfAdvent
import org.springframework.stereotype.Component

@Component("dummy")
class DummyDay: DayOfAdvent {

    override fun run() {
        println("This is a dummy day")
    }

}