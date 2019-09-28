package es.woofl

import io.micronaut.context.event.ApplicationEventListener
import io.micronaut.discovery.event.ServiceStartedEvent
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StartUp implements ApplicationEventListener<ServiceStartedEvent> {

    private static final Logger log = LoggerFactory.getLogger(StartUp.class)

    @Inject
    SequenceService sequenceService

    @Override
    void onApplicationEvent(ServiceStartedEvent event) {
        sequenceService.init()
    }
}
