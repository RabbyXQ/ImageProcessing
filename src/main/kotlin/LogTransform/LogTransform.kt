package org.example.logtransform

import java.awt.image.BufferedImage
import java.awt.Color
import kotlin.math.log10

class LogTransform(
    image: BufferedImage
) : BufferedImage(image.width, image.height, BufferedImage.TYPE_INT_ARGB) {

    init {
        applyLogTransformation(image)
    }

    private fun applyLogTransformation(image: BufferedImage) {
        val maxPixelValue = 255.0
        val c = maxPixelValue / log10(1 + maxPixelValue)

        for (y in 0 until image.height) {
            for (x in 0 until image.width) {
                val rgb = image.getRGB(x, y)
                val color = Color(rgb, true)

                val red = color.red
                val green = color.green
                val blue = color.blue
                val alpha = color.alpha

                val newRed = (c * log10(1 + red.toDouble())).toInt().coerceIn(0, 255)
                val newGreen = (c * log10(1 + green.toDouble())).toInt().coerceIn(0, 255)
                val newBlue = (c * log10(1 + blue.toDouble())).toInt().coerceIn(0, 255)

                val newRgb = Color(newRed, newGreen, newBlue, alpha).rgb

                this.setRGB(x, y, newRgb)
            }
        }
    }
}
