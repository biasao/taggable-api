package org.jetbrains.kotlin.demo

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import kotlinx.serialization.json.*
import org.json.JSONObject

@CrossOrigin(origins = ["http://localhost:8081", "http://1608c82a.ngrok.io"])
@RestController
class FeedController {

    final val baseURL = "http://192.168.99.100:5984"

    @GetMapping("/feeds/{id}")
    fun getFeed(@PathVariable id: String): Feed {
        val response = khttp.get(url = "$baseURL/feeds/$id")
        if(response.statusCode == 200) {
            val json = Json.nonstrict
            return json.parse(Feed.serializer(), response.jsonObject.toString())
        }
        throw ResponseStatusException(HttpStatus.NOT_FOUND, "user not found")
    }
}