package io.github.glorrian.utils

import org.opencv.core.Mat
import org.opencv.core.MatOfByte
import org.opencv.imgcodecs.Imgcodecs
import java.io.InputStream

class MatUtils {
    companion object {
        fun toMat(imagePath: String): Mat {
            return Imgcodecs.imread(imagePath)
        }

        fun toMat(imageData: ByteArray): Mat {
            return Imgcodecs.imdecode(MatOfByte(*imageData), Imgcodecs.IMREAD_UNCHANGED)
        }

        fun toMat(image: InputStream): Mat {
            val bytes = image.readBytes()
            return toMat(bytes)
        }
    }
}
