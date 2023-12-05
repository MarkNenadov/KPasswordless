package org.pythonbyte.kpasswordless.domain

class KPasswordlessIdentity {
    var userName = ""
    var userId = ""
    var displayName = ""

    fun map(): Map<String, String> {
        return mapOf(
            "userId" to userId,
            "username" to userName,
            "displayname" to displayName,
        )
    }
}
