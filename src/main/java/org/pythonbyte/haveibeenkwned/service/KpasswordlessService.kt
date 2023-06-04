package org.pythonbyte.haveibeenkwned.service

import org.pythonbyte.haveibeenkwned.domain.KPasswordlessIdentity
import org.pythonbyte.haveibeenkwned.domain.KPasswordlessSignIn

interface KpasswordlessService {
    fun registerToken( privateKey: String, identity: KPasswordlessIdentity ): String
    fun addAliases( privateKey: String, identity: KPasswordlessIdentity, aliases: List<String> ): Boolean
    fun signin( privateKey: String, token: String ): KPasswordlessSignIn
}