package hr.foi.air.core.data

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.fragment.app.Fragment
import hr.foi.air.core.entities.Discount
import hr.foi.air.core.entities.Store

interface DataPresenter {
    fun getIcon(context: Context) : Drawable
    fun getName(context: Context) : String
    fun getFragment() : Fragment
    fun setData(stores: List<Store>?, discounts: List<Discount>?)
}