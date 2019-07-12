```java


package myinterface

/**
 * Created by sunnyDay on 2019/7/12 14:13
 *kotlin中的关键字：
 * 1、 open public private protected
 * 2、 java类方法 默认是开放的，子类默认可以继承重写父类，或者父类方法，kotlin的默认是final的
 * 3、final和open关键字的功能相反（open标注的就是可继承、可重写）
 * 4、子类中重写基类的方法或者成员后，子类内新的方法或者成员默认权限是 open 我们可以通过关键字修改
 *
 * 注意：1、接口和接口中的方法默认是  开放的（open）继承后也是open
 *       2、抽象类同接口（抽象类，和抽象方法 默认 public open）
 *       3、抽象类中的非抽象函数并不是open  默认final
 */
class KeyWord : Button() { // 普通类默认public final 类型,

    fun getKeyWorld(): String = "我是关键字"  // 默认 方法public final

    override fun doClick() {} // 接口中的方法还是可以重写的

    override fun getButtonName(): String {
        return "重写啦 父类方法，父类的方法需要加open 关键字"
    }
}


interface Click {

    fun doClick()
    fun test(){
        println("我是Click-test()默认实现")
    }


}
```

