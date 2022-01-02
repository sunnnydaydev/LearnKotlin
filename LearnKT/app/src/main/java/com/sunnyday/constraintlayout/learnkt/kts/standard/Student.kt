package com.sunnyday.constraintlayout.learnkt.kts.standard

/**
 * Create by SunnyDay on 20:20 2022/01/01
 */

class Student(private var name: String?) {

    fun doHomework() {}

    fun readBook() {}

    fun changeName() {
//        if (name != null) {
//            val code = name!!.substring(0)
//            val length = name!!.length
//        }
        name?.let {
            val code = it.substring(0)
            val length = it.length
        }
    }

}