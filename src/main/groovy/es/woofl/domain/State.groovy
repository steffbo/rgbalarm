package es.woofl.domain

class State {
    String state = "ON"
    String mode = "SOLID"
    int brightness = 100
    Color color = Color.WHITE

    static State getOff() {
        return new State(state: "OFF")
    }
}
