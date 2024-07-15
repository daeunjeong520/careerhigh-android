package com.daeun.careerhigh.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.daeun.careerhigh.databinding.ItemProjectBinding
import com.daeun.careerhigh.vo.response.ProjectInfo

class ProjectAdapter(val onClick: (ProjectInfo) -> Unit): ListAdapter<ProjectInfo, ProjectAdapter.ViewHolder>(diffUtil) {

    inner class ViewHolder(private val viewBinding: ItemProjectBinding): RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(item: ProjectInfo) {
            viewBinding.ProjectTitle.text = item.title
            viewBinding.ProjectJobGroup.text = "${item.jobGroup} > "
            viewBinding.ProjectJob.text = item.job
            viewBinding.WorkStyle.text = item.workStyle
            viewBinding.Salary.text = "${item.pay}만원"
            viewBinding.ProjectStartDate.text = item.startDate
            viewBinding.period.text = "  (${item.period}개월)"

            viewBinding.root.setOnClickListener {
                onClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemProjectBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val project = currentList[position]
        holder.bind(project)
    }

    companion object {
        val diffUtil = object: DiffUtil.ItemCallback<ProjectInfo>() {
            override fun areItemsTheSame(oldItem: ProjectInfo, newItem: ProjectInfo): Boolean {
                return oldItem.projectId == newItem.projectId
            }

            override fun areContentsTheSame(oldItem: ProjectInfo, newItem: ProjectInfo): Boolean {
                return oldItem == newItem
            }
        }
    }
}