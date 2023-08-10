package org.pythonbyte.kpasswordless.domain

import org.pythonbyte.krux.json.JsonObject

class KPasswordlessCredential {
    var id = ""
    var type = ""
    var publicKey = ""
    var userHandle = ""
    var origin = ""
    var country = ""
    var device = ""

    companion object {
        private fun create(jsonObject: JsonObject): KPasswordlessCredential {
            val credential = KPasswordlessCredential()

            val descriptorObject = jsonObject.getObject("descriptor")
            credential.id = descriptorObject.getString("id")
            credential.type = descriptorObject.getString( "type" )

            credential.publicKey = jsonObject.getString("publicKey")
            credential.userHandle = jsonObject.getString("userHandle")
            credential.origin = jsonObject.getString("origin")
            credential.country = jsonObject.getString("country")
            credential.device = jsonObject.getString("device")

            return credential;
        }

        fun createList(array: List<JsonObject>): List<KPasswordlessCredential> {
            return array.map { jsonObject -> create( jsonObject ) }
        }
    }
}