package com.example.moviesearch.home

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.moviesearch.core.model.domain.SearchResponseVO
import com.example.moviesearch.core.util.UIListeners
import com.example.moviesearch.core.util.loadGlide
import kotlinx.android.synthetic.main.item_view.view.*

/**
 * Created by Kiran.
 */
class HomeAdapter(
        private val searchResponse: SearchResponseVO,
        private val context: Context,
        private val listener: UIListeners.OnClickListener)
        : RecyclerView.Adapter<HomeAdapter.ResultHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultHolder {
            val itemView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_view, parent, false)

            val holder = ResultHolder(itemView, context)
            holder.itemView.setOnClickListener {
                val position = holder.adapterPosition
                val resultSelected = searchResponse.resultList[position]
                listener.onClick(resultSelected)
            }

            return holder
        }

        override fun onBindViewHolder(holder: ResultHolder, position: Int) {
            val result = searchResponse.resultList[position]
            holder.bindData(result)
        }

        override fun getItemCount(): Int {
            return searchResponse.resultList.size
        }

        class ResultHolder(view: View, val context: Context) : RecyclerView.ViewHolder(view) {

            fun bindData(vo: SearchResponseVO.ResultVO) {
                itemView.ivChannelMini.loadGlide(vo.poster)
                itemView.tvTitle.text = vo.title
                itemView.tvDescription.text =
                        "${"Type"}: ${vo.type} - " +
                        "${"Year"}: ${vo.year}"
            }

        }
}