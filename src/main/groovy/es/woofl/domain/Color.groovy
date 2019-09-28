package es.woofl.domain

import com.fasterxml.jackson.annotation.JsonFormat

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
enum Color {
    WHITE(255, 255, 255),
    RED(255, 0, 0),
    GREEN(0, 255, 0),
    BLUE(0, 0, 255);

    String mode = "rgb"
    int r, g, b = 0

    Color(int r, int g, int b) {
        this.r = r
        this.g = g
        this.b = b
    }
}
