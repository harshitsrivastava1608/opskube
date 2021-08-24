package com.example.opskube

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.*
import org.json.JSONException
import org.json.JSONObject
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {
    var page:Int=1
    lateinit var requestQueue:RequestQueue
    lateinit var recyclerView:RecyclerView
    lateinit var layoutManager:RecyclerView.LayoutManager
    lateinit var blogsAdapter: BlogsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView=findViewById(R.id.recyclerView)
        requestQueue = Volley.newRequestQueue(this)
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager=layoutManager
        jsonParse("https://gorest.co.in/public/v1/posts?page="+page)
    }
    private fun jsonParse(url:String) {

        val arrayList=ArrayList<Blogs>()
        val request = JsonObjectRequest(Request.Method.GET, url, null, Response.Listener {
                response ->try {
            val jsonArray = response.getJSONArray("data")
            for (i in 0 until jsonArray.length()) {
                var jsonObject:JSONObject= jsonArray.get(i) as JSONObject
                var blogs=Blogs(
                    jsonObject.get("id") as Int, jsonObject.get("user_id") as Int,
                    ""+jsonObject.get("title"),""+jsonObject.get("body"))
                arrayList.add(blogs)}
            if (!arrayList.isEmpty()){
                Log.i("hs",""+arrayList)
                blogsAdapter= BlogsAdapter(arrayList,applicationContext)
                recyclerView.layoutManager=layoutManager
                recyclerView.adapter=blogsAdapter
            }
            println("/*/*"+arrayList)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        }, Response.ErrorListener { error -> error.printStackTrace() })
        requestQueue?.add(request)
    }

    fun decr(view: View) {
        if(page>1)
            --page
        jsonParse("https://gorest.co.in/public/v1/posts?page="+page)
    }

    fun incr(view: View) {
        ++page
        jsonParse("https://gorest.co.in/public/v1/posts?page="+page)
    }
}