package com.warokdroid.virtualin.fragments


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.warokdroid.virtualin.R
import com.warokdroid.virtualin.activities.MainActivity
import com.warokdroid.virtualin.activities.MyPointActivity

/**
 * A simple [Fragment] subclass.
 */
class UmkmDetailFragment : Fragment(), View.OnClickListener {

    private lateinit var root: View

    private lateinit var imgIcon: ImageView
    private lateinit var tvUmkmName: TextView
    private lateinit var tvUmkmFounder: TextView
    private lateinit var tvUmkmRating: TextView
    private lateinit var imgShare: ImageView
    private lateinit var imgBoomark: ImageView
    private lateinit var tvAboutUmkm: TextView
    private lateinit var btnMyPoint: Button

    private var dataIcon = 0
    private lateinit var dataUmkmName: String
    private lateinit var dataUmkmFounder: String
    private lateinit var dataUmkmRating: String
    private lateinit var dataAboutUmkm: String

    private var isBookmark = false

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
        root = inflater.inflate(R.layout.fragment_umkm_detail, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        getData()
        setViews()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.img_share -> {
                val intentShare = Intent(Intent.ACTION_SEND)
                intentShare.type = "text/plain"
                intentShare.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET)
                intentShare.putExtra(Intent.EXTRA_SUBJECT, "Share UMKM")
                intentShare.putExtra(Intent.EXTRA_TEXT, "https://www,warokdroid.com")
                context?.startActivity(Intent.createChooser(intentShare, "Share link!"))
            }
            R.id.img_bookmark -> {
                Toast.makeText(context, "Bookmark Icon clicked!", Toast.LENGTH_SHORT).show()
                isBookmark = !isBookmark
                if (isBookmark) imgBoomark.setImageResource(R.drawable.ic_bookmark_white_24dp)
                else imgBoomark.setImageResource(R.drawable.ic_bookmark_border_white_24dp)
            }
            R.id.btn_my_point -> {
                val pointIntent = Intent(context, MyPointActivity::class.java)
                startActivity(pointIntent)
                val activity: MainActivity = context as MainActivity
                activity.overridePendingTransition(
                    R.transition.slide_in_left,
                    R.transition.slide_out_right
                )
            }
        }
    }

    private fun initViews() {
        imgIcon = root.findViewById(R.id.img_icon_umkm_details)
        tvUmkmName = root.findViewById(R.id.tv_umkm_name_details)
        tvUmkmFounder = root.findViewById(R.id.tv_umkm_founder_details)
        tvUmkmRating = root.findViewById(R.id.tv_umkm_rating_details)
        imgShare = root.findViewById(R.id.img_share)
        imgShare.setOnClickListener(this)
        imgBoomark = root.findViewById(R.id.img_bookmark)
        imgBoomark.setOnClickListener(this)
        tvAboutUmkm = root.findViewById(R.id.tv_about_umkm)
        btnMyPoint = root.findViewById(R.id.btn_my_point)
        btnMyPoint.setOnClickListener(this)
    }

    private fun getData() {
        val bundle = this.arguments
        if (bundle != null) {
            dataIcon = bundle.getInt(KEY_ICON)
            dataUmkmName = bundle.getString(KEY_UMKM_NAME)
            dataUmkmFounder = bundle.getString(KEY_UMKM_FOUNDER)
            dataUmkmRating = bundle.getString(KEY_UMKM_RATING)
            dataAboutUmkm = bundle.getString(KEY_ABOUT_UMKM)
        }
    }

    private fun setViews() {
        imgIcon.setImageResource(dataIcon)
        tvUmkmName.text = dataUmkmName
        tvUmkmFounder.text = resources.getString(R.string.founder, dataUmkmFounder)
        tvUmkmRating.text = dataUmkmRating
        tvAboutUmkm.text = dataAboutUmkm
    }
}