package `in`.testpress.testpress.ui.view_holders

import `in`.testpress.testpress.R
import `in`.testpress.testpress.models.pojo.DashboardResponse
import `in`.testpress.testpress.models.pojo.DashboardSection
import `in`.testpress.testpress.ui.adapters.PostCarouselAdapter
import android.content.Context
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager

class PostsCarouselViewHolder(itemView: View, context: Context): BaseCarouselViewHolder(itemView, context) {

    fun display(response: DashboardResponse, context: Context) {
        val section = response.availableSections[adapterPosition]
        initRecyclerView(section, context, response)
        displayTitle(section.displayName)
        if (section.items.size > 2) {
            showPageIndicator()
        }
    }

    private fun initRecyclerView(section: DashboardSection, context: Context, response: DashboardResponse) {
        val adapter = PostCarouselAdapter(response, section, context)
        if (section.items.size > 8) {
            recyclerView.layoutManager = GridLayoutManager(context, 2, GridLayoutManager.HORIZONTAL, false)
        } else {
            recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
        recyclerView.adapter = adapter
    }

    private fun displayTitle(displayName: String) {
        title.text = displayName
        title.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_news_black, 0, 0, 0)
    }
}