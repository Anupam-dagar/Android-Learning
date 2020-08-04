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
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.URL

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        val addProductButton = findViewById<FloatingActionButton>(R.id.fab)
        addProductButton.setOnClickListener {
            startActivity(Intent(this, AddProductActivity::class.java))
        }

        val products = listOf(
            Product("Bread", "Anupam", 2006, 260.50),
            Product("Keyboard", "John", 2007, 500.00),
            Product("Bread", "Anupam", 2008, 12.34),
            Product("Keyboard", "John", 2009, 50.20)
        )

        var totalCost = 0.0

        products.forEach {
            totalCost += it.cost
            productsTextView.append("${it.name} - ${it.owner} - ${it.yearPurchased} - $ ${it.cost}\n\n")
        }

        totalCostTextView.text = "$ ${totalCost}"

//        val preferences = getSharedPreferences("database", Context.MODE_PRIVATE)
//        val savedName = preferences.getString("savedProductName", "This value doesn't exist")
//        d("Anupam", "Saved message is: $savedName")

//        lastSavedProduct.text = savedName

//        lifecycleScope.launch(Dispatchers.Default) {
//            val specialMessage = URL("https://finepointmobile.com/api/inventory/v1/message").readText()
//            d("Anupam", "The message is: $specialMessage")
//
//            lastSavedProduct.text = specialMessage
//        }

    }
}