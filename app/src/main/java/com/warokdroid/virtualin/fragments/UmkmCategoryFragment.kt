package com.warokdroid.virtualin.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.warokdroid.virtualin.R
import com.warokdroid.virtualin.adapters.UmkmsAdapter
import com.warokdroid.virtualin.models.Umkm

/**
 * A simple [Fragment] subclass.
 */
class UmkmCategoryFragment : Fragment() {

    private lateinit var root: View

    companion object {
        const val KEY_ID = "key_id"
        const val KEY_NAME = "key_name"
        const val KEY_ICON = "key_icon"
        const val KEY_UMKMS = "key_umkms"
    }

    private lateinit var tvCategoryName: TextView
    private lateinit var rvCategoryUmkm: RecyclerView

    private var umkms: MutableList<Umkm> = mutableListOf()

    private var dataId: Int = 0
    private lateinit var dataName: String
    private var dataIcon: Int = 0
    private var dataUmkms: ArrayList<Umkm> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_umkm_category, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(root)
        getData()
        setViews()
        setRecyclerView()
    }

    private fun initViews(root: View) {
        tvCategoryName = root.findViewById(R.id.tv_category_name)
        rvCategoryUmkm = root.findViewById(R.id.rv_category_umkm)
    }

    private fun getData() {
        val bundle = this.arguments
        if (bundle != null) {
            dataId = bundle.getInt(KEY_ID)
            dataName = bundle.getString(KEY_NAME)
            dataIcon = bundle.getInt(KEY_ICON)
            dataUmkms = bundle.getParcelableArrayList(KEY_UMKMS)

            umkms.addAll(dataUmkms)
        }
    }

    private fun setViews() {
        tvCategoryName.text = dataName
    }

    private fun setRecyclerView() {
        rvCategoryUmkm.adapter = UmkmsAdapter(context, umkms)
        rvCategoryUmkm.layoutManager = LinearLayoutManager(context)
    }
}