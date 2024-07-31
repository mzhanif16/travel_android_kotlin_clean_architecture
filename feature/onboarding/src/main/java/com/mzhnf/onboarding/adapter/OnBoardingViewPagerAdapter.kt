package com.mzhnf.onboarding.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mzhnf.onboarding.R

class OnboardingViewPagerAdapter(private val onboardingContent: List<Map<String, Any>>): RecyclerView.Adapter<OnboardingViewPagerAdapter.ViewPagerViewHolder>() {

    inner class ViewPagerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val ivImg: ImageView = itemView.findViewById(R.id.iv_onboarding_img)
        val tvTitle: TextView = itemView.findViewById(R.id.tv_title)
        val tvDesc: TextView = itemView.findViewById(R.id.tv_desc)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.onboarding_content, parent, false)
        return ViewPagerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return onboardingContent.size
    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        val currContent = onboardingContent[position]

        holder.tvTitle.text = currContent["title"] as String
        holder.tvDesc.text = currContent["desc"] as String
        holder.ivImg.setImageResource(currContent["image"] as Int)
    }

}