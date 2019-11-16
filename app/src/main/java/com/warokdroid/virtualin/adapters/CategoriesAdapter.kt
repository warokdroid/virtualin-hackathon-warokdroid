package com.warokdroid.virtualin.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.warokdroid.virtualin.R
import com.warokdroid.virtualin.activities.MainActivity
import com.warokdroid.virtualin.fragments.UmkmCategoryFragment
import com.warokdroid.virtualin.models.Category
import com.warokdroid.virtualin.models.Umkm

class CategoriesAdapter(
    var context: Context? = null,
    var categories: MutableList<Category>? = null
) :
    RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder =
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.category_item, parent, false))

    override fun getItemCount(): Int = categories?.size ?: -1

    @SuppressLint("ResourceType")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        categories?.get(position)?.let { it ->
            holder.bindItem(it) {
                val bundle = Bundle()
                it.id?.let { itId -> bundle.putInt(UmkmCategoryFragment.KEY_ID, itId) }
                it.name?.let { itName -> bundle.putString(UmkmCategoryFragment.KEY_NAME, itName) }
                it.icon?.let { itIcon -> bundle.putInt(UmkmCategoryFragment.KEY_ICON, itIcon) }
                val umkms: ArrayList<Umkm> = arrayListOf()
                it.umkms?.let { itUmkms -> umkms.addAll(itUmkms) }
                bundle.putParcelableArrayList(UmkmCategoryFragment.KEY_UMKMS, umkms)

                val umkmCategoryFragment = UmkmCategoryFragment()
                umkmCategoryFragment.arguments = bundle
                val activity: MainActivity = context as MainActivity
                activity.supportFragmentManager.beginTransaction()
                    .setCustomAnimations(R.transition.slide_in_left, R.transition.slide_out_right)
                    .replace(R.id.frame_home, umkmCategoryFragment)
                    .addToBackStack(null).commit()
            }
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val imgIcon: ImageView = view.findViewById(R.id.img_icon)
        private val tvCategoryName: TextView = view.findViewById(R.id.tv_category_name)

        fun bindItem(category: Category, listener: (Category) -> Unit) {
            category.icon?.let {
                imgIcon.setImageResource(it)
            }
            category.name?.let {
                tvCategoryName.text = it
            }
            itemView.setOnClickListener { listener(category) }
        }
    }
}