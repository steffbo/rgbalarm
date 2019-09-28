package es.woofl

import com.fasterxml.jackson.databind.ObjectMapper
import es.woofl.domain.Color
import es.woofl.domain.Sequence
import es.woofl.domain.State
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SequenceService {
    private static final Logger log = LoggerFactory.getLogger(SequenceService.class)

    @Inject
    StateSender stateSender

    List<Sequence> sequences = []

    Sequence findByName(String name) {
        sequences.find { it.name == name } ?: null
    }

    void run(Sequence sequence) {
        // run the sequence
        sequence.repeat.times {
            sequence.states.each { State state ->
                log.info("Sending state: ${new ObjectMapper().writeValueAsString(state)}")
                sendState(state)
            }
        }

        // finish
        if (sequence.turnOffAfter) {
            log.info("turning off")
            sendState(State.off)
        } else {
            log.info("keep turned on")
        }
    }

    /**
     * Sends an async http request to the RGB thingy with the given State
     * @param state
     */
    void sendState(State state) {
        stateSender.state = state
        stateSender.run()
    }

    void init() {
        wakeUp()
        disco()
    }


    private void wakeUp() {
        Sequence wakeUp = new Sequence(name: "wakeup", delay: 2500)
        List<State> states = []
        100.times {
            states << new State(brightness: (it + 1))
        }
        wakeUp.states = states
        sequences << wakeUp
    }

    private void disco() {
        Sequence sq = new Sequence(name: "disco", delay: 2500, repeat: 2, turnOffAfter: true)
        List<State> states = []
        [Color.RED, Color.GREEN, Color.BLUE].each {
            states << new State(color: it)
        }
        sq.states = states
        sequences << sq
    }
}
