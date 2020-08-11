package com.example.tvfoodapp.ui.Presenters

import android.graphics.drawable.Drawable
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.leanback.widget.ImageCardView
import androidx.leanback.widget.Presenter
import com.example.tvfoodapp.R
import com.example.tvfoodapp.model.Movie
import kotlin.properties.Delegates

class CardPresenter : Presenter() {
    private var defaultCardImage: Drawable? = null
    private var defaultBackgroundColor: Int by Delegates.notNull()
    private var selectedDefaultBackgroundColor: Int by Delegates.notNull()

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        defaultCardImage = ContextCompat.getDrawable(parent.context, R.drawable.city)
        defaultBackgroundColor = ContextCompat.getColor(parent.context, R.color.default_background)
        selectedDefaultBackgroundColor =
            ContextCompat.getColor(parent.context, R.color.selected_background)

        val cardview = object : ImageCardView(parent.context) {
            override fun setSelected(selected: Boolean) {
                updateBackgroundColor(this, selected)
                super.setSelected(selected)
            }
        }

        cardview.isFocusable = true
        cardview.isFocusableInTouchMode = true
        updateBackgroundColor(cardview, false)

        return ViewHolder(cardview)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, item: Any) {
        val movie = item as Movie
        val cardView = viewHolder.view as ImageCardView

        cardView.titleText = movie.title
        cardView.contentText = movie.overview
        cardView.setMainImageDimensions(313, 176)
        cardView.mainImageView.setImageResource(R.drawable.city)
    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder) {
        val cardView = viewHolder.view as ImageCardView

        cardView.badgeImage = null
        cardView.mainImage = null
    }

    private fun updateBackgroundColor(view: ImageCardView, selected: Boolean) {
        val color = if (selected) selectedDefaultBackgroundColor else defaultBackgroundColor
        view.setBackgroundColor(color)
        view.setInfoAreaBackgroundColor(color)
    }
}