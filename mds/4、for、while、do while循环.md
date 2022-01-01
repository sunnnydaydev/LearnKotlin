# 循环

#### 1、 for

常见写法

```kotlin
 // 循环语句
    val list = arrayListOf<String>("a","b","c ")

    //1、for (int i = 0; i < list.size(); i++) 按照索引（从0开始） 递增遍历 写法
     for(i in list.indices){
       println(list[i])
     }

     // 2、for (int i = 2; i < list.size(); i++) 按照索引（从索引2开始） 递增遍历 写法

    for(i in 2..(list.size-1)){
        println(list[i])
    }

    //3、 (int i = list.size() - 1; i >= 0; i--) 按照索引的递减顺序写法
    for (i in list.size - 1 downTo 0) {
        println(list[i])
    }

    // 4、 类似 foreach
    for (item in list){
        println(item)
    }
```

> [其他for写法参考](<https://www.jianshu.com/p/1ea733ea197d>)



#### 2、while      do  while

> 和java一致