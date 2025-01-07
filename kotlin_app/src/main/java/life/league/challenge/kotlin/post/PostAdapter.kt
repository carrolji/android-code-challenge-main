package life.league.challenge.kotlin.post

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_post.view.img_avatar
import kotlinx.android.synthetic.main.item_post.view.tv_body
import kotlinx.android.synthetic.main.item_post.view.tv_name
import kotlinx.android.synthetic.main.item_post.view.tv_title
import life.league.challenge.kotlin.R
import life.league.challenge.kotlin.model.Post

class PostAdapter :
    RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    var posts: List<Post> = emptyList()
        set(items) {
            field = items
            notifyDataSetChanged()
        }

    class PostViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return PostViewHolder(view)
    }

    override fun getItemCount(): Int = posts.size

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        Glide.with(holder.itemView.context)
            .load(posts[position].avatar)
            .centerCrop()
            .circleCrop()
            .fallback(R.drawable.ic_account_default)
            .into(holder.itemView.img_avatar)

        holder.itemView.apply {
            tv_name.text = posts[position].name
            tv_title.text = posts[position].title
            tv_body.text = posts[position].body
        }
    }
}