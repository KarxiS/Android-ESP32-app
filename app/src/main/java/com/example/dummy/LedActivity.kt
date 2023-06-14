package com.example.dummy

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.alpha
import androidx.core.graphics.blue
import androidx.core.graphics.green
import androidx.core.graphics.red
import androidx.navigation.NavController
import androidx.navigation.ui.AppBarConfiguration
import com.example.dummy.databinding.ActivityMainBinding
import com.flask.colorpicker.ColorPickerView
import okhttp3.*
import java.io.IOException


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

    /**
     * v tejto metode sa cez http3 pripojim na zariadenie, riesenie z inej triedy neslo, kde som pozival URL triedu lebo som nic neparsoval
     * v pripade pripojenia sa kontrolka na zariadeni zmeni na zelenu
     */
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

    /**
     * zmena farby  LED podla Color objektu na zariadeni
     *
     * @param color
     */
    private fun zmenFarbu(color: Int) {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("http://192.168.4.1/ledZmena1?r="+color.red+"&g="+color.green+"&b="+color.blue+"&a="+(color.alpha.toDouble()/255))
            .build()

        client.newCall(request).enqueue(object : Callback {
            /**
             * osetrenie v pripade chyby-ja ignorujem
             *
             * @param call
             * @param e
             */
            override fun onFailure(call: Call, e: IOException) {

            }

            /**
             * osetrenie v pripade odpovedi, pripojenia-ignorujem,
             *
             * @param call
             * @param response
             */
            override fun onResponse(call: Call, response: Response) {
                val responseBody = response.body?.string()

            }

    })


}
}
