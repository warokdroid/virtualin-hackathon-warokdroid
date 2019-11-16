package com.warokdroid.virtualin.adapters

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.warokdroid.virtualin.R
import com.warokdroid.virtualin.models.ProductCashback

class ProductCashbacksAdapter(
    var context: Context? = null,
    var productCashbacks: MutableList<ProductCashback>? = null
) : RecyclerView.Adapter<ProductCashbacksAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(context).inflate(
            R.layout.product_cashback_item,
            parent,
            false
        )
    )

    override fun getItemCount(): Int = productCashbacks?.size ?: -1

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        productCashbacks?.let {
            holder.bindItem(it[position]) {
                Toast.makeText(context, "${it.name} clicked!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var imgIcon: ImageView = view.findViewById(R.id.img_icon)
        private var tvProductName: TextView = view.findViewById(R.id.tv_product_name)
        private var tvProductPriceOld: TextView = view.findViewById(R.id.tv_product_price_old)
        private var tvProductPriceNew: TextView = view.findViewById(R.id.tv_product_price_new)
        private var tvPointTotal: TextView = view.findViewById(R.id.tv_point_total)

        fun bindItem(productCashback: ProductCashback, listener: (ProductCashback) -> Unit) {
            productCashback.icon?.let { imgIcon.setImageResource(it) }
            productCashback.name?.let { tvProductName.text = it }
            productCashback.oldPrice?.let {
                tvProductPriceOld.text = context?.resources?.getString(R.string.price_old, it)
                tvProductPriceOld.paintFlags =
                    tvProductPriceOld.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            }
            productCashback.newPrice?.let {
                tvProductPriceNew.text = context?.resources?.getString(R.string.price_new, it)
            }
            productCashback.totalPoint?.let { tvPointTotal.text = it.toString() }

            itemView.setOnClickListener { listener(productCashback) }
        }
    }
}