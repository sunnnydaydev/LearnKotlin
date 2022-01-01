package com.sunnyday.constraintlayout.learnkt.kts.standard

/**
 * Create by SunnyDay on 20:22 2022/01/01
 */

fun study1(student: Student?){
    student?.doHomework()
    student?.readBook()
    // 下面
}

fun study2(student: Student?){

    if (student!=null){
        student.doHomework()
    }
    if (student!=null){
        student.readBook()
    }
}
