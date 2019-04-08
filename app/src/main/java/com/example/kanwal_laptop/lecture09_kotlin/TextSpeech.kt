package com.example.kanwal_laptop.lecture09_kotlin

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.speech.tts.TextToSpeech
import android.view.View
import kotlinx.android.synthetic.main.activity_text_speech.*
import java.util.*

class TextSpeech : AppCompatActivity() {
    private val REQ_CODE : Int =123
    private lateinit var tts : TextToSpeech
    private val myCurrentLanguage : Locale = Locale.US

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_speech)

        tts = TextToSpeech(this, TextToSpeech.OnInitListener {status ->
            if(status != TextToSpeech.ERROR){
                tts.language = myCurrentLanguage
            }
        })
    }

    fun stt_function(view : View){
        val i = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, Locale.getDefault())
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        i.putExtra(RecognizerIntent.EXTRA_PROMPT, "Say Something!")
        startActivityForResult(i, REQ_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            REQ_CODE -> if (resultCode === Activity.RESULT_OK && data != null) {
                val list = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                if (list!!.isEmpty() || list == null) {
                    return
                } else {
                    display_text.setText(list[0])
                }
            }
        }
    }


    fun tts_function(view : View){
        val speech = textToSpeechET.getText().toString()
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            tts.speak(speech, TextToSpeech.QUEUE_FLUSH, null)
        } else {
            tts.speak(speech, TextToSpeech.QUEUE_FLUSH, null, null)
        }
    }


    override fun onPause() {
        super.onPause()
        tts.stop()
        tts.shutdown()
    }

    override fun onStop() {
        super.onStop()
        tts.stop()
        tts.shutdown()
    }
}
