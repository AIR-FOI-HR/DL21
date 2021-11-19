package hr.foi.air.dl.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bignerdranch.expandablerecyclerview.ExpandableRecyclerAdapter
import hr.foi.air.core.entities.Discount
import hr.foi.air.dl.R
import org.jetbrains.annotations.NotNull

class StoreRecyclerAdapter(var context : Context, @NotNull parentList : List<StoreParent>) :
    ExpandableRecyclerAdapter<StoreParent, Discount, StoreViewHolder, DiscountViewHolder>(parentList) {

    override fun onCreateParentViewHolder(parentViewGroup: ViewGroup, viewType: Int): StoreViewHolder {
        val itemView : View =
            LayoutInflater.from(context).inflate(R.layout.list_item_store, parentViewGroup, false)

        return StoreViewHolder(itemView)
    }

    override fun onCreateChildViewHolder(childViewGroup: ViewGroup, viewType: Int): DiscountViewHolder {
        val itemView : View =
            LayoutInflater.from(context).inflate(R.layout.list_item_discount, childViewGroup, false)

        return DiscountViewHolder(itemView)
    }

    override fun onBindParentViewHolder(parentViewHolder: StoreViewHolder, parentPosition: Int, parent: StoreParent) {
        parentViewHolder.bindDataToView(parent)
    }

    override fun onBindChildViewHolder(childViewHolder: DiscountViewHolder, parentPosition: Int, childPosition: Int, child: Discount) {
        childViewHolder.bindDataToView(child)
    }
}
