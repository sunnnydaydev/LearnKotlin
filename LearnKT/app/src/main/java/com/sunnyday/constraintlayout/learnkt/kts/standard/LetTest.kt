package com.sunnyday.constraintlayout.learnkt.kts.standard

/**
 * Create by SunnyDay on 20:22 2022/01/01
 */

fun study1(student: Student?) {
    student?.doHomework()
    student?.readBook()
}


/**
 * kt中的?.可使用if语句替换。
 * 对study1中的代码进行“精确”替换。
 * */
fun study2(student: Student?) {

    if (student != null) {
        student.doHomework()
    }
    if (student != null) {
        student.readBook()
    }
}


fun study3(student: Student?) {
    // 使用if 语句对study2 进行优化
    if (student != null) {
        student.doHomework()
        student.readBook()
    }
    // 使用let函数对study2进行优化

    student?.let {
        it.doHomework()
        it.readBook()
    }
}
