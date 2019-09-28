package es.woofl

import es.woofl.domain.Color
import es.woofl.domain.State
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Produces

import javax.inject.Inject

@Controller
class AlarmController {
    @Inject
    SequenceService sequenceService

    @Get("/")
    @Produces(MediaType.APPLICATION_JSON)
    String index() {
        sequenceService.sendState(new State(color: Color.RED))
        sequenceService.sendState(State.off)
        return HttpResponse.ok()
    }
}
