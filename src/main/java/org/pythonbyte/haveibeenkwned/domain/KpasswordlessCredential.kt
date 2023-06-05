package org.pythonbyte.haveibeenkwned.domain

import org.pythonbyte.krux.json.JsonObject

class KpasswordlessCredential {
    var id = ""
    var type = ""
    var publicKey = ""
    var userHandle = ""
    var origin = ""
    var country = ""
    var device = ""

    companion object {
        fun createList(array: List<JsonObject>): List<KpasswordlessCredential> {
            return array.map { jsonObject ->
                val credential = KpasswordlessCredential()

                val descriptorObject = jsonObject.getObject("descriptor")

                credential.id = descriptorObject.getString("id")
                credential.type = descriptorObject.getString( "type" )
                credential.publicKey = jsonObject.getString("publicKey")
                credential.userHandle = jsonObject.getString("userHandle")
                credential.origin = jsonObject.getString("origin")
                credential.country = jsonObject.getString("country")
                credential.device = jsonObject.getString("device")

                credential;
            }
        }
    }
}