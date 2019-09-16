package com.example.an6_asynctask

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener { view->
            var time=System.currentTimeMillis()
            textView.text="버튼 클릭 : ${time}"
        }

        var sync=AsyncTaskClass()
        sync.execute(10,20)
    }
    inner class AsyncTaskClass : AsyncTask<Int,Long,String>(){ //앞에 Int는 exucute의 매개변수 타입이자 doInBackground 매개변수 타입이다. 배열로 p0
        override fun onPreExecute() {
            super.onPreExecute()
            textView2.text="AsyncTask 가동"
        }

        override fun doInBackground(vararg p0: Int?): String { //p0는 null허용
            var a1=p0[0]!! //10, 0을 허용해선 안된다. 더하기 뺴기 연산을 하기 위해서
            var a2=p0[1]!! //20

            for(idx in 0..9){ //0부터 9까지 배열
                SystemClock.sleep(1000)

                a1++
                a2++
                Log.d("Test1","${idx} : ${a1}, ${a2}")
                //textView2.text="${idx} : ${a1}, ${a2}"
                var time=System.currentTimeMillis()
                publishProgress(time) //Long type의 배열
            }
            return "수행이 완료되었습니다"
        }

        override fun onProgressUpdate(vararg values: Long?) {
            super.onProgressUpdate(*values)
            textView2.text="Async : ${values[0]}"
        }

        override fun onPostExecute(result: String?) { //doInBackground의 반환 타입
            super.onPostExecute(result)
            textView2.text=result
        }
    }
}
