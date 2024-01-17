package com.example.androidthreadtest

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.widget.Toast
import com.example.androidthreadtest.databinding.ActivityMainBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    val updateText = 1
    val resetProcessBar = 2
    val handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                updateText -> binding.textView.text = "Nice to meet you"
            }
        }
    }

    lateinit var downloadTask: DownloadTask

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.changeTextBtn.setOnClickListener {
            thread {
                val msg = Message()
                msg.what = updateText
                handler.sendMessage(msg)
            }
        }
        downloadTask = DownloadTask()
        binding.downloadBtn.setOnClickListener {
            downloadTask.execute()
        }
    }

    inner class DownloadTask : AsyncTask<Unit, Int, Boolean>() {

        var progress = 0
        override fun onPreExecute() {
            binding.progressBar.progress = 0
        }

        override fun doInBackground(vararg params: Unit?) = try {
            while (true) {
                val downloadPercent = doDownload()
                publishProgress(downloadPercent)
                if (downloadPercent >= 100) {
                    break
                }
            }
            true
        } catch (e: Exception) {
            false
        }

        override fun onProgressUpdate(vararg values: Int?) {
            binding.progressBar.progress = values[0] ?: 0
        }

        override fun onPostExecute(result: Boolean?) {
            if (result == true) {
                Toast.makeText(applicationContext, "Download succeeded", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(applicationContext, "Download failed", Toast.LENGTH_SHORT).show()
            }
        }

        private fun doDownload(): Int {
            progress += 10
            Thread.sleep(500)
            return progress
            // 非ui线程访问ui也会有问题
//            return binding.progressBar.progress + 10
            // 这样也不行
//            var res = 0
//            runOnUiThread {
//                res = binding.progressBar.progress + 10
//            }
//            return res
        }

    }

}