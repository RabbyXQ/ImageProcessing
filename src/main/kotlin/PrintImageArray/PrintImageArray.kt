package org.example.ImageArray

import java.awt.Color
import java.awt.image.BufferedImage

class PrintImageArray (
    image: BufferedImage
):BufferedImage(128, 128, TYPE_INT_ARGB) {

    init {
        val height = image.height
        val width = image.width

        val matrix = Array(height) { IntArray(width) }

        for (y in 0 until height) {
            for (x in 0 until width) {
                matrix[y][x] = image.getRGB(x, y)
            }
        }

        val output = BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB)

        for (y in 0 until height) {
            for (x in 0 until width) {

                val col = Color(matrix[x][y])

                val r = col.red
                val g = col.green
                val b = col.blue

                print("[ " + r.toString() + " " + g.toString()+ " " + b.toString()+" ]")
                println("")
            }
            println("")
        }
    }


}