package es.woofl

import es.woofl.domain.State
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.client.BlockingHttpClient
import io.micronaut.http.client.RxHttpClient
import io.micronaut.http.client.annotation.Client

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StateSender implements Runnable {
    @Client("http://192.168.1.243/api/v1/state")
    @Inject
    RxHttpClient httpClient

    State state

    @Override
    void run() {
        BlockingHttpClient blockingClient = httpClient.toBlocking()
        HttpResponse response = blockingClient.exchange(
                HttpRequest.POST("/", state).contentType(MediaType.APPLICATION_JSON)
        )
    }
}
