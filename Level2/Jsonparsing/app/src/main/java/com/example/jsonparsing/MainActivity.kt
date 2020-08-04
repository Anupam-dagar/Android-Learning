package com.example.jsonparsing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.recycler_layout.*
import org.json.JSONException
import org.json.JSONObject
import java.io.IOError
import java.io.IOException
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {

    private var JsonData = mutableListOf<JsonData>()
    private var personNames = mutableListOf<String>()
    private var emailIds = mutableListOf<String>()
    private var mobileNumbers = mutableListOf<String>()
    private var jsonstring = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = layoutManager

        try {
            val obj = JSONObject(loadJSON())

            val userArray = obj.getJSONArray("users")
            jsonstring = userArray.toString()
            Log.d("jsonstring", "$jsonstring")
            for(i in 0 until userArray.length()) {
                val userDetail = userArray.getJSONObject(i)

                personNames.add(userDetail.getString("name"))
                emailIds.add(userDetail.getString("email"))

                val contact = userDetail.getJSONObject("contact")
                mobileNumbers.add(contact.getString("mobile1"))

            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        val gson = GsonBuilder().create()
        var jsonuserdata = gson.fromJson<ArrayList<JsonData>>(jsonstring, JsonData::class.java)
        Log.d("abc", "${jsonuserdata}")

        val jsonadapter = JsonDataAdapter(this@MainActivity, personNames, emailIds, mobileNumbers)
        recyclerView.adapter = jsonadapter
    }

    private fun loadJSON(): String? {
        var json: String? = null
        val charset: Charset = Charset.defaultCharset()
        try {
            val file = assets.open("data.json")
            val size = file.available()
            val buffer = ByteArray(size)
            file.read(buffer)
            file.close()
            json = String(buffer, charset)
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
        return json
    }
}