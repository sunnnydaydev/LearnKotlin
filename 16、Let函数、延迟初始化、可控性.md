### 一、Let函数

###### 1、语法

```java
let{
    //todo
}
```

###### 2、使用

> 用处有两处
>
> 1、在函数体内使用it替代类的对象去访问其公有的属性和方法
>
> 2、与？.一起使用，表示对象不为null时执行let方法体
>
> ps:详情参考下面栗子

```java
/**
 * Created by sunnyDay on 2019/8/19 16:31
 *
 */
fun main() {
      val xiaoHua = Stu()
      // case 1
      println(xiaoHua.let { it.getStuname() }) // it 代表xiaoHua这个对象
      // case 2
      println(xiaoHua?.let { "i am not empty" }) // xiaoHua 不为null时执行
}

class Stu {
    val name = "sunnyday"
    fun getStuname(): String ? {
        return name
    }
}
```

