package com.example.an5_threadhandler

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.os.SystemClock
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var isRunning:Boolean=false
    var handler:DisplayHandler?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener { view->
            var time=System.currentTimeMillis()
            textView.text="버튼 클릭 : ${time}"
        }

        handler=DisplayHandler()

        isRunning=true
        var thread=ThreadClass()
        thread.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        isRunning=false
    }
    inner class ThreadClass : Thread(){
        override fun run() {

            var a1=10
            var a2=20

            while (isRunning) {
                SystemClock.sleep(100)
                var time = System.currentTimeMillis()
                Log.d("test1", "쓰래드:${time}")
                //textView2.text = "쓰레드:${time}" //8.0이상에서는 개발자가 발생시킨 일반 쓰레드에서 화면처리 가능
                //handler?.sendEmptyMessage(0) //Main Thread가 한가할 때 handleMessage 호출
//                var msg=Message()
//                msg.what=0 //what은 작업을 나눌 때 사용
//                msg.obj=time //자동으로 객체로 박싱, 값을 무한정 넘길 수 있다.
//                handler?.sendMessage(msg) //화면에 표시

                var msg2=Message()
                msg2.what=1
                msg2.arg1=++a1 //int
                msg2.arg2=++a2 //int
                msg2.obj="안녕하세요" //객체
                handler?.sendMessage(msg2)
            }
        }
    }
    //위에 msg2와 아래 msg랑 혼돈하지 말기
    inner class DisplayHandler: Handler(){
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)

            if(msg?.what==0){
                //var time=System.currentTimeMillis()
                textView2.text="Handler : ${msg?.obj}"
            }
            else if(msg?.what==1){
                textView2.text="${msg?.arg1}, ${msg?.arg2}, ${msg?.obj}"
            }
        }
    }
}
