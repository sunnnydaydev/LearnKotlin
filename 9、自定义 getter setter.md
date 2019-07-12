自定义 getter setter

```java

package myinterface

/**
 * Created by sunnyDay on 2019/7/12 18:53
 *
 */
class User() {
    // 给address 重写setter属性
    var address: String = ""
        set(value){ // set 换行
         if (value> 0.toString()){
           field =value+"我是后缀"
        }
    }
   val name:String
    get()="自定义getter"  // get换行
}

fun main(){
    val user = User()
    user.address = "sdfasf"
    println(user.address) // log:sdfasf我是后缀

    println(user.name) // log:自定义getter
}
```

> ps:
>
> 1、get() set()的书写格式
>
> 2、重写set时 filed、value字段是 set（）定义的。我们只需使用操作表达式即可。