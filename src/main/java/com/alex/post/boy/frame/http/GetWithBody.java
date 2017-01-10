package com.alex.post.boy.frame.http;

import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;

/**
 * @author Aliaksandr_Shynkevich
 */
public class GetWithBody extends HttpEntityEnclosingRequestBase {

    private HttpMethod method;

    public GetWithBody(HttpMethod method) {
        this.method = method;
    }

    @Override
    public String getMethod() {
        return method.name();
    }
}
