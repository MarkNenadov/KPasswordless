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
            val signIn = KPasswordlessSignIn()

            signIn.timestamp = responseObject.getString( "timestamp" )
            signIn.device = responseObject.getString( "device" )
            signIn.country = responseObject.getString( "country" )
            signIn.credentialId = responseObject.getString( "CredentialId" )
            signIn.userId = responseObject.getString( "userId" )
            signIn.rpId = responseObject.getString( "rpid" )
            signIn.expiresAt = responseObject.getString( "expiresAt" )
            signIn.origin = responseObject.getString( "origin" )
            signIn.nickname = responseObject.getString( "nickname" )
            signIn.type = responseObject.getString( "type" )
            signIn.isSuccess = responseObject.getString( "isSuccess" ) === "sucess"

            return signIn
        }
    }

}