package hr.foi.air.dl.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import hr.foi.air.core.entities.Discount
import hr.foi.air.core.entities.Store
import hr.foi.air.dl.databinding.FragmentListViewBinding
import hr.foi.air.dl.recyclerview.StoreParent
import hr.foi.air.dl.recyclerview.StoreRecyclerAdapter
import hr.foi.air.dl.repository.LoadDataListener

class ListViewFragment : Fragment(), LoadDataListener {
    private lateinit var binding : FragmentListViewBinding
    private var discounts: List<Discount>? = null
    private var stores: List<Store>? = null
    private var dataReadyFlag: Boolean = false
    private var viewReadyFlag: Boolean = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentListViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewReadyFlag = true
        tryToDisplayData()
    }

    override fun onDataLoaded(stores: List<Store>?, discounts: List<Discount>?) {
        this.stores = stores
        this.discounts = discounts
        dataReadyFlag = true
        tryToDisplayData()
    }

    private fun tryToDisplayData()
    {
        if (dataReadyFlag && viewReadyFlag)
        {
            if (stores != null) {
                val parentList : ArrayList<StoreParent> = ArrayList()
                for (s in stores!!)
                    parentList.add(StoreParent(s, discounts!!))

                //prikaz podataka
                binding.mainRecycler.adapter = StoreRecyclerAdapter(requireContext(), parentList)
                binding.mainRecycler.layoutManager = LinearLayoutManager(context)

                //hiding empty message
                if (stores!!.isNotEmpty())
                    binding.emptyMessage.isVisible = false
            }
        }
    }
}