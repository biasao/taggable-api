package org.jetbrains.kotlin.demo

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import kotlinx.serialization.json.Json
import org.json.JSONObject
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@CrossOrigin(origins = ["http://localhost:8081", "http://1608c82a.ngrok.io"])
@RestController
class TagController {
    final val baseURL = "http://192.168.99.100:5984"

    @GetMapping("/tags")
    fun getTags(): List<Tag> {
        return listOf(Tag("someUUID", "mateus.com", User("mateus", "Mateus"), listOf(User("biasao","Alberto"), User("mirianrevers", "Mirian"))))
    }

    @GetMapping("/tags/{id}")
    fun getTag(@PathVariable id: String): Tag {
        return Tag("someUUID", "url", User("mateus", "Mateus"), listOf(User("biasao","Alberto"), User("mirianrevers", "Mirian")))
    }

    @PostMapping("/tags", "application/json", "application/json")
    fun postTag(@RequestBody tag: Tag): Tag {
        tag.targetUsers.forEach {
            val response = khttp.get(url = "$baseURL/feeds/${it.handle}")
            if(response.statusCode == 200) {
                val json = Json.nonstrict
                val feed = json.parse(Feed.serializer(), response.jsonObject.toString())

                val url = "$baseURL/feeds/${it.handle}"
                feed.tags.add(tag)

                val mapper = jacksonObjectMapper()
                val payload = mapper.writeValueAsString(feed)
                khttp.put(url = url, data = payload)
            } else {
                throw ResponseStatusException(HttpStatus.NOT_FOUND, "user not found")
            }
        }
        return tag
    }
}