package com.example.uidetails.presenters

import androidx.leanback.widget.AbstractDetailsDescriptionPresenter
import com.example.uidetails.data.entity.Movie

class DetailsPresenter : AbstractDetailsDescriptionPresenter() {

    override fun onBindDescription(viewHolder: AbstractDetailsDescriptionPresenter.ViewHolder, itemData: Any) {
        val details = itemData
        // In a production app, the itemData object contains the information
        // needed to display details for the media item:
        // viewHolder.title.text = details.shortTitle

        // Here we provide static data for testing purposes:
        viewHolder.apply {
            title.text = itemData.toString()
            subtitle.text = "2014   Drama   TV-14"
            body.text = ("Lorem ipsum dolor sit amet, consectetur "
                    + "adipisicing elit, sed do eiusmod tempor incididunt ut labore "
                    + " et dolore magna aliqua. Ut enim ad minim veniam, quis "
                    + "nostrud exercitation ullamco laboris nisi ut aliquip ex ea "
                    + "commodo consequat.")
        }
    }
}
