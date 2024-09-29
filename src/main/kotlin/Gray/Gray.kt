package org.example.Gray

import java.awt.image.BufferedImage
import java.awt.Color
import java.awt.image.BufferedImage.TYPE_INT_ARGB

class Gray(
    image: BufferedImage
) : BufferedImage(image.width, image.height, TYPE_BYTE_GRAY) {

    init {
        convertToGray(image)
    }

    private fun convertToGray(image: BufferedImage) {
        for (y in 0 until image.height) {
            for (x in 0 until image.width) {
                val rgb = image.getRGB(x, y)

                val color = Color(rgb, true)
                val red = color.red
                val green = color.green
                val blue = color.blue
                val alpha = color.alpha

                val grayValue = (red+green+blue)%255.toInt()

                val newRgb = Color(grayValue, grayValue, grayValue, alpha).rgb

                this.setRGB(x, y, newRgb)
            }
        }
    }
}
