package com.example.dummy

import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.alpha
import androidx.core.graphics.blue
import androidx.core.graphics.green
import androidx.core.graphics.red
import androidx.navigation.NavController
import androidx.navigation.ui.AppBarConfiguration
import com.example.dummy.databinding.ActivityMainBinding
import com.flask.colorpicker.ColorPickerView
import com.flask.colorpicker.OnColorSelectedListener
import com.flask.colorpicker.builder.ColorPickerClickListener
import com.flask.colorpicker.builder.ColorPickerDialogBuilder
import com.google.android.material.snackbar.Snackbar
import okhttp3.*
import java.io.IOException
import java.net.HttpURLConnection
import java.net.SocketTimeoutException
import java.net.URL


class LedActivity : AppCompatActivity() {
    private var currentBackgroundColor = Color.WHITE
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_led)

        val colorPickerView = findViewById<ColorPickerView>(R.id.color_picker_view)
        colorPickerView.addOnColorSelectedListener { selectedColor ->
            zmenFarbu(selectedColor)
        }
    this.pripojSa();



    }

    private fun pripojSa(){

        val client = OkHttpClient()
        val request = Request.Builder()
            .url("http://192.168.4.1/ledZmena1?r=0&g=255&b=0&a=1")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {

            }

            override fun onResponse(call: Call, response: Response) {


            }
        })


    }

    private fun zmenFarbu(color: Int) {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("http://192.168.4.1/ledZmena1?r="+color.red+"&g="+color.green+"&b="+color.blue+"&a="+(color.alpha.toDouble()/255))
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {

            }

            override fun onResponse(call: Call, response: Response) {
                val responseBody = response.body?.string()

            }

    })


}
}
