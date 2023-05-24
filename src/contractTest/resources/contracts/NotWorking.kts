import org.springframework.cloud.contract.spec.ContractDsl.Companion.contract
import org.springframework.http.MediaType

contract {
    request {
        headers {
            contentType = MediaType.MULTIPART_FORM_DATA
        }
        url = url("/multipart/test")
        method = POST
        multipart {
            field(
                "message", named(
                    // name of the file
                    value(consumer(regex(nonEmpty)), producer("message.json")),
                    // content of the file
                    value(consumer(regex(nonEmpty)), producer(file("message.json"))),
                    // content type for the part
                    value(consumer(regex(nonEmpty)), producer("application/json"))
                )
            )
            field(
                "attachments",
                named(
                    // name of the file
                    value(consumer(regex(nonEmpty)), producer("filename.xml")),
                    // content of the file
                    value(consumer(regex(nonEmpty)), producer("CONTENT".encodeToByteArray())),
                    // content type for the part
                    value(consumer(regex(nonEmpty)), producer("application/xml"))
                )
            )
        }
    }
    response {
        status = OK
    }
}
