package org.example.Scale

import java.awt.image.BufferedImage
import javax.imageio.ImageIO
import java.io.File

class ScaledImage(
    val path: String,
    val scaledHeight: Int,
    val scaledWidth: Int
) : BufferedImage(scaledWidth, scaledHeight, BufferedImage.TYPE_INT_ARGB) {

    init {
        val originalImage: BufferedImage = ImageIO.read(File(path))
        scaleImage(originalImage)
    }

    private fun scaleImage(originalImage: BufferedImage) {
        val originalWidth = originalImage.width
        val originalHeight = originalImage.height

        for (y in 0 until scaledHeight) {
            for (x in 0 until scaledWidth) {
                val originalX = (x * originalWidth) / scaledWidth
                val originalY = (y * originalHeight) / scaledHeight

                val pixelColor = originalImage.getRGB(originalX, originalY)

                this.setRGB(x, y, pixelColor)
            }
        }
    }
}
