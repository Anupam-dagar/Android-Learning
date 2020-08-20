package com.example.uidetails.presenters

import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.leanback.widget.Presenter

class ActionsPresenter(private var onButtonClickListener: OnButtonClickListener, var addToFavourites: Boolean): Presenter(){

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val buttonView = Button(parent.context).apply {
            tag = "favouriteButton"
            isFocusable = true
        }

        return ViewHolder(buttonView, onButtonClickListener)
    }

    override fun onBindViewHolder(viewHolder: Presenter.ViewHolder?, item: Any?) {

        (viewHolder?.view as Button).text = item.toString()
    }

    override fun onUnbindViewHolder(viewHolder: Presenter.ViewHolder?) {
    }

    inner class ViewHolder(view: View, onButtonClickListener: OnButtonClickListener) :
        Presenter.ViewHolder(view){
        val button = view.findViewWithTag<Button>("favouriteButton")
        init {
            button.setOnClickListener{
                onButtonClickListener.onButtonClick(addToFavourites, button)
            }
        }
    }

    interface OnButtonClickListener {
        fun onButtonClick(addToFavourites: Boolean, button: Button)
    }
}
