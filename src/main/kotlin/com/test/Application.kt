package com.test

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.web.reactive.config.EnableWebFlux

@EnableWebFlux
@SpringBootApplication
internal class Application

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}
