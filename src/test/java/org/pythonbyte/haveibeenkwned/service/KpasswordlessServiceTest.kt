package org.pythonbyte.haveibeenkwned.service

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.pythonbyte.haveibeenkwned.domain.KPasswordlessIdentity
import org.pythonbyte.krux.properties.PropertyReader

class KpasswordlessServiceTest {
    private val propertiesFile = "/kpasswordless.properties"
    private val propertyReader = PropertyReader(propertiesFile)
    private val kpasswordlessService = KpasswordlessServiceImpl()

    private val privateKey = propertyReader.get("kpasswordless.keys.private")
    private val userName = propertyReader.get("kpasswordless.identity.userName")
    private val userId = propertyReader.get("kpasswordless.identity.userId")
    private val displayName = propertyReader.get("kpasswordless.identity.displayName")


    @Test
    fun testRegisterToken() {
        val identity = KPasswordlessIdentity()
        identity.userId = userId;
        identity.displayName = displayName;
        identity.userName = userName;

        System.out.println( "Token is " + kpasswordlessService.registerToken( privateKey, identity ) );
    }

    @Test
    fun testAddAlias() {
        val identity = KPasswordlessIdentity()
        identity.userId = userId;

        assertTrue( kpasswordlessService.addAliases( privateKey, identity, listOf( "Joe Jones", "yoddle") ) );
    }

    @Test
    fun testSignIn() {
        val identity = KPasswordlessIdentity()
        identity.userId = userId;
        identity.displayName = displayName;
        identity.userName = userName;

        val token = kpasswordlessService.registerToken( privateKey, identity );

        val signIn = kpasswordlessService.signin( privateKey, token )

        assertTrue( signIn.isSuccess );
        System.out.println( signIn.device );
    }
}