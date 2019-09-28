package es.woofl

import es.woofl.domain.Sequence
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
        Sequence sequence = sequenceService.findByName("wakeup")
        if (sequence) {
            sequenceService.run(sequence)
            return HttpResponse.ok()
        }
        HttpResponse.notFound()
    }
}
