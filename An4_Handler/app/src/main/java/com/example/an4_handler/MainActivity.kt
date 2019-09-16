package com.example.an4_handler

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.SystemClock
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var handler : Handler?=null //Handler 객체를 담을 변수

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {view->
            var time=System.currentTimeMillis()
            textView.text="버튼 클릭 : ${time}"
        }

        //객체 생성
        handler=Handler()

        //쉴 틈이 없어서 아무것도 나오지 않게 된다.
//        while(true){
//            SystemClock.sleep(100)
//            var time=System.currentTimeMillis()
//            textView2.text="버튼 클릭 : ${time}"
//        }
        var thread=ThreadClass()
        //handler?.post(thread) //post는 android os가 한가할 때 바로 처리하게 된다.
        handler?.postDelayed(thread,100)
    }
    inner class ThreadClass : Thread(){
        override fun run() {
            //5초 미만으로 하기
            var time=System.currentTimeMillis()
            textView2.text="Handler : ${time}"

            //handler?.post(this)
            handler?.postDelayed(this,100)
        }

    }

}
