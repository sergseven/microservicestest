package contracts.demo.reactive

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "should return any non-empty account details"

    request {
        url "/account"
        method GET()

    }

    response {
        status OK()
        headers {
            contentType applicationJson()
        }
        body(
                accountNumber: "12345",
                ownerCode: "OWNER_CODE_1",
        )
    }
}