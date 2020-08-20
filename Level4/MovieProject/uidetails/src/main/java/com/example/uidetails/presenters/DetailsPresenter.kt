package com.example.uidetails.presenters

import android.util.Log
import androidx.leanback.widget.AbstractDetailsDescriptionPresenter
import com.example.base.data.entity.Movie

class DetailsPresenter : AbstractDetailsDescriptionPresenter() {

    override fun onBindDescription(viewHolder: AbstractDetailsDescriptionPresenter.ViewHolder, itemData: Any) {
        val details = itemData as Movie

        viewHolder.title.text = details.title
        viewHolder.subtitle.text = "${details.popularity}   ${details.release_date}"
        viewHolder.body.text = details.overview
    }
}
