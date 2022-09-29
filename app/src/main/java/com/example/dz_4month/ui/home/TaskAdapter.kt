package com.example.dz_4month.ui.home

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dz_4month.Task
import com.example.dz_4month.databinding.ItemTaskBinding


class TaskAdapter(private val onLongClick:(Int)->Unit ) :
    RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {
    var onClick:((Task)->Unit)?=null
    private val data = arrayListOf<Task>()
    fun addTask(task: Task) {
        data.add(0,task)
        notifyItemChanged(0)

    }
    fun addTasks(List:List<Task>){
        data.clear()
        data.addAll(List)
        notifyDataSetChanged()
    }
    fun getTask(position: Int):Task{
      return  data[position]
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            ItemTaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(data[position])
        if (position % 2==0){
            holder.itemView.setBackgroundColor(Color.BLACK)
        }else{
            holder.itemView.setBackgroundColor(Color.WHITE)

        }
    }

    override fun getItemCount(): Int {
        return data.size
    }


    inner class TaskViewHolder(private val binding: ItemTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(task: Task) {
            binding.tvDescription.text = task.description
            binding.tvTitle.text = task.title
            itemView.setOnClickListener {
                onClick?.invoke(task
                )
            }
            itemView.setOnLongClickListener {
                onLongClick(adapterPosition)
                false
            }
        }

    }
}