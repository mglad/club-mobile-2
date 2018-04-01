package io.mglad.clubmobile.ui.club.fragment.twitter

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import io.mglad.clubmobile.R
import io.mglad.clubmobile.databinding.ItemTweetBinding

class ClubTwitterAdapter(private val context: Context) : RecyclerView.Adapter<ClubTwitterAdapter.PostViewHolder>() {
    /**
     * The list of posts of the adapter
     */
    private var tweets: List<String> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val binding: ItemTweetBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_tweet, parent, false)
        return PostViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return tweets.size
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(tweets[position])
    }

    /**
     * Updates the list of clubs of the adapter
     * @param clubs the new list of clubs of the adapter
     */
    fun updateTweets(tweets: List<String>) {
        this.tweets = tweets
        notifyDataSetChanged()
    }

    /**
     * The ViewHolder of the adapter
     * @property binding the DataBinging object for Post item
     */
    class PostViewHolder(private val binding: ItemTweetBinding) : RecyclerView.ViewHolder(binding.root) {
        /**
         * Binds a post into the view
         */
        fun bind(tweet: String) {
            binding.tweet = tweet
            binding.executePendingBindings()
        }
    }
}