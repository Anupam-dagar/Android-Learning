package com.example.inventorymanagement

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log.d
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        val gotoButton = findViewById<Button>(R.id.goToAddProduct)
        gotoButton.setOnClickListener {
            startActivity(Intent(this, AddProductActivity::class.java))
        }

        val preferences = getSharedPreferences("database", Context.MODE_PRIVATE)
        val savedName = preferences.getString("savedProductName", "This value doesn't exist")
        d("Anupam", "Saved message is: $savedName")

        lastSavedProduct.text = savedName
    }
}