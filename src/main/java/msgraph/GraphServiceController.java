package msgraph;

import com.microsoft.graph.models.extensions.Attachment;
import com.microsoft.graph.models.extensions.IGraphServiceClient;
import com.microsoft.graph.models.extensions.Message;
import com.microsoft.graph.models.extensions.User;
import com.microsoft.graph.requests.extensions.IMailFolderCollectionPage;
import exception.FolderNotFound;

import java.util.List;
import java.util.Optional;

public class GraphServiceController {

    private final IGraphServiceClient mGraphServiceClient;

    public GraphServiceController() {
        mGraphServiceClient = GraphServiceClientManager.getInstance().getGraphServiceClient();
    }

    public String getUserInfoFromGraph() {

        User user = null;
        try {
            user = mGraphServiceClient
                    .me()
                    .buildRequest()
                    .get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user.displayName;
    }

    public IMailFolderCollectionPage getUserFolders() {
        return mGraphServiceClient
                .me()
                .mailFolders()
                .buildRequest()
                .get();
    }

    public List<Message> getUserEmailsFrom(String folderName) {
        return mGraphServiceClient
                .me()
                .mailFolders()
                .byId(getFolderID(folderName))
                .messages()
                .buildRequest()
                .get()
                .getCurrentPage();
    }

    private String getFolderID(String folderName) {
        Optional<String> folderIDContainer = getUserFolders().getCurrentPage().stream()
                .filter(folder -> folder.displayName.equals(folderName))
                .map(folder -> folder.id)
                .findAny();

        if (!folderIDContainer.isPresent()) {
            throw new FolderNotFound(String.format("Folder named %s not found.", folderName));
        }

        return folderIDContainer.get();
    }
}
