import com.microsoft.graph.models.extensions.Message;
import com.microsoft.graph.models.extensions.User;
import com.microsoft.graph.requests.extensions.IMailFolderCollectionPage;
import com.microsoft.graph.requests.extensions.IMessageCollectionPage;
import com.microsoft.graph.requests.extensions.MailFolderCollectionPage;
import msgraph.GraphServiceClientManager;
import msgraph.GraphServiceController;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class PublicClient {

    public static void main(String args[]) throws Exception {

        GraphServiceController controller = new GraphServiceController();
        List<Message> userEmails = controller.getUserEmailsFrom("RCT");
        System.out.print(userEmails);
    }

}
