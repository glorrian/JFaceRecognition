package io.github.glorrian.utils

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.reflection.beCompanion
import io.kotest.matchers.shouldBe
import nu.pattern.OpenCV
import org.junit.jupiter.api.assertThrows
import org.opencv.core.Mat
import java.io.ByteArrayInputStream
import java.io.File
import java.nio.file.Files

class MatUtilsTest : FunSpec({
    val imagePath = "image.jpeg"

    beforeTest {
        OpenCV.loadLocally()
    }

    test("toMat(imagePath) should return a non-empty Mat") {
        val mat: Mat = MatUtils.toMat(javaClass.classLoader.getResource(imagePath)!!.path)

        mat.empty() shouldBe false
    }

    test("toMat(imageData) should return a non-empty Mat") {
        val mat: Mat = MatUtils.toMat(javaClass.classLoader.getResourceAsStream(imagePath)!!.readAllBytes())

        mat.empty() shouldBe false
    }

    test("toMat(image) should return a non-empty Mat") {
        val mat: Mat = MatUtils.toMat(javaClass.classLoader.getResourceAsStream(imagePath)!!)

        mat.empty() shouldBe false
    }

    test("toMat(imagePath) should throw error for invalid path") {
        val invalidImagePath = "invalid/path/to/image.jpg"

        val exception = assertThrows<Exception> {
            MatUtils.toMat(invalidImagePath)
        }
        exception.message shouldBe "Can't open image at path: $invalidImagePath"
    }

    test("toMat(imageData) should throw error for invalid data") {
        val invalidData = ByteArray(0) // Пустые данные

        // Проверка, что метод выбрасывает исключение для пустых данных
        val exception = assertThrows<Exception> {
            MatUtils.toMat(invalidData)
        }
        exception.message shouldBe "Failed to decode image data"
    }
})

