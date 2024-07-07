package com.daeun.careerhigh.adapter

import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.daeun.careerhigh.databinding.ItemRecommendedFreelancerBinding
import com.daeun.careerhigh.vo.response.FreelancerInfo


class FreelancerAdapter(val onClick: (FreelancerInfo) -> Unit): ListAdapter<FreelancerInfo, FreelancerAdapter.ViewHolder>(diffUtil) {

    private lateinit var uri: Uri

    inner class ViewHolder(private val viewBinding: ItemRecommendedFreelancerBinding) : RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(freelancer: FreelancerInfo) {
            // TODO
            viewBinding.freelancerName.text = freelancer.name
            viewBinding.jobGroup.text = "# ${freelancer.jobGroup}"
            viewBinding.job.text = "# ${freelancer.job}"
            viewBinding.career.text = "${freelancer.careerYear}년"
            viewBinding.workStyle.text = freelancer.workStyle
            viewBinding.starRating.text = "평점: ${freelancer.starRating}"

            uri = Uri.parse("android.resource://com.daeun.careerhigh/drawable/${freelancer.profileImg}")
            viewBinding.profileImage.setImageURI(uri)

            // 프리랜서 상세 정보
            viewBinding.root.setOnClickListener {
                onClick(freelancer)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemRecommendedFreelancerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val freelancer = currentList[position]
        holder.bind(freelancer)
    }

    companion object {
        val diffUtil = object: DiffUtil.ItemCallback<FreelancerInfo>() {
            override fun areItemsTheSame(oldItem: FreelancerInfo, newItem: FreelancerInfo): Boolean {
                return oldItem.freelancerId == newItem.freelancerId
            }

            override fun areContentsTheSame(oldItem: FreelancerInfo, newItem: FreelancerInfo): Boolean {
                return oldItem == newItem
            }
        }
    }
}