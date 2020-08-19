package com.example.ui.presenters
import android.graphics.drawable.Drawable
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.leanback.widget.ImageCardView
import androidx.leanback.widget.Presenter
import com.example.ui.data.entity.Movie
import com.example.ui.R
import com.squareup.picasso.Picasso
import kotlin.properties.Delegates

class CardPresenter : Presenter() {
    private var defaultCardImage: Drawable? = null
    private var defaultBackgroundColor: Int by Delegates.notNull()
    private var selectedDefaultBackgroundColor: Int by Delegates.notNull()
    val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500"

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        defaultCardImage = ContextCompat.getDrawable(parent.context, R.drawable.lb_action_bg)
        defaultBackgroundColor = ContextCompat.getColor(parent.context, R.color.default_background)
        selectedDefaultBackgroundColor =
            ContextCompat.getColor(parent.context, R.color.focus_background)

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
        Picasso.get().load(BASE_IMAGE_URL + movie.poster_path).into(cardView.mainImageView)
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
        if (selected) {
            view.setPadding(4,4,4,4)
        } else {
            view.setPadding(0,0,0,0)
        }
    }
}
