package com.sunnyday.constraintlayout.learnkt.kts.standard

import java.io.*
import java.lang.StringBuilder

/**
 * Create by SunnyDay on 16:45 2022/01/08
 */

fun main() {

    val file = File("F://test.txt")
    if (!file.exists()) {
        println("file is not exists ")
        return
    }
    val ins = FileInputStream(file)
    val reader = BufferedReader(InputStreamReader(ins))
    val sb = StringBuilder()
    reader.use {

        reader.forEachLine {
            sb.append(it)
        }
    }
    println("读取文件成功，文件内容如下：\n $sb")

    // readLines() 是file的扩展函数，源码最终还是调用Reader的forEachLine
    File("F://test.txt")
        .readLines()
        .forEach {
            println("File 扩展函数读取：:$it")
        }
}