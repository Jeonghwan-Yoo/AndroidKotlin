package com.example.an11_sendobject

import android.os.Parcel
import android.os.Parcelable

class TestClass : Parcelable {
    var data10:Int=0
    var data20:String?=null

    companion object { //static으로 만들기 위해서
        @JvmField //JVM에서 직접 사용할 경우에 붙여준다.
        val CREATOR : Parcelable.Creator<TestClass> = object:Parcelable.Creator<TestClass>{
            override fun createFromParcel(p0: Parcel?): TestClass { //객체를 복원하는 메소드
                val test=TestClass()
                test.data10=p0?.readInt()!!
                test.data20=p0?.readString()!!
                return test
            }

            override fun newArray(p0: Int): Array<TestClass?> { //배열을 담을 때 호출해야 함
                return arrayOfNulls<TestClass>(p0) //sizeof(p0)
            }
        }
    }

    override fun writeToParcel(p0: Parcel?, p1: Int) { //Parcel 클래스 타입의 객체를 저장하거나 전달한다.
        //순서가 중요하다.
        p0?.writeInt(data10)
        p0?.writeString(data20)
    }

    override fun describeContents(): Int {
        return 0
    }
}