package com.example.uidetails.presenters

import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.leanback.widget.Presenter
import com.example.uidetails.R

class StringPresenter : Presenter() {

    override fun onCreateViewHolder(parent: ViewGroup): Presenter.ViewHolder {
        val textView = TextView(parent.context).apply {
            isFocusable = true
            isFocusableInTouchMode = true
            background = ResourcesCompat.getDrawable(resources, R.drawable.city, context.theme)
        }
        return Presenter.ViewHolder(textView)
    }

    override fun onBindViewHolder(viewHolder: Presenter.ViewHolder, item: Any) {
        (viewHolder.view as TextView).text = item.toString()
    }

    override fun onUnbindViewHolder(viewHolder: Presenter.ViewHolder) {
        // no op
    }
}
