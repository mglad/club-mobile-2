package io.mglad.clubmobile.ui.dashboard

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import io.mglad.clubmobile.R
import io.mglad.clubmobile.databinding.ItemClubBinding
import io.mglad.clubmobile.model.Club
import io.mglad.clubmobile.utils.ItemSelector

class DashboardAdapter(private val context: Context, private val itemSelector: ItemSelector<Club>) : RecyclerView.Adapter<DashboardAdapter.PostViewHolder>() {
    /**
     * The list of posts of the adapter
     */
    private var clubs: List<Club> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val binding: ItemClubBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_club, parent, false)
        return PostViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return clubs.size
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(clubs[position])
        holder.itemView.setOnClickListener({
            itemSelector.itemSelected(clubs[position])
        })
    }

    /**
     * Updates the list of clubs of the adapter
     * @param clubs the new list of clubs of the adapter
     */
    fun updateClubs(clubs: List<Club>) {
        this.clubs = clubs
        notifyDataSetChanged()
    }

    /**
     * The ViewHolder of the adapter
     * @property binding the DataBinging object for Post item
     */
    class PostViewHolder(private val binding: ItemClubBinding) : RecyclerView.ViewHolder(binding.root) {
        /**
         * Binds a post into the view
         */
        fun bind(club: Club) {
            binding.club = club
            binding.executePendingBindings()
        }
    }
}