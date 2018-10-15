import connect.AuthenticationManager
import spock.lang.Specification

class EmailConnectionTests extends Specification {

    def "should be able to obtain access token"() {
        given: "An instance of Authentication Manager"
        AuthenticationManager authenticationManager = AuthenticationManager.getInstance()

        when: "an attempt to connect is made"
        authenticationManager.connect()

        then: "the access token should not be null"
        assert authenticationManager.authResult.getAccessToken() != null
    }

}
