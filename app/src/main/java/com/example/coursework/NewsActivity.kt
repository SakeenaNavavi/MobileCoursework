package com.example.coursework

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class NewsActivity : AppCompatActivity() {
    var layoutManager: LinearLayoutManager? = null
    private lateinit var recycleview: RecyclerView


    var progressView: ViewGroup? = null
    protected var isProgressShowing: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        superHeroes
    }


    private val superHeroes: Unit
        get() {
            showProgressingView()

            RetrofitClient.getApi().getsuperHeroes(
                "4662f45afce949fea09fbbbe7183eca6",
                100,
                "women AND Sri Lanka"

            )?.enqueue(object : Callback<NewsResults?> {
                override fun onResponse(call: Call<NewsResults?>?, response: Response<NewsResults?>) {
                    if (response.isSuccessful()) {
                        recycleview = findViewById<RecyclerView>(R.id.reclcle_list)

                        layoutManager = LinearLayoutManager(
                            this@NewsActivity,
                            LinearLayoutManager.VERTICAL,
                            false
                        )
                        recycleview.setLayoutManager(layoutManager)

                        val bannerAdapter: NewsAdapter = NewsAdapter(
                            this@NewsActivity,
                            response.body()?.getArticles()
                        )
                        recycleview.setAdapter(bannerAdapter)

                        hideProgressingView()

                        //   hideProgressingView();
                    }
                }

                override fun onFailure(call: Call<NewsResults?>?, t: Throwable?) {
                    Toast.makeText(this@NewsActivity, "Unsuccessful ", Toast.LENGTH_SHORT).show()
                }
            })
        }


    fun showProgressingView() {
        // if isProgressShowing = false , means progressbar is not showing .

        if (!isProgressShowing) {
            isProgressShowing = true

            // inflating progressbar_layout layout which has progressbar to show.
            progressView = layoutInflater.inflate(R.layout.progressbar_layout, null) as ViewGroup?

            // "android.R.id.content" gives you the root element of a view of current activity, without having to know its actual name/type/ID but
            //Actually just findViewById(android.R.id.content) is giving the root view.
            // If that is not true in some cases you can get root view from the findViewById(android.R.id.content).getRootView().
            val v = findViewById<View>(R.id.content).rootView
            // View v = this.findViewById(R.id.view );
            val viewGroup = v as ViewGroup // converting obtained root View object to viewGroup
            viewGroup.addView(progressView) // now adding progressbar to the logingactivity ViewGroup which is root view obtained from findViewById(android.R.id.content).getRootView().
        }
    }

    // function used to hide progressbar when login process is completed
    fun hideProgressingView() {
        val v = findViewById<View>(R.id.content).rootView
        // View v = this.findViewById(R.id.view );
        val viewGroup = v as ViewGroup
        viewGroup.removeView(progressView) // removing progressbar from login layout
        isProgressShowing = false
    }
}