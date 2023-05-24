package com.test.adapter

import org.springframework.http.MediaType
import org.springframework.http.codec.multipart.FilePart
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
internal class TestController {
    @PostMapping(value = ["/multipart/test"], consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun multipartTest(
        @RequestPart("message") message: RequestDto,
        @RequestPart("attachments") attachments: Flux<FilePart>
    ): Mono<Void> {
        return Mono.empty()
    }

    @PostMapping("/json/test")
    fun jsonTest(
        @RequestBody message: RequestDto
    ): Mono<Void> {
        return Mono.empty()
    }

    @PostMapping(value = ["/aaa/test"], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun aaaTest(): Mono<Void> {
        return Mono.empty()
    }
}
