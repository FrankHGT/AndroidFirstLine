package com.example.activitytest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.widget.Button

class ThirdActivity : BaseActivity() {

    companion object {
        private val MESSAGE_KILL_PROCESS = 1
    }

    private val tag = "ThirdActivity"

    private val mHandler: Handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                ThirdActivity.MESSAGE_KILL_PROCESS -> {
                    android.os.Process.killProcess(android.os.Process.myPid())
                }
                else -> {
                    Log.e(tag, "message not matched $msg")
                }
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(tag, "Task id is $taskId")
        setContentView(R.layout.third_layout)

        val button3: Button = findViewById(R.id.button3)

        button3.setOnClickListener {
            ActivityCollector.finishAll()
            // 加上杀进程之后，会有类似闪退的效果，所以延时1s再杀，或者在onDestroy里面kill，动画上也没事
//            mHandler.sendEmptyMessageDelayed(ThirdActivity.MESSAGE_KILL_PROCESS, 1000)

//            android.os.Process.killProcess(android.os.Process.myPid())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
//        android.os.Process.killProcess(android.os.Process.myPid())
    }
}