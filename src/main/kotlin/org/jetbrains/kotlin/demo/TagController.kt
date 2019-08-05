package org.jetbrains.kotlin.demo

import java.util.concurrent.atomic.AtomicLong
import org.springframework.web.bind.annotation.*


@RestController
class TagController {

    val counter = AtomicLong()


    @GetMapping("/tags")
    fun getTags(): Tag {
        return Tag(counter.incrementAndGet(), "url", "sourceHandle", listOf("one", "two", "three", "four"))
    }

    @GetMapping("/tags/{id}")
    fun getTag(@PathVariable id: String): Tag {
        return Tag(counter.incrementAndGet(), "url", "sourceHandle", listOf("one", "two", "three", "four"))
    }

    @PostMapping("/tags", "application/json", "application/json")
    fun postTag(@RequestBody tag: Tag) {
        println("oi")
    }
}