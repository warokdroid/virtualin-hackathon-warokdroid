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
class UmkmCategoryFragment : Fragment() {

    companion object {
        const val KEY_ID = "key_id"
        const val KEY_NAME = "key_name"
        const val KEY_ICON = "key_icon"
        const val KEY_UMKMS = "key_umkms"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_umkm_category, container, false)
    }


}
