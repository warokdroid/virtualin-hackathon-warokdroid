package com.warokdroid.virtualin.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.warokdroid.virtualin.R

/**
 * A simple [Fragment] subclass.
 */
class UmkmDetailFragment : Fragment() {

    companion object {
        const val KEY_ICON = "key_icon"
        const val KEY_UMKM_NAME = "key_umkm_name"
        const val KEY_UMKM_FOUNDER = "key_umkm_founder"
        const val KEY_UMKM_RATING = "key_umkm_rating"
        const val KEY_ABOUT_UMKM = "key_about_umkm"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_umkm_detail, container, false)
    }
}
