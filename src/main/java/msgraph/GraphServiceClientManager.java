/*
 * Copyright (c) Microsoft. All rights reserved. Licensed under the MIT license.
 * See LICENSE in the project root for license information.
 */
package msgraph;


import com.microsoft.graph.authentication.IAuthenticationProvider;
import com.microsoft.graph.core.ClientException;
import com.microsoft.graph.core.DefaultClientConfig;
import com.microsoft.graph.core.IClientConfig;
import com.microsoft.graph.http.IHttpRequest;
import com.microsoft.graph.logger.LoggerLevel;
import com.microsoft.graph.models.extensions.IGraphServiceClient;
import com.microsoft.graph.requests.extensions.GraphServiceClient;
import connect.AuthenticationManager;

/**
 * Singleton class that manages a GraphServiceClient object.
 * It implements the IAuthentication provider interface necessary to
 * insert the access token obtained in {@link AuthenticationManager} class
 * into REST requests in the Java Graph SDK.
 */
public class GraphServiceClientManager implements IAuthenticationProvider {
    private IGraphServiceClient mGraphServiceClient;
    private static GraphServiceClientManager INSTANCE;

    private GraphServiceClientManager() {
    }

    public static synchronized GraphServiceClientManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new GraphServiceClientManager();
        }
        return INSTANCE;
    }

    /**
     * Appends an access token obtained from the {@link AuthenticationManager} class to the
     * Authorization header of the request.
     *
     * @param request
     */
    @Override
    public void authenticateRequest(IHttpRequest request) {
        try {
            request.addHeader("Authorization", "Bearer "
                    + AuthenticationManager
                    .getInstance()
                    .connect()
                    .getAccessToken());
        } catch (ClientException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized IGraphServiceClient getGraphServiceClient() {
        return getGraphServiceClient(this);
    }

    /**
     * Provides a new instance of the Microsoft Graph SDK client on first request.
     *
     * @param authenticationProvider The interface that exposes an access token getter. Getter is called with each HTTP operation
     * @return
     */
    public synchronized IGraphServiceClient getGraphServiceClient(IAuthenticationProvider authenticationProvider) {
        if (mGraphServiceClient == null) {
            IClientConfig clientConfig = DefaultClientConfig.createWithAuthenticationProvider(
                    authenticationProvider
            );
            clientConfig.getLogger().setLoggingLevel(LoggerLevel.DEBUG);
            mGraphServiceClient = GraphServiceClient.fromConfig(clientConfig);
        }

        return mGraphServiceClient;
    }
}
