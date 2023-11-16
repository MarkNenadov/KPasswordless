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
            val signIn = KPasswordlessSignIn().apply {
                timestamp = responseObject.getString( "timestamp" )
                device = responseObject.getString( "device" )
                country = responseObject.getString( "country" )
                credentialId = responseObject.getString( "CredentialId" )
                userId = responseObject.getString( "userId" )
                rpId = responseObject.getString( "rpid" )
                expiresAt = responseObject.getString( "expiresAt" )
                origin = responseObject.getString( "origin" )
                nickname = responseObject.getString( "nickname" )
                type = responseObject.getString( "type" )
                isSuccess = responseObject.getString( "isSuccess" ) == "success"
            }

            return signIn
        }
    }

}