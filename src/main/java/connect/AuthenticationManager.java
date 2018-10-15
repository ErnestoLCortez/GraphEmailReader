package connect;

import com.microsoft.aad.adal4j.AuthenticationContext;
import com.microsoft.aad.adal4j.AuthenticationResult;

import javax.naming.ServiceUnavailableException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static connect.Constants.*;

public class AuthenticationManager {

    private static AuthenticationManager INSTANCE;
    public AuthenticationResult authResult;

    private AuthenticationManager() {
    }

    public static synchronized AuthenticationManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AuthenticationManager();
        }
        return INSTANCE;
    }

    public AuthenticationManager connect() throws ServiceUnavailableException {
        try {
            authResult = getAuthResultFromUserCredentials();
        } finally {

        }
        return this;
    }

    public String getAccessToken() throws ServiceUnavailableException{
        if (authResult == null) {
            connect();
        }
        return authResult.getAccessToken();
    }

    private static AuthenticationResult getAuthResultFromUserCredentials() throws ServiceUnavailableException {
        AuthenticationContext context;
        AuthenticationResult result = null;
        ExecutorService service = null;
        try {
            service = Executors.newFixedThreadPool(1);
            context = new AuthenticationContext(AUTHORITY, false, service);
            Future<AuthenticationResult> future = context.acquireToken(
                    "https://graph.microsoft.com", CLIENT_ID, USER_NAME, USER_PASS,
                    null);
            result = future.get();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            service.shutdown();
        }

        if (result == null) {
            throw new ServiceUnavailableException(
                    "authentication result was null");
        }
        return result;
    }
}
