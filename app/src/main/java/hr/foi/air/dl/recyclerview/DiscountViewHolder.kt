package hr.foi.air.dl.recyclerview

import android.view.View
import android.widget.TextView
import androidx.annotation.NonNull
import com.bignerdranch.expandablerecyclerview.ChildViewHolder
import hr.foi.air.core.entities.Discount
import hr.foi.air.dl.R

class DiscountViewHolder: ChildViewHolder<Discount> {

    var discountName: TextView? = null
    var discountDesc: TextView? = null
    var discountValue: TextView? = null

    constructor(@NonNull itemView: View): super(itemView){
        discountName = itemView.findViewById(R.id.discount_name)
        discountDesc = itemView.findViewById(R.id.discount_desc)
        discountValue = itemView.findViewById(R.id.discount_value)
    }

    public fun bindDataToView(discount: Discount){
        discountName?.text = discount.name
        discountDesc?.text = discount.description
        discountValue?.text = (discount.discountValue).toString()
    }

}