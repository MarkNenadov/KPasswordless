package org.pythonbyte.kpasswordless.domain

import org.pythonbyte.krux.json.JsonObject

class KPasswordlessSignIn {
    var isSuccess = false
    var userId = ""
    var timestamp = ""
    var origin = ""
    var device = ""
    var country = ""
    var nickname = ""
    var expiresAt = ""
    var credentialId = ""
    var type = ""
    var rpId = ""

    companion object {
        fun create(responseObject: JsonObject): KPasswordlessSignIn {
            val signIn =
                KPasswordlessSignIn().apply {
                    with(responseObject) {
                        timestamp = getString("timestamp")
                        device = getString("device")
                        country = getString("country")
                        credentialId = getString("CredentialId")
                        userId = getString("userId")
                        rpId = getString("rpid")
                        expiresAt = getString("expiresAt")
                        origin = getString("origin")
                        nickname = getString("nickname")
                        type = getString("type")
                        isSuccess = getString("isSuccess") == "success"
                    }
                }

            return signIn
        }
    }
}
