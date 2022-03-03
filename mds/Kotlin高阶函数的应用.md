# Kotlin高阶函数应用

学过高阶函数后发现还没怎么在项目中使用过，刚好最近碰到了这里小结一下~

###### 场景1：实现类似接口回调的功能

接口的回调在开发中屡见不鲜，接下来就结合自定义view模拟回顾下~ 

```java
<?xml version="1.0" encoding="utf-8"?>
<androidx.appcompat.widget.LinearLayoutCompat xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    >

    <androidx.appcompat.widget.AppCompatTextView
        android:id="@+id/btnLeft"
        android:layout_width="100dp"
        android:layout_height="100dp"
        android:background="@color/purple_700"
        android:gravity="center"
        android:text="Custom1"
        android:textStyle="bold"
        tools:ignore="HardcodedText"
        />

    <androidx.appcompat.widget.AppCompatTextView
        android:id="@+id/btnRight"
        android:layout_width="100dp"
        android:layout_height="100dp"
        android:background="@color/cardview_dark_background"
        android:gravity="center"
        android:text="Custom2"
        android:textStyle="bold"
        tools:ignore="HardcodedText"
        />
</androidx.appcompat.widget.LinearLayoutCompat>
```

```java
/**
 * Created by SunnyDay on 2022/3/3 17:37:44
 */
class CustomView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayoutCompat(context, attrs, defStyleAttr) {

    private var custom1: View
    private var custom2: View
    private var mRoot: View =
        LayoutInflater.from(context).inflate(R.layout.layout_custom_view, this, true)
    private lateinit var mOnButtonSelectListener: OnButtonSelectListener

    init {
        custom1 = mRoot.findViewById(R.id.btnLeft)
        custom2 = mRoot.findViewById(R.id.btnRight)
        initData()
    }

    private fun initData() {
        custom1.setOnClickListener {
            mOnButtonSelectListener.onSelected(StateInfo.BTN_LEFT)
        }
        custom2.setOnClickListener {
            mOnButtonSelectListener.onSelected(StateInfo.BTN_RIGHT)
        }
    }

    /**
     * set Button Select Listener
     * */
    fun setOnButtonSelectListener(onButtonSelectListener: OnButtonSelectListener) {
        mOnButtonSelectListener = onButtonSelectListener
    }

    interface OnButtonSelectListener {
        fun onSelected(stateInfo: StateInfo)
    }

    enum class StateInfo {
        BTN_LEFT, BTN_RIGHT
    }
}
```

好了，一个自定义View就完工了，接下来看看接口回调的效果：

```java
class MainActivity : AppCompatActivity() {
    companion object{
        const val TAG = "MainActivity"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // test interface callback
        customView.setOnButtonSelectListener(object : CustomView.OnButtonSelectListener {
            override fun onSelected(stateInfo: CustomView.StateInfo) {
                if (CustomView.StateInfo.BTN_LEFT==stateInfo){
                   Log.d(TAG,"选中左侧按钮~")
                }else if (CustomView.StateInfo.BTN_RIGHT==stateInfo){
                    Log.d(TAG,"选中右侧按钮~")
                }
            }
        })
    }
}
```



如上测试代码，使用接口回调很容易获取到按钮改变的状态。然而kotlin中来实现这个功能使用高阶函数同样能达到这个效果。接下来我们修改代码：

```java
/**
 * Created by SunnyDay on 2022/3/3 17:37:44
 */
class CustomView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayoutCompat(context, attrs, defStyleAttr) {

    private var custom1: View
    private var custom2: View
    private var mRoot: View =
        LayoutInflater.from(context).inflate(R.layout.layout_custom_view, this, true)

    // 定义个成员变量，为高阶函数类型。初始值为lambda匿名函数{}
    var onSelect: (StateInfo) -> Unit = {}

    init {
        custom1 = mRoot.findViewById(R.id.btnLeft)
        custom2 = mRoot.findViewById(R.id.btnRight)
        initData()
    }

    private fun initData() {
        // 事件触发时，执行高阶函数。
        custom1.setOnClickListener {
            onSelect.invoke(StateInfo.BTN_LEFT)
        }
        // 事件触发时，执行高阶函数。
        custom2.setOnClickListener {
            onSelect.invoke(StateInfo.BTN_RIGHT)
        }
    }

    enum class StateInfo {
        BTN_LEFT, BTN_RIGHT
    }
}
```

同样的功能，高阶函数方式实现就完工了，看看测试效果：

```java
class MainActivity : AppCompatActivity() {
    companion object {
        const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // test function
        customView.apply {
            onSelect = {//这里Lambda中具有CustomView.StateInfo 上下文
                if (CustomView.StateInfo.BTN_LEFT == it) {
                    Log.d(TAG, "选中左侧按钮~")
                } else if (CustomView.StateInfo.BTN_RIGHT == it) {
                    Log.d(TAG, "选中右侧按钮~")
                }
            }
        }

    }
}
```

注意点：

（1）invoke方法执行的参数与高阶函数接受的参数类型是一致的。

```java
//这里定义onSelect: (StateInfo)定义了一参数，那么方法执行时传递符合的参数即可。
var onSelect: (StateInfo) -> Unit = {} //定义1
...
onSelect.invoke(StateInfo.BTN_LEFT) // 执行1
  
//这里定义onSelect: ()定义无参数，那么方法执行时无需传递参数。
var onSelect: (StateInfo) -> Unit = {} //定义2
...
onSelect.invoke() // 执行2
```

（2）高阶函数有一个参数时lambda可以不声明参数，lambda 参数默认为高阶函数那一个参数类型。

```java
 var onSelect: (StateInfo) -> Unit = {}//lambda 中参数默认StateInfo
 // 完整写法是这样的：
 var onSelect: (StateInfo) -> Unit = {it:StateInfo->单行表达式时}
 var onSelect: (StateInfo) -> Unit = {it:StateInfo->{多行表达式时}}
   
```

（3）当高阶函数有多个参数时lambda 需要声明全部的参数。

```java
//如 onSelect定义了两个参数，lambda要定义两个参数。   
var onSelect: (StateInfo,Int) -> Unit = {it:StateInfo,a:Int->{}}
```

###### 场景2：sp 优化

###### 场景3：ContentValue

[高阶函数&Lambda回顾可参考这里](https://github.com/sunnnydaydev/LearnKotlin/blob/master/mds/18、高阶函数.md)