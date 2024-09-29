package org.example.EdgeDetection

import java.awt.Color
import java.awt.image.BufferedImage
import java.awt.image.BufferedImage.TYPE_INT_ARGB

class EdgeDetection(
    image: BufferedImage
) : BufferedImage(image.width, image.height, TYPE_INT_ARGB) {

    init {
        applySobelFilter(image)
    }

    private fun applySobelFilter(image: BufferedImage) {
        val sobelX = arrayOf(
            intArrayOf(-1, 0, 1),
            intArrayOf(-2, 0, 2),
            intArrayOf(-1, 0, 1)
        )

        val sobelY = arrayOf(
            intArrayOf(1, 2, 1),
            intArrayOf(0, 0, 0),
            intArrayOf(-1, -2, -1)
        )

        for (y in 1 until image.height - 1) {
            for (x in 1 until image.width - 1) {
                var gx = 0
                var gy = 0

                // Apply Sobel filter
                for (ky in -1..1) {
                    for (kx in -1..1) {
                        val pixelColor = image.getRGB(x + kx, y + ky)
                        val grayValue = Color(pixelColor, true).red // Assuming grayscale

                        gx += grayValue * sobelX[ky + 1][kx + 1]
                        gy += grayValue * sobelY[ky + 1][kx + 1]
                    }
                }

                // Calculate the magnitude of the gradient
                val magnitude = Math.sqrt((gx * gx + gy * gy).toDouble()).toInt().coerceIn(0, 255)

                // Set the new pixel value using the Color class
                val newColor = Color(magnitude, magnitude, magnitude)
                this.setRGB(x, y, newColor.rgb)
            }
        }

        // Handle the edges by setting them to black or any other color
        val blackColor = Color(0, 0, 0)
        for (y in 0 until image.height) {
            this.setRGB(0, y, blackColor.rgb) // left edge
            this.setRGB(image.width - 1, y, blackColor.rgb) // right edge
        }
        for (x in 0 until image.width) {
            this.setRGB(x, 0, blackColor.rgb) // top edge
            this.setRGB(x, image.height - 1, blackColor.rgb) // bottom edge
        }
    }
}
