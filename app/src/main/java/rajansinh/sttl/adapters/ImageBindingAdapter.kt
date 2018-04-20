package rajansinh.sttl.adapters

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.squareup.picasso.Picasso


/**
 * Created by rajan.bhavsar on 4/5/2018.
 */
object ImageBindingAdapter {

    @JvmStatic
    @BindingAdapter("bind:imageUrl")
    fun loadImage(imageView: ImageView, url: String) {
        if (url != "") {
            Picasso.with(imageView.getContext()).load("https://image.tmdb.org/t/p/w500/" + url).resize(200, 200).into(imageView)
        }
    }
}