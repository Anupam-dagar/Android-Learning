package com.example.uidetails.presenters

import android.content.Intent
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.leanback.widget.OnItemViewClickedListener
import androidx.leanback.widget.Presenter
import androidx.leanback.widget.Row
import androidx.leanback.widget.RowPresenter
import com.example.base.data.entity.Movie
import com.example.uidetails.R
import com.example.uidetails.activities.DetailActivity

class ActionsPresenter : Presenter() {

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val buttonView = Button(parent.context).apply {
            isFocusable = true
            setOnClickListener(ButtonClickedListener())
        }

        return ViewHolder(buttonView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, item: Any) {
        (viewHolder.view as Button).text = item.toString()
    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder) {
    }

    inner class ButtonClickedListener: View.OnClickListener {
        override fun onClick(p0: View?) {
            Log.d("clicked", "$p0")
        }
    }
}
