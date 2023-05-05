package com.example.dummy

import android.content.Context
import android.content.Intent
import android.net.wifi.WifiConfiguration
import android.net.wifi.WifiManager
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.navigation.NavController
import androidx.room.Room
import com.example.dummy.databinding.ActivityMainBinding
import com.google.gson.Gson
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.URL

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var db: AppDatabase;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "databazka.db"
        ).build()

        val thread = Thread {
            while (true) {
                aktualizujUdajeNet()
                Thread.sleep(2000)
            }
        }
        thread.start()
//        binding.fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }



    }
    data class meteoStanica(val name: String, val temperature:Double, val humidity:Double)
    fun aktualizujUdajeNet(){
        var url= "http://192.168.4.1/data.json"
        var contents: String? = ""

        try {
            val conn  = URL(url).openConnection()
            conn.connectTimeout = 2000
            conn.readTimeout = 2000
            val input = conn.getInputStream()

            val bufferedReader = input.bufferedReader()
            val string :String = bufferedReader.use {it.readText()}

            val gson = Gson()
            val jsonMeteo = gson.fromJson(string, meteoStanica::class.java)
            val meteo = roomMeteo.MeteoStanica(0, jsonMeteo.name, jsonMeteo.temperature, jsonMeteo.humidity)
            val meteoDao = db.meteoStanicaDao()
            meteoDao.insert(meteo)



        } catch(ex: IOException){
            Log.println(Log.ERROR,"ch:","chyba")

            println(ex.message)
        }catch (ex2: SocketTimeoutException){
            Toast.makeText(this, "Timeout", Toast.LENGTH_SHORT).show()
        }
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        if (item.itemId == R.id.itemPripoj) {

            startActivity(Intent(Settings.Panel.ACTION_INTERNET_CONNECTIVITY))

        }
        if (item.itemId == R.id.itemGraf) {
            navController.navigate(R.id.SecondFragment);


        }

        if (item.itemId == R.id.itemStatus) {
            navController.navigate(R.id.FirstFragment);


        }

        return super.onOptionsItemSelected(item)
    }


    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}