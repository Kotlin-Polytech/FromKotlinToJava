package part2.recode.kotlin

import java.io.*

class Recoder(private val charsetInput: String, private val charsetOutput: String) {

    @Throws(IOException::class)
    fun recode(`in`: InputStream, out: OutputStream): Int {
        InputStreamReader(`in`, charsetInput).use { reader ->
            OutputStreamWriter(out, charsetOutput).use { writer ->
                var sym = reader.read()
                var count = 0
                while (sym != -1) {
                    writer.write(sym)
                    count++
                    sym = reader.read()
                }
                return count
            }
        }
    }

    @Throws(IOException::class)
    fun recode(inputName: String, outputName: String): Int {
        FileInputStream(inputName).use { inputStream ->
            FileOutputStream(outputName).use { outputStream ->
                return recode(inputStream, outputStream)
            }
        }
    }
}
