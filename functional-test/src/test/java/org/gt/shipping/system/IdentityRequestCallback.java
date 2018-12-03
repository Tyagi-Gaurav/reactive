package org.gt.shipping.system;

import org.springframework.http.client.ClientHttpRequest;
import org.springframework.web.client.RequestCallback;

import java.io.IOException;

public class IdentityRequestCallback implements RequestCallback {
    @Override
    public void doWithRequest(ClientHttpRequest request) throws IOException {
        //Do Nothing
    }
}
