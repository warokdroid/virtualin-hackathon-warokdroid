package com.warokdroid.virtualin.activities

import android.content.res.TypedArray
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.warokdroid.virtualin.R
import com.warokdroid.virtualin.adapters.ProductCashbacksAdapter
import com.warokdroid.virtualin.models.ProductCashback

class MyPointActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var imgArrowBack: ImageView
    private lateinit var rvProductCashback: RecyclerView

    private lateinit var productIcons: TypedArray
    private lateinit var productNames: Array<String>
    private lateinit var productOldPrices: Array<String>
    private lateinit var productNewPrices: Array<String>
    private lateinit var totalPoints: Array<String>

    private val productCashbacks: MutableList<ProductCashback> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_point)
        initViews()
        prepare()
        addItems()
        setRecyclerView()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.img_arrow_back -> {
                finish()
                overridePendingTransition(R.transition.slide_in_right, R.transition.slide_out_left)
            }
        }
    }

    private fun initViews() {
        imgArrowBack = findViewById(R.id.img_arrow_back)
        imgArrowBack.setOnClickListener(this)
        rvProductCashback = findViewById(R.id.rv_product_cashback)
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
        rvProductCashback.adapter = ProductCashbacksAdapter(this, productCashbacks)
        rvProductCashback.layoutManager = LinearLayoutManager(this)
    }
}