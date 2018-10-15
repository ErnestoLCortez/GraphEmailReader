import com.microsoft.graph.models.extensions.User;
import msgraph.GraphServiceClientManager;

import java.io.IOException;

public class PublicClient {

    public static void main(String args[]) throws Exception {

        String userInfo = getUserInfoFromGraph();
        System.out.print(userInfo);
    }

    private static String getUserInfoFromGraph() throws IOException {

        User user = null;
        try {
            user = GraphServiceClientManager.getInstance()
                    .getGraphServiceClient()
                    .me()
                    .buildRequest()
                    .get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user.displayName;
    }
}
