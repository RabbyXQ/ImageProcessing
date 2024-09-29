package org.example.Negative

import java.awt.Color
import java.awt.image.BufferedImage

class Negative(image: BufferedImage) : BufferedImage(image.width, image.height, image.type) {
    init {
        val height = image.height
        val width = image.width

        for (y in 0 until height) {
            for (x in 0 until width) {
                // Get the color of the pixel from the original image
                val color = Color(image.getRGB(x, y), true)

                // Create the negative color by subtracting from 255
                val negativeColor = Color(
                    255 - color.red,
                    255 - color.green,
                    255 - color.blue,
                    color.alpha // Preserve alpha channel
                )

                // Set the negative color to the current BufferedImage
                this.setRGB(x, y, negativeColor.rgb)
            }
        }
    }
}
