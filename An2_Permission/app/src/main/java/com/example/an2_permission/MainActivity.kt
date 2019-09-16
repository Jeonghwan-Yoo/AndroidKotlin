package com.example.an2_permission

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var permission_list=arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.READ_CONTACTS,
        Manifest.permission.WRITE_CONTACTS,
        Manifest.permission.SEND_SMS,
        Manifest.permission.RECEIVE_SMS
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkPermission()
    }

    fun checkPermission(){
        if(Build.VERSION.SDK_INT< Build.VERSION_CODES.M){ //마시멜로보다 낮으면 메소드 종료
            return;
        }
        for(permission:String in permission_list){
            var chk=checkCallingOrSelfPermission(permission) //활성화 상태였는지 아니였는지 확인

            if(chk==PackageManager.PERMISSION_DENIED){ //활성이 안됏었으면
                requestPermissions(permission_list,0);
                break;
            }
        }
    }
    //이 메소드를 호출하면 물어보는 창이 사라질때 자동으로 호출
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        var idx=0;

        textView.text=""

        for(idx in grantResults.indices){ //index번호가 하나 씩 들어가게 된다.
            if(grantResults[idx]==PackageManager.PERMISSION_GRANTED){
                textView.append("${permission_list[idx]}:허용함\n")
            }
            else{
                textView.append("${permission_list[idx]}:허용하지 않음\n")
            }
        }
    }
}
