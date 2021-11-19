package hr.foi.air.dl.recyclerview

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bignerdranch.expandablerecyclerview.ParentViewHolder
import com.squareup.picasso.Picasso
import hr.foi.air.core.entities.Discount
import hr.foi.air.core.entities.Store
import hr.foi.air.dl.R
import org.jetbrains.annotations.NotNull

class StoreViewHolder(@NotNull itemView: View) : ParentViewHolder<StoreParent, Discount>(itemView) {
    var storeName = itemView.findViewById<TextView>(R.id.store_name)
    var storeDesc = itemView.findViewById<TextView>(R.id.store_desc)
    var storeImage = itemView.findViewById<ImageView>(R.id.store_image)

    fun bindDataToView(store: Store)
    {
        storeName?.text = store.name
        storeDesc?.text = store.description
        Picasso.with(itemView.context).load(store.imgUrl).into(storeImage)
    }
}
