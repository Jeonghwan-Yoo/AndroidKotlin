package com.example.an1_helloworld

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var text2:TextView?=null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("abcd","on create")

        text2=findViewById<TextView>(R.id.textView2)
        text2?.text="문자열2"

        textView3.text="문자열3"

    }

    override fun onStart() {
        super.onStart()
        Log.d("abcd","on start")
    }

    override fun onResume() {
        super.onResume()
        Log.d("abcd","on resume")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("abcd","on restart")
    }

    override fun onPause() {
        super.onPause()
        Log.d("abcd","on pause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("abcd","on stop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("abcd","on destroy")
    }
}
