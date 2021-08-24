package com.example.opskube
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.opskube.Blogs
import java.util.*

class BlogsAdapter(arrayList: ArrayList<Blogs>, context: Context) :
    RecyclerView.Adapter<BlogsAdapter.ViewHolder>() {
    var arrayList: ArrayList<Blogs>
    var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_blog, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val blogs: Blogs = arrayList[position]
        holder.id.text=""+blogs.id
        holder.user_id.text=""+blogs.user_id
        holder.title.text=blogs.title
        holder.body.text=blogs.body

    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
         var id: TextView
         var user_id: TextView
         var title: TextView
         var body: TextView

        init {
            id = itemView.findViewById(R.id.id)
            user_id = itemView.findViewById(R.id.userid)
            title = itemView.findViewById(R.id.title)
            body = itemView.findViewById(R.id.body)
        }
    }

    init {
        this.arrayList = arrayList
        this.context = context
    }
}