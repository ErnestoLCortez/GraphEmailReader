import com.microsoft.graph.models.extensions.Message
import connect.AuthenticationManager
import msgraph.GraphServiceController
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

    def "should be able to obtain user name"() {
        given: "An instance of service controller"
        GraphServiceController controller = new GraphServiceController()

        when: "an attempt to retrieve display name is made"
        String userName = controller.getUserInfoFromGraph()

        then: "the user name should be present"
        assert userName == "Ernesto Cortez"
    }

    def "should be able to retrieve user mail folders"() {
        given: "An instance of service controller"
        GraphServiceController controller = new GraphServiceController()

        when: "an attempt to retrieve folder names is made"
        def folderCollection = controller
                .getUserFolders()
                .getCurrentPage()
                .stream()
                .map { folder -> folder.displayName }
                .collect()

        then: "folders names should match expected values"
        folderCollection == ['Archive', 'Conversation History', 'Deleted Items', 'Drafts', 'Inbox', 'Junk Email', 'Outbox', 'RCT', 'Sent Items']

    }

    def "should be able to retrieve user messages from specified folder"() {
        given: "An instance of service controller"
        GraphServiceController controller = new GraphServiceController()

        when: "An attempt to get messages from folder named RCT is made"
        List<Message> messages = controller.getUserEmailsFrom("RCT")

        then: "response should not be empty"
        assert !messages.isEmpty()
    }

    def "should be able to retrieve the latest email in a folder"() {
        given: "An instance of service controller"
        GraphServiceController controller = new GraphServiceController()

        when: "An attempt to get messages from folder named RCT is made"
        List<Message> messages = controller.getUserEmailsFrom("RCT")

        then: "response should not be empty"
        assert !messages.isEmpty()
    }
}
