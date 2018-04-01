package io.mglad.clubmobile.ui.findclub

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import io.mglad.clubmobile.R
import io.mglad.clubmobile.databinding.ItemClubBinding
import io.mglad.clubmobile.model.Club
import io.mglad.clubmobile.utils.ItemSelector


class FindClubAdapter(private val context: Context, private val itemSelector: ItemSelector<Club>) : RecyclerView.Adapter<FindClubAdapter.PostViewHolder>(), Filterable {
    /**
     * The list of posts of the adapter
     */
    private var clubs: List<Club> = listOf()
    private var filteredClubs: List<Club> = listOf()
    private val clubFilter: ClubFilter = ClubFilter()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val binding: ItemClubBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_club, parent, false)
        return PostViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return filteredClubs.size
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(filteredClubs[position])
        holder.itemView.setOnClickListener({
            itemSelector.itemSelected(filteredClubs[position])
        })
    }

    /**
     * Updates the list of clubs of the adapter
     * @param clubs the new list of clubs of the adapter
     */
    fun updateClubs(clubs: List<Club>) {
        this.clubs = clubs
        this.filteredClubs = clubs
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

    override fun getFilter(): Filter {
        return clubFilter
    }

    inner class ClubFilter: Filter(){
        override fun performFiltering(charSequence: CharSequence?): FilterResults {
            val filterResults = Filter.FilterResults()
            if (charSequence != null && charSequence.isNotEmpty()) {
                val tempList = arrayListOf<Club>()

                // search content in friend list
                for (club in clubs) {
                    if (club.name.toLowerCase().contains(charSequence.toString().toLowerCase())) {
                        tempList.add(club)
                    }
                }

                filterResults.count = tempList.size
                filterResults.values = tempList
            } else {
                filterResults.count = clubs.size
                filterResults.values = clubs
            }

            return filterResults
        }

        @Suppress("UNCHECKED_CAST")
        override fun publishResults(p0: CharSequence?, filterResults: FilterResults) {
            filteredClubs = filterResults.values as List<Club>

            notifyDataSetChanged()
        }

    }
}
