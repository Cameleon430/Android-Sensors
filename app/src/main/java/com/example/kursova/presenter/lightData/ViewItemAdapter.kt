package com.example.kursova.presenter.lightData

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kursova.data.LightEntity
import com.example.kursova.databinding.ListItemBinding

class ViewItemAdapter(
    private val listener: OnItemSelectListener? = null
): RecyclerView.Adapter<ViewItemAdapter.ViewHolder>(){
    private var itemList: List<LightEntity> = emptyList()

    class ViewHolder(
        private val binding: ListItemBinding
        ):RecyclerView.ViewHolder(binding.root) {

        fun bind(light: LightEntity, listener: OnItemSelectListener?){

            with(binding) {
                root.setOnClickListener {
                    listener?.onClick(light)
                }

                titleTextView.text = light.name
                descriptionTextView.text = light.value.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: LightEntity = itemList[position]
        holder.bind(item, listener)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateItemsView(list: List<LightEntity>){
        itemList = list
        notifyDataSetChanged()
    }

    interface OnItemSelectListener{
        fun onClick(light: LightEntity)
    }

}