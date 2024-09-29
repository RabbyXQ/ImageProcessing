package org.example

import org.example.Scale.ScaledImage
import org.example.Gray.Gray
import org.example.EdgeDetection.EdgeDetection
import org.example.ImageArray.PrintImageArray
import org.example.logtransform.Gamma
import org.example.logtransform.LogTransform
import org.example.Negative.Negative // Import your Negative class
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

fun main() {
    val inputPath: String = "/home/rabbyxq/IdeaProjects/ImageProcessing/src/images/cameloon.png"
    val scaledOutputPath: String = "/home/rabbyxq/IdeaProjects/ImageProcessing/src/images/scaled_result.png"
    val grayOutputPath: String = "/home/rabbyxq/IdeaProjects/ImageProcessing/src/images/gray_result.png"
    val logTransformOutputPath: String = "/home/rabbyxq/IdeaProjects/ImageProcessing/src/images/log_transform_result.png"
    val edgeOutputPath: String = "/home/rabbyxq/IdeaProjects/ImageProcessing/src/images/edge_result.png"
    val gammaTransformOutputPath: String = "/home/rabbyxq/IdeaProjects/ImageProcessing/src/images/gamma_transform_result.png"
    val negativeImagePath: String = "/home/rabbyxq/IdeaProjects/ImageProcessing/src/images/negative_image.png"

    val originalImage: BufferedImage = ImageIO.read(File(inputPath))

    val scaledImage: ScaledImage = ScaledImage(inputPath, 128, 128)

    ImageIO.write(scaledImage, "png", File(scaledOutputPath))

    val grayImage: Gray = Gray(scaledImage)

    ImageIO.write(grayImage, "png", File(grayOutputPath))

    val logTransformedImage: LogTransform = LogTransform(grayImage)

    ImageIO.write(logTransformedImage, "png", File(logTransformOutputPath))

    val gammaTransform: Gamma = Gamma(scaledImage, .7)

    ImageIO.write(gammaTransform, "png", File(gammaTransformOutputPath))

    val edgeImage: EdgeDetection = EdgeDetection(logTransformedImage)

    ImageIO.write(edgeImage, "png", File(edgeOutputPath))

    // Apply the Negative transformation
    val negativeImage: Negative = Negative(scaledImage)
    ImageIO.write(negativeImage, "png", File(negativeImagePath))

    val printOriginal = PrintImageArray(scaledImage)
    println("End Original")

    val printLogTransform = PrintImageArray(logTransformedImage)
    println("End of Log Transform")

    val gamma = PrintImageArray(gammaTransform)

    ImageIO.write(edgeImage, "png", File(edgeOutputPath))
    println("End of Edge Detection")

    // Print array representation of the negative image
    val printNegative = PrintImageArray(negativeImage)
    println("End of Negative Image")
}
