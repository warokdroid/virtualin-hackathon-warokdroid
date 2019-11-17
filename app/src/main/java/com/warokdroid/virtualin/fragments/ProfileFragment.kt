package com.warokdroid.virtualin.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.warokdroid.virtualin.R

/**
 * A simple [Fragment] subclass.
 */
class ProfileFragment : Fragment(), View.OnClickListener {

    private lateinit var root: View
    private lateinit var btnSearchProduct: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_profile, container, false)
        initViews()
        return root
    }

    private fun initViews() {
        btnSearchProduct = root.findViewById(R.id.btn_search_product)
        btnSearchProduct.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_search_product -> {
                Toast.makeText(context, "Button search product clicked!", Toast.LENGTH_SHORT).show()
            }
        }
    }

}
