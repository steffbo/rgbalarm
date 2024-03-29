package es.woofl

import es.woofl.domain.Sequence
import es.woofl.domain.State
import io.micronaut.scheduling.annotation.Scheduled
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WakeUpJob {
    @Inject
    SequenceService sequenceService

    private static final Logger LOG = LoggerFactory.getLogger(WakeUpJob.class);

    @Scheduled(cron = "0 40 6 ? * MON")
    void wakeUp() {
        LOG.info("wake up at SUN");
        Sequence sq = sequenceService.findByName("wakeup")
        sequenceService.run(sq)
    }

    @Scheduled(cron = "0 15 7 ? * MON")
    void wakeUpOff() {
        LOG.info("wakeUp off");
        sequenceService.sendState(State.off)
    }

//    @Scheduled(cron = "0 * * ? * *")
    void disco() {
        LOG.info("*** DISCO ***");
        Sequence sq = sequenceService.findByName("disco")
        sequenceService.run(sq)
    }
}
