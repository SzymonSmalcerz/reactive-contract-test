import org.springframework.cloud.contract.spec.ContractDsl.Companion.contract
import org.springframework.http.MediaType

contract {
    request {
        headers {
            accept = MediaType.APPLICATION_JSON
            contentType = MediaType.APPLICATION_JSON
        }
        url =
            url("/json/test")
        method = POST
        body = body(
            """
                {
                	"content": "some content"
                }
            """.trimIndent()
        )
    }
    response {
        status = OK
    }
}
