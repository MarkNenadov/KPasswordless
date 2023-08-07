package org.pythonbyte.kpasswordless.service.exceptions

import com.squareup.okhttp.Response;

class KpasswordlessServiceException(response: Response, requestAlias: String) : RuntimeException() {
    val code: Int = response.code()
    val responseMessage: String = response.message()
    val responseBody: String = response.body().string().trim()
    val requestAlias: String = requestAlias;

    override val message: String?
        get() = "KpasswordlessServiceException: $requestAlias request failed with code [$code] [$responseMessage] json [$responseBody]"
}