package es.woofl

import es.woofl.domain.Color
import es.woofl.domain.State
import io.micronaut.context.event.ApplicationEventListener
import io.micronaut.discovery.event.ServiceStartedEvent
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StartUp implements ApplicationEventListener<ServiceStartedEvent> {
    @Inject
    SequenceService sequenceService

    private static final Logger log = LoggerFactory.getLogger(StartUp.class)

    @Override
    void onApplicationEvent(ServiceStartedEvent event) {
        sequenceService.init()
        sequenceService.sendState(new State(color: Color.GREEN))
        sequenceService.sendState(State.off)
    }
}
