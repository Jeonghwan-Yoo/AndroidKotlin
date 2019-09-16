package com.example.grammar

import android.view.MotionEvent
import android.view.View

fun variable(){
    val variable1 : Double=123.5 //64
    val variable2 : Float=123.5f //32
    val variable3 : Int=123 //32
    val variable4 : Short=123 //16
    val variable5 : Byte=123 //8
    val variable6 : String="a"

    //타입을 안써도 된다.
    val variable_1=123.5
    val variable_2=123.5f
    val variable_3=123
    val variable_4=123
    val variable_5=123
    val variable_6="a"

    //val variable=null은 불가능
    val variable7:Nothing?=null
    //Void variable8=null

    var vari="a" //mutable read/write getter/setter 자동
    val vari2="a" //inmutable read only getter 자동

    var name:String="" //이것을 만들게 되면 그아래 다 생성
    /*public final class KotlinSampleKt{
        private static String name="";
        //getter
        public static final String getName(){
            return name;
        }
        //setter
        public static final void setName(@NotNull String var0){
            Intrinsics.checkParameterIsNotNull(var0,"<set-?>");
            name=var0;
        }
    }
    */

    //모든 변수는 null을 허용하지 않는다.
    //허용하려면 ?를 추가해야 한다.
    var var_null : String?="a"
    var_null=null

    //java의 instanceof 대신에 as is를 제공
    //as:값의 casting
    //is:값이 맞는지 체크
    val a:Any?="ABC"
    var b:Int?=a as? Int //초기화 코드가 없어서 null
    println("Out b $b") //Out b null
    /* 똑같이 길어진다
    if(b==null){
        b=0
    }
    println("Out b $b") //Out b 0
    */
    //Elvis Operator
    var _b:Int?=a as? Int ?:0 //a가 Int냐 아니면(else) 0을 리턴해라

    val c:Any?="ABC"
    var d:Int?=0
    if(c is Int){
        d=c as? Int
    }
    println("Out d $d") //Out b 0

    //3항식은 코틀린에서는 없다.
    val l: Int=if(b!=null) b.length else -1
    val _l=b?.length ?:-1

    /*example
    fun foo(node:Node):String?{
        val parent=node.getParent()?:
                return null
        val name=node.getName()?:
                throw IllegalArgumentException("name expected")...
    }
    */
    //정말 null이 필요한 경우
    val __l=b!!.length

    //is와 when을 활용시 if 문을 줄일 수 있다 switch가 없다.
    val aa:Any?="ABC" //Any=Object
    when(a){
        is Int->println(a)
        is String->println(a)
        else->println("nothing")
    }
}

//arguments에 default를 줄 수 있다.
//default value정의한 생성자를 java에서 부르려면, @JvmOverloads을 적용
class KotlinSample @JvmOverloads constructor(val name:String, val name2:String="", val age:Int){ //public을 따로 쓰지 않는다.
    //1개의 메인 생성자와 n개의 보조 생성자가있다
    //primary constructor를 호출하지 않으면 오류가 발생
    //secondary constructor에서는 전역변수를 정의할 수 없다.
    //private var name:String=""

    init{ //이것을 먼저 실행하게 된다. primary->코틀린에서는 primary constructor에게 파라미터 몰아주자.
        println("name $name name $name2 age $age")
    }
    constructor(name:String,name2:String):this(name,name2,0)

    private fun aaa(){
        println("name $name name2 $name2, age $age")
    }
}

class UnitTest{
     fun test(){
         KotlinSample(name="a",age=0)
     }
}

//상속
//Default class 모든 class/function은 final 재정의 불가능
//재정의 하려면 open 키워드를 추가해야함

//상속의 정의는 콜론(:)으로 한다.
//다중 상속 정의는 콤마(,)로 한다.
//abstract와 interface 상속 순서가 변경되어도 상관없음.

//interface
//default정의 가능
//변수도 선언만 가능(상속에서 구현해야 함)
//companion object를 이용하면 내부에 static class를 추가로 생성하고, 활용할 수 있다.
interface InterfaceKotlinSample{
    companion object{
        const val type:Int=0
    }
    fun init(){

    }
    fun out()
}
//변수를 java 처럼 사용하려고 하면 오류 발생 사실 변수가아니라 함수로 만들어짐
//val type=0

//상속시
class Inheritance:
    InterfaceKotlinSample{

    override fun out(){

    }
}

//다중 상속
interface AA{
    fun init(){
        println("InterfaceKotlinSample - init")
    }
}
abstract class BB{
    open fun init(){
        println("AbstractKotlinSample - init")
    }
}
//둘다 골라 쓸수있다. 자바와 다르게
class CC : AA, BB(){
    override fun init(){
        super<AA>.init()
        super<BB>.init()
    }
}

//data class
data class User(val id:Long=0,val name:String?=null,val email:String?=null)

//Sealed class:다향성을 쉽게 하기위해서
sealed class Expr
data class Const(val number:Double):Expr()
data class Sum(val e1:Expr,val e2:Expr):Expr()
object NotANumber : Expr()

fun eval(expr:Expr):Double=when(expr){
    is Const->expr.number
    is Sum->eval(expr.e1)+eval(expr.e2)
    NotANumber->Double.NaN
    //the 'else' clause is not required because we've convered all the cases
}

fun test(){
    println(eval(Sum(Const(0.0),Const(1.0))))
}

//singleton
object Eager

//Lambda function안에 익명 function
/*
btn.setOnTouchListener{view:View?,motionEvent:
MotionEvent?->
    Log.i("SampleActivity","motionEvent ${motionEvent?.action}")
    return @setOnTouchListener false
}
//kotlin에서는 파라미터 타입을 생략가능
btn.setOnTouchListener{view,motionEvent->
    Log.i("SampleActivity","motionEvent ${motionEvent?.action}")
    return @setOnTouchListener false
}
//return 명시도 하지 않을 수 있음
btn.setOnTouchListener{view,motionEvent->
    Log.i("SampleActivity","motionEvent ${motionEvent?.action}")
    false
}
//파라미터가 1개면 it을 사용할 수 있다.
btn.setOnClickListener{
    Log.d("SampleActivity","view $it")
}
*/

//Higher-Order Functions
//함수를 파라미터로 전달, 함수 return
//lambda를 이용하여 축약 형태로 제공
//변수로 함수를 가질 수 있다.
//함수를 넘겨줄때 default는 쓸 수 없다.
private var onClick:((view: View)->
Unit)?=null //Unit=Void

fun setOnClick(
    onClick:(view:View)->Unit){
    this.onClick=onClick
}

//example
fun higherOrder(body:(Int,Int)->Int)=body(20,10)
fun sum(a:Int,b:Int)=a+b
fun minus(a:Int,b:Int)=a-b
fun multiply(a:Int,b:Int)=a*b
fun division(a:Int,b:Int)=a/b
//::는 묶어주는 역할을 한다.
fun test1(){
    println(higherOrder(::sum)) //30
    println(higherOrder(::minus)) //10
    println(higherOrder(::multiply)) //200
    println(higherOrder(::division)) //2

}