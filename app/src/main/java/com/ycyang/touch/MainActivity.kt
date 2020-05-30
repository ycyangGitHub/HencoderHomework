package com.ycyang.touch

import android.animation.TypeEvaluator
import android.app.Activity
import android.graphics.PointF
import android.os.Bundle
import android.view.View
import androidx.viewpager.widget.ViewPager
import com.ycyang.drawpractice.R

private val provinces = listOf("北京市",
        "天津市",
        "上海市",
        "重庆市",
        "河北省",
        "山西省",
        "辽宁省",
        "吉林省",
        "黑龙江省",
        "江苏省",
        "浙江省",
        "安徽省",
        "福建省",
        "江西省",
        "山东省",
        "河南省",
        "湖北省",
        "湖南省",
        "广东省",
        "海南省",
        "四川省",
        "贵州省",
        "云南省",
        "陕西省",
        "甘肃省",
        "青海省",
        "台湾省",
        "内蒙古自治区",
        "广西壮族自治区",
        "西藏自治区",
        "宁夏回族自治区",
        "新疆维吾尔自治区",
        "香港特别行政区",
        "澳门特别行政区")

open class MainActivity : Activity() {

    final override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.drag_listener_practice)
//       val textView= findViewById<TextView>(R.id.textView)
//        textView.post {  }
//        textView.setOnClickListener{
//            thread {
//                textView.text = "1123444"
//                textView.post {  }
//            }
//        }

        //        dashboardPractice = findViewById(R.id.dashboardview);
//        findViewById(R.id.sport);
//        findViewById(R.id.mutil);
//        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(findViewById(R.id.cicle_animator), "radius", 50f, 200f);
//        objectAnimator.setDuration(2000);
//        objectAnimator.setStartDelay(2000);
//        objectAnimator.start();
//        new NewActivity();
//        int[] target = new int[]{1, 2, 3, 4, 5};
////        List<Integer> result = Arrays.asList(1, 2, 3, 4, 5);
////        List<Integer> end = result.stream().map(n->n*n).collect(Collectors.toList());
//        int[] outputStream = Arrays.stream(target).map(n -> n * n).toArray();
//        double doub = Stream.of(-1.5, 2, 3, 4).reduce(Double.MAX_VALUE, Double::min);
//        Stream.of(1,2,3).parallel();
//        int interger = 1;
//        Integer.valueOf(interger);
//        new Integer(3).intValue();
//        new Student("ycyang", 1);
//        new Thread(() -> {
//            int a = c;
//            int b = 4;
//            int d = a + b;
//
//        }).start();
//        val flipView = findViewById<View>(R.id.flip)
//        val bottomFlipAnimator = ObjectAnimator.ofFloat(flipView, "bottomFlip", 60f)
//        bottomFlipAnimator.startDelay = 1000
//        bottomFlipAnimator.duration = 1000
//
//        val flipRotationAnimator = ObjectAnimator.ofFloat(flipView, "rotateY", 0f, 270f)
//        flipRotationAnimator.startDelay = 500
//        flipRotationAnimator.duration = 2000
//        val topFlipAnimator = ObjectAnimator.ofFloat(flipView, "rotateX", -60f)
//        topFlipAnimator.startDelay = 300
//        topFlipAnimator.duration = 1000
//        val animatorSet = AnimatorSet()
//        animatorSet.playSequentially(bottomFlipAnimator, flipRotationAnimator, topFlipAnimator)
//        animatorSet.start()
//        val bot = PropertyValuesHolder.ofFloat("bottomFlip", 0f, 60f)
////        val flip = PropertyValuesHolder.ofFloat("rotateY", 0f, 270f)
////        val rot = PropertyValuesHolder.ofFloat("rotateX", -60f)
////
////        val objectAnimator = ObjectAnimator.ofPropertyValuesHolder(flipView, bot, flip, rot)
////        propertyValuesHolder.duration = 2000
////        propertyValuesHolder.startDelay = 1000
////        propertyValuesHolder.start()

//        val keyframe1 = Keyframe.ofObject(0f,PointF(0f, 0f))
//        val keyframe2 = Keyframe.ofObject(0.2f, PointF(100f, 200f))
//        val keyframe3 = Keyframe.ofObject(0.8f, PointF(600f, 600f))
//        val keyframe4 = Keyframe.ofObject(1f, PointF(700f, 700f))

//        var interpolator = Path()
//        interpolator.lineTo(0.25f, 0.25f)
//        interpolator.lineTo(0.4f, 0.5f)
//        interpolator.lineTo(0.6f, 0.6f)
//        interpolator.lineTo(1f, 1f)
//        var circleView = findViewById<View>(R.id.point)
//        var skipCircle = ObjectAnimator.ofObject(circleView, "skipPoint", Evaluator(), PointF(200.dp, 100.dp))
//        skipCircle.duration = 2000
//        skipCircle.startDelay = 2000
//
//        skipCircle.setInterpolator(PathInterpolator(interpolator))
//
//        skipCircle.start()

//        var provinceView = findViewById<View>(R.id.province)
//        var textObjectAnimator = ObjectAnimator.ofObject(provinceView,"provice",StringEvaluator(),"北京市")
//        textObjectAnimator.setDuration(4000)
//        textObjectAnimator.start()


    }

//    class Evaluator : TypeEvaluator<PointF> {
//        override fun evaluate(fraction: Float, startValue: PointF, endValue: PointF): PointF {
//            return PointF(startValue.x + (endValue.x - startValue.x) * fraction, startValue.y + (endValue.y - startValue.y) * fraction)
//        }
//    }
//
//    class StringEvaluator : TypeEvaluator<String> {
//        override fun evaluate(fraction: Float, startValue: String?, endValue: String?): String {
//            val startIndex = provinces.indexOf(startValue)
//            val endIndex = provinces.indexOf(endValue)
//            val currentIndex = startIndex + ((endIndex - startIndex) * fraction).toInt()
//            return provinces[currentIndex]
//        }
//
//    }
//
//
//}
//
//open class MyActivity : MainActivity {
//    private var a = 1
//
//    class User {
//        var name = "ycyang"
//        var password = "yyyy"
//
//        init {
//            INSTANCE
//        }
//    }
//
//    private var newObject = object : A() {
//
//    }
//
//    companion object {
//        private var INSTANCE = MyActivity()
//
//    }
//
//    var list = object : View.OnClickListener {
//        private var g = 1
//
//        override fun onClick(v: View?) {
//            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//        }
//
//    }
//
//    private constructor() {
//        val user = User()
//        user.password = "3333"
//        newObject
////        user = User()
//    }
//
//    fun a() {
//        INSTANCE.a
//        list.g
//    }
//}
//
//open class A {
//    companion object {
//        private var activity = Activity()
//    }
//
//    var a = object : A() {
//        private var c = "dddd"
//        activity
//    }
//
//    class B{
//       private var d = "1111"
//        init{
//            activity
//        }
//    }
//
//    inner class C{
//        private var g = "333"
//        init{
//            activity
//        }
//    }
//
//
//    init {
//        a.c
//        var b = B()
//        b.d
//        var c = C()
//        c.g
//    }
//}
//
//open class B(var name: String, var password: String) {
//
//    constructor(a: String, b: String, c: String) : this(a, b) {
//        when {
//            1 == 2 -> {
//                arrayOf("1")
//
//            }
//            else -> {
//
//            }
//        }
//    }
//
//    var listener = object : View.OnClickListener {
//        override fun onClick(v: View?) {
//            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//        }
//
//    }
//    var nameTest = "ycyang"
//    var passwordTest = "yyy"
//
//    var l = object : B(nameTest, passwordTest) {
//
//    }
//    var ca = object : ViewPager.SimpleOnPageChangeListener() {
//
//    }
//
//    override fun toString(): String {
//        return super.toString()
//    }
//
//    override fun hashCode(): Int {
//        return super.hashCode()
//    }
//
//    override fun equals(other: Any?): Boolean {
//        return super.equals(other)
//    }


}


