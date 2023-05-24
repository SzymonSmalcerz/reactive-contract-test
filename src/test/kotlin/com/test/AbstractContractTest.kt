@file:Suppress("ReactiveStreamsUnusedPublisher")

package com.test

import io.restassured.module.webtestclient.RestAssuredWebTestClient
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.context.ApplicationContext
import org.springframework.test.context.ActiveProfiles


@Tag("contract")
@ActiveProfiles("default", "contractTest")
@SpringBootTest(
    webEnvironment = WebEnvironment.RANDOM_PORT,
    properties = [
        "spring.main.web-application-type=reactive"
    ]
)
internal abstract class AbstractContractTest {
    @Autowired
    private lateinit var context: ApplicationContext

    @BeforeEach
    fun setup() {
        RestAssuredWebTestClient.applicationContextSetup(context)
    }
}
