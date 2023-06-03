package org.pythonbyte.haveibeenkwned.service

import org.pythonbyte.haveibeenkwned.domain.KPasswordlessIdentity

interface KpasswordlessService {
    fun registerToken( privateKey: String, identity: KPasswordlessIdentity ): String
    fun addAliases( privateKey: String, identity: KPasswordlessIdentity, aliases: List<String> ): Boolean
}