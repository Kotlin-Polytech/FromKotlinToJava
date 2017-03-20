package part2.recode.java

import org.junit.Test

import org.junit.Assert.*
import java.io.File
import java.nio.charset.Charset

class RecoderTest {

    private fun assertFileContent(name: String, expectedContent: String, encoding: String) {
        val file = File(name)
        val content = file.readLines(Charset.forName(encoding)).joinToString("\n")
        assertEquals(expectedContent, content)
    }

    @Test
    fun recode() {
        val recoder = Recoder("UTF8", "CP1251")
        recoder.recode("files/text1.txt", "files/temp.txt")
        assertFileContent("files/temp.txt", "А роза упала на лапу Азора...", "CP1251")
        File("files/temp.txt").delete()
    }

    @Test
    fun recodeKotlin() {
        val recoder = part2.recode.kotlin.Recoder("UTF8", "CP1251")
        recoder.recode("files/text1.txt", "files/temp.txt")
        assertFileContent("files/temp.txt", "А роза упала на лапу Азора...", "CP1251")
        File("files/temp.txt").delete()
    }
}