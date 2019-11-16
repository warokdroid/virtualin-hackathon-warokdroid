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
import com.warokdroid.virtualin.fragments.UmkmDetailFragment
import com.warokdroid.virtualin.models.Umkm

class UmkmsAdapter(var context: Context? = null, var umkms: MutableList<Umkm>? = null) :
    RecyclerView.Adapter<UmkmsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.umkm_item, parent, false))

    override fun getItemCount(): Int = umkms?.size ?: -1

    @SuppressLint("ResourceType")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        umkms?.let { it[position] }?.let { itUmkm ->
            holder.bindItem(itUmkm) {
                val bundle = Bundle()
                it.icon?.let { bundle.putInt(UmkmDetailFragment.KEY_ICON, it) }
                it.name?.let { bundle.putString(UmkmDetailFragment.KEY_UMKM_NAME, it) }
                it.founder?.let { bundle.putString(UmkmDetailFragment.KEY_UMKM_FOUNDER, it) }
                it.rating?.let {
                    bundle.putString(
                        UmkmDetailFragment.KEY_UMKM_RATING,
                        it.toString()
                    )
                }
                it.about?.let { bundle.putString(UmkmDetailFragment.KEY_ABOUT_UMKM, it) }

                val umkmDetailFragment = UmkmDetailFragment()
                umkmDetailFragment.arguments = bundle
                val activity: MainActivity = context as MainActivity
                activity.supportFragmentManager.beginTransaction()
                    .setCustomAnimations(R.transition.slide_in_right, R.transition.slide_out_left)
                    .replace(R.id.frame_home, umkmDetailFragment)
                    .addToBackStack(null).commit()
            }
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val imgIcon: ImageView = view.findViewById(R.id.img_icon)
        private val tvUmkmName: TextView = view.findViewById(R.id.tv_umkm_name)
        private val tvUmkmFounder: TextView = view.findViewById(R.id.tv_umkm_founder)
        private val tvUmkmRating: TextView = view.findViewById(R.id.tv_umkm_rating)

        fun bindItem(umkm: Umkm, listener: (Umkm) -> Unit) {
            umkm.icon?.let { imgIcon.setImageResource(it) }
            umkm.name?.let { tvUmkmName.text = it }
            umkm.founder?.let {
                tvUmkmFounder.text = itemView.resources.getString(R.string.founder, it)
            }
            umkm.rating?.let { tvUmkmRating.text = it.toString() }

            itemView.setOnClickListener { listener(umkm) }
        }
    }
}