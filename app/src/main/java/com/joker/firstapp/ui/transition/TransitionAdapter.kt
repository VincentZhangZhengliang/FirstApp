package com.joker.firstapp.ui.transition

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.joker.firstapp.R


/**
 * Created by Vincent;
 * Created on 2018/5/9;
 * DSC:
 */
class TransitionAdapter(var context: Context) : RecyclerView.Adapter<TransitionAdapter.ViewHolder>() {

    var mListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_transition, parent, false)
        val holder = ViewHolder(view)
        return holder

    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.root?.setOnClickListener {
            mListener?.onItemClick(it, position)
        }
        holder?.root?.setOnLongClickListener {
            mListener?.onItemLongClick(it, position)
            true
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val root = view.findViewById<ConstraintLayout>(R.id.item_transition_root)
        val iv = view.findViewById<ImageView>(R.id.item_transition_iv)
        val title = view.findViewById<TextView>(R.id.item_transition_tv_title)
        val content = view.findViewById<TextView>(R.id.item_transition_tv_content)
    }

    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int)

        fun onItemLongClick(view: View, position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        mListener = listener
        Log.e("setOnItemClickListener","ssssssss")
    }
}