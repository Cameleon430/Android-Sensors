package com.example.kursova.presenter.accelerometerData

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kursova.data.AxisEntity
import com.example.kursova.databinding.ListAxisItemBinding

class AxisViewItemAdapter(
    private val listener: OnItemSelectListener? = null
    ): RecyclerView.Adapter<AxisViewItemAdapter.ViewHolder>(){

    private var itemList: List<AxisEntity> = emptyList()

    class ViewHolder(
        private val binding: ListAxisItemBinding
    ):RecyclerView.ViewHolder(binding.root) {

        fun bind(axis: AxisEntity, listener: OnItemSelectListener?){

            with(binding) {
                root.setOnClickListener {
                    listener?.onClick(axis)
                }

                titleTextView.text = axis.name
                xAxisTextView.text = axis.xValue.toString()
                yAxisTextView.text = axis.yValue.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListAxisItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: AxisEntity = itemList[position]
        holder.bind(item, listener)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateItemsView(list: List<AxisEntity>){
        itemList = list
        notifyDataSetChanged()
    }

    interface OnItemSelectListener{
        fun onClick(axis: AxisEntity)
    }

}