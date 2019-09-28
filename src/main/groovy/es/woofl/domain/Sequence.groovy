package es.woofl.domain

class Sequence {
    String name
    // time between states in ms
    int delay
    List<State> states
    int repeat = 1
    boolean turnOffAfter = false
}
