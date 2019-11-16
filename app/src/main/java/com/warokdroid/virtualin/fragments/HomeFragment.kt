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
import com.warokdroid.virtualin.adapters.CategoriesAdapter
import com.warokdroid.virtualin.adapters.TopPointsAdapter
import com.warokdroid.virtualin.models.Category
import com.warokdroid.virtualin.models.Customer
import com.warokdroid.virtualin.models.Umkm

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    private lateinit var rvCategories: RecyclerView
    private lateinit var rvTopPoints: RecyclerView
    private lateinit var root: View

    private var umkms: MutableList<Umkm> = mutableListOf()
    private var categories: MutableList<Category> = mutableListOf()
    private var customers: MutableList<Customer> = mutableListOf()

    private lateinit var categoryIds: Array<String>
    private lateinit var categoryNames: Array<String>
    private lateinit var categoryIcons: TypedArray

    private lateinit var umkmNames: Array<String>
    private lateinit var umkmFounderNames: Array<String>
    private lateinit var umkmRatings: Array<String>
    private lateinit var umkmIcons: TypedArray
    private lateinit var umkmAbouts: Array<String>

    private lateinit var customerNames: Array<String>
    private lateinit var customerPhotos: TypedArray
    private lateinit var customerPoints: Array<String>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_home, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(root)
        prepare()
        addItems()
        setRecyclerViewCategories()
        setRecyclerViewTopPoints()
    }

    private fun initViews(root: View) {
        rvCategories = root.findViewById(R.id.rv_categories)
        rvTopPoints = root.findViewById(R.id.rv_top_points)
    }

    private fun prepare() {
        categoryIds = resources.getStringArray(R.array.category_id)
        categoryNames = resources.getStringArray(R.array.category_name)
        categoryIcons = resources.obtainTypedArray(R.array.category_icon)

        umkmNames = resources.getStringArray(R.array.umkm_name)
        umkmFounderNames = resources.getStringArray(R.array.umkm_founder_name)
        umkmRatings = resources.getStringArray(R.array.umkm_rating)
        umkmIcons = resources.obtainTypedArray(R.array.umkm_icon)
        umkmAbouts = resources.getStringArray(R.array.umkm_about)

        customerNames = resources.getStringArray(R.array.customer_name)
        customerPhotos = resources.obtainTypedArray(R.array.customer_photo)
        customerPoints = resources.getStringArray(R.array.customer_point)
    }

    private fun addItems() {
        for (index in umkmNames.indices) {
            val umkm = Umkm(
                umkmNames[index],
                umkmFounderNames[index],
                umkmRatings[index].toDouble(),
                umkmIcons.getResourceId(index, -1),
                umkmAbouts[index]
            )
            umkms.add(umkm)
        }

        for (index in categoryIds.indices) {
            val category = Category(
                categoryIds[index].toInt(),
                categoryNames[index],
                categoryIcons.getResourceId(index, -1),
                umkms
            )
            categories.add(category)
        }

        for (index in customerNames.indices) {
            val customer = Customer(
                customerNames[index],
                customerPhotos.getResourceId(index, -1),
                customerPoints[index].toInt()
            )
            customers.add(customer)
        }
    }

    private fun setRecyclerViewCategories() {
        val categoriesAdapter = CategoriesAdapter(context, categories)
        rvCategories.adapter = categoriesAdapter
        rvCategories.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun setRecyclerViewTopPoints() {
        val topPointsAdapter = TopPointsAdapter(context, customers)
        rvTopPoints.adapter = topPointsAdapter
        rvTopPoints.layoutManager = LinearLayoutManager(context)
    }
}
