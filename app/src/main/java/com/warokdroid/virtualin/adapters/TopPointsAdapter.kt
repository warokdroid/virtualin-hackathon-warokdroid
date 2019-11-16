package com.warokdroid.virtualin.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.warokdroid.virtualin.R
import com.warokdroid.virtualin.models.Customer

class TopPointsAdapter(var context: Context? = null, var customers: MutableList<Customer>? = null) :
    RecyclerView.Adapter<TopPointsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.top_point_item, parent, false))

    override fun getItemCount(): Int = customers?.size ?: -1

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        customers?.let { it[position] }?.let { it ->
            holder.bindItem(it) {
                Toast.makeText(context, customers?.let { it[position].name }, Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val imgIcon: ImageView = view.findViewById(R.id.img_icon)
        private val tvCustomerName: TextView = view.findViewById(R.id.tv_customer_name)
        private val tvCustomerPoint: TextView = view.findViewById(R.id.tv_customer_point)

        fun bindItem(customer: Customer, listener: (Customer) -> Unit) {
            customer.photo?.let { imgIcon.setImageResource(it) }
            customer.name?.let { tvCustomerName.text = it }
            customer.point?.let {
                tvCustomerPoint.text = itemView.resources.getString(R.string.point, it.toString())
            }
            itemView.setOnClickListener { listener(customer) }
        }
    }
}