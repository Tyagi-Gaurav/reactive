package org.gt.shipping;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;

import java.io.IOException;
import java.nio.charset.Charset;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = {CustomerApplication.class})
public abstract class BaseFunctionalTest {

    @LocalServerPort
    protected int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    private ResponseResult lastResponse;
    private ObjectMapper objectMapper = new ObjectMapper();

    protected void executeGet(String url, RequestCallback requestCallback) {
        ResponseExtractor<ResponseResult> responseExtractor = ResponseResult::new;
        lastResponse = testRestTemplate.execute(url,
                HttpMethod.GET,
                requestCallback,
                responseExtractor);
    }

    protected ResponseResult getLastResponse() {
        return lastResponse;
    }

    public class ResponseResult {
        private final String body;
        private ClientHttpResponse response;

        ResponseResult(ClientHttpResponse response) throws IOException {
            this.response = response;
            this.body = StreamUtils.copyToString(response.getBody(), Charset.defaultCharset());
        }

        public int getStatusCode() throws IOException {
            return response.getRawStatusCode();
        }

        public boolean isError() throws IOException {
            return response.getRawStatusCode() >= 400;
        }

        public <T> T getBody(Class<T> clazz) throws IOException {
            return objectMapper.readValue(body, clazz);
        }
    }
}
