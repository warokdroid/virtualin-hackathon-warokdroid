package com.warokdroid.virtualin.fragments


import android.content.res.TypedArray
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.warokdroid.virtualin.R
import com.warokdroid.virtualin.adapters.ProductCashbacksAdapter
import com.warokdroid.virtualin.models.ProductCashback

/**
 * A simple [Fragment] subclass.
 */
class PromotionsFragment : Fragment() {

    private lateinit var rvProductCashback: RecyclerView
    private lateinit var root: View

    private lateinit var productIcons: TypedArray
    private lateinit var productNames: Array<String>
    private lateinit var productOldPrices: Array<String>
    private lateinit var productNewPrices: Array<String>
    private lateinit var totalPoints: Array<String>

    private val productCashbacks: MutableList<ProductCashback> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_promotions, container, false)
        initViews()
        prepare()
        addItems()
        return root
    }

    private fun initViews() {
        rvProductCashback = root.findViewById(R.id.rv_product_cashback)
        setRecyclerView()
    }

    private fun prepare() {
        productIcons = resources.obtainTypedArray(R.array.product_icon)
        productNames = resources.getStringArray(R.array.product_name)
        productOldPrices = resources.getStringArray(R.array.product_old_price)
        productNewPrices = resources.getStringArray(R.array.product_new_price)
        totalPoints = resources.getStringArray(R.array.total_point)
    }

    private fun addItems() {
        for (index in productNames.indices) {
            val productCashback = ProductCashback(
                productIcons.getResourceId(index, -1),
                productNames[index],
                productOldPrices[index],
                productNewPrices[index],
                totalPoints[index].toInt()
            )
            productCashbacks.add(productCashback)
        }
    }

    private fun setRecyclerView() {
        rvProductCashback.adapter = ProductCashbacksAdapter(context, productCashbacks)
        rvProductCashback.layoutManager = LinearLayoutManager(context)
    }
}
