package com.sunnyday.constraintlayout.learnkt.kts.standard

import java.lang.StringBuilder

/**
 * Create by SunnyDay on 13:51 2022/01/02
 */

fun testWith(){
   val result = with(StringBuilder()){
       append("Http")
       append(":")
       append("//")
       append("www.baidu.com")
       toString()
   }
}

fun  testRun(){

    val result = StringBuilder().run {
        append("Http")
        append(":")
        append("//")
        append("www.baidu.com")
        toString()
    }
}



fun  testApply(){

    val result = StringBuilder().apply {
        append("Http")
        append(":")
        append("//")
        append("www.baidu.com")
    }
    val str = result.toString()
}
