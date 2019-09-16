package com.example.an9_onactivityresult

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val SECOND_ACTIVITY=1
    val THIRED_ACTIVITY=2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button2.setOnClickListener { view->
            var intent= Intent(this,SecondActivity::class.java)
            //startActivity(intent)
            startActivityForResult(intent,SECOND_ACTIVITY)
        }
        button4.setOnClickListener { view->
            var intent=Intent(this,ThirdActivity::class.java)
            startActivityForResult(intent,THIRED_ACTIVITY)
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) { //requestCode로 어디서 왔는지 알 수 있음, resultCode로 작업이 어떻게 됐는지 확인함
        super.onActivityResult(requestCode, resultCode, data)

        when(requestCode){
            SECOND_ACTIVITY->{
                textView2.text="Second Activity에서 돌아옴\n"
                when(resultCode){
                    Activity.RESULT_OK->{
                        textView2.append("RESULT_OK")
                    }
                    Activity.RESULT_CANCELED->{ //back button
                        textView2.append("RESULT_CANCELED")
                    }
                    Activity.RESULT_FIRST_USER->{
                        textView2.append("RESULT_FIRST_USER")
                    }
                }
            }
            THIRED_ACTIVITY->{
                textView2.text="Third Activity에서 돌아옴"
            }
        }

    }
}
