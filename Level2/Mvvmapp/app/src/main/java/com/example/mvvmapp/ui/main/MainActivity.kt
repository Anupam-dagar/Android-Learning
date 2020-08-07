package com.example.mvvmapp.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmapp.R
import com.example.mvvmapp.data.Item
import com.example.mvvmapp.utilities.InjectorUtils
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialiseUI()
    }

    private fun initialiseUI() {
        val factory = InjectorUtils.provideMainViewModelFactory()
        val viewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)

        viewModel.getItems().observe(this, Observer { items ->
            val stringBuilder = StringBuilder()
            items.forEach { item -> stringBuilder.append("$item\n") }
            itemList.text = stringBuilder.toString()
        })

        addButton.setOnClickListener {
            val item = Item(itemName.text.toString(), itemCategory.text.toString())
            viewModel.addItem(item)
            itemName.setText("")
            itemCategory.setText("")
        }
    }
}