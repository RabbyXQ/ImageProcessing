package org.example.logtransform

import java.awt.image.BufferedImage
import java.awt.Color
import kotlin.math.log10

class Gamma(
    image: BufferedImage,
    threshold: Double
) : BufferedImage(image.width, image.height, BufferedImage.TYPE_INT_ARGB) {
    val threshold = threshold;
    init {
        applyLogTransformation(image)
    }

    private fun applyLogTransformation(image: BufferedImage) {

        val gamma: Double = this.threshold;

        val c: Double = 255 / Math.pow(255.0, gamma);

        for (y in 0 until image.height) {
            for (x in 0 until image.width) {
                val rgb = image.getRGB(x, y)
                val color = Color(rgb, true)

                val red = color.red
                val green = color.green
                val blue = color.blue
                val alpha = color.alpha

                val newRed = (c * Math.pow(red.toDouble(), gamma)).toInt().coerceIn(0, 255)
                val newGreen = (c * Math.pow(green.toDouble(), gamma)).toInt().coerceIn(0, 255)
                val newBlue = (c * Math.pow(blue.toDouble(), gamma)).toInt().coerceIn(0, 255)

                val newRgb = Color(newRed, newGreen, newBlue, alpha).rgb

                this.setRGB(x, y, newRgb)
            }
        }
    }
}
