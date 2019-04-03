package com.igorvd.chuckfacts.data.testutils

import java.io.*
import java.lang.Exception


/**
 * Loads the given json into a String. The json file must exists in the /tests/resources directory
 */
fun loadJsonFromResources(classLoader: ClassLoader, fileName: String): String {

    val url = classLoader.getResource(fileName)
    val file = File(url.path)
    return file.readContent()

}

/**
 * Reads the data for the given file
 */
fun File.readContent(): String {

    var content = ""
    try {
        val inputStream = FileInputStream(this)

        val inputStreamReader = InputStreamReader(inputStream)
        val bufferedReader = BufferedReader(inputStreamReader)
        var receiveString = bufferedReader.readLine()

        while (receiveString != null) {
            content += receiveString
            receiveString = bufferedReader.readLine()
        }
        inputStream.close()


    } catch (e: Exception) {
    }

    return content
}