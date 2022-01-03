package com.sunnyday.constraintlayout.learnkt.kts.standard

/**
 * Create by SunnyDay on 10:29 2022/01/03
 */

fun <T,R>T.customLet(block: T.()->R):R{
    return block()
}


fun main(){
    // 测试自定义
    val str = "name"

    str.customLet {
        println(this)
    }
}
