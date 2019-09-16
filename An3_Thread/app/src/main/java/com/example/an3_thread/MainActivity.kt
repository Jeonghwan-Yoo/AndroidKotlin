package com.example.an3_thread

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var isRunning=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener { view->
            var now=System.currentTimeMillis() //현재 시간을 milisec으로 가져올 수 있다.
            textView.text="버튼 클릭 : ${now}"
        }
        //무한 루프 때문에 화면에 아무것도 나타나지 않게 된다. 쓰래드 운영이 필요하다.
        /*while(true){
            var now=System.currentTimeMillis()
            textView2.text="무한 루프 : ${now}"
        }
        */
        isRunning=true
        var thread=ThreadClass1()
        thread.start()
    }

    inner class ThreadClass1 : Thread(){
        override fun run() {
            while(isRunning){
                SystemClock.sleep(100) //100ms으로 쉰다.
                var now=System.currentTimeMillis()
                Log.d("test1","쓰레드 : ${now}")
                //8.0이상만 화면에 관련된 처리 가능하다.
                textView2.text="쓰레드 : ${now}"
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        isRunning=false
    }
}
