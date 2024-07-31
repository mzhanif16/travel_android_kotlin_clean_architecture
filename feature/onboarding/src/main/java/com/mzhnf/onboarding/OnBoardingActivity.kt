package com.mzhnf.onboarding

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.mzhnf.onboarding.adapter.OnboardingViewPagerAdapter
import com.mzhnf.onboarding.databinding.ActivityOnBoardingBinding

class OnBoardingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOnBoardingBinding
    private var currentPage = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val onBoardingContent = listOf(
            mapOf(
                "title" to "Explore The World With TourPlace",
                "desc" to "Explore the world with TourPlace. Discover amazing destinations. Plan your dream trip today.",
                "image" to R.drawable.pg1
            ),
            mapOf(
                "title" to "Make Memories That Last\na Lifetime",
                "desc" to "Lorem ipsum dolor sit amet consectetur. Mi ultricies ultrices fermentum a. Duis neque lectus pharetra ac sed lorem.",
                "image" to R.drawable.pg2
            ),
            mapOf(
                "title" to "Plan Your Dream Trip With Tourplace",
                "desc" to "Lorem ipsum dolor sit amet consectetur. Mi ultricies ultrices fermentum a. Duis neque lectus pharetra ac sed lorem.",
                "image" to R.drawable.pg3
            ),
        )
        val viewPagerAdapter = OnboardingViewPagerAdapter(onBoardingContent)
        binding.onboardingViewPager.adapter = viewPagerAdapter
        binding.wormDotsIndicator.attachTo(binding.onboardingViewPager)

        binding.btnNext.setOnClickListener {
            if (currentPage < onBoardingContent.size - 1) {
                currentPage++
                binding.onboardingViewPager.setCurrentItem(currentPage,true)
                updateProgressBar(currentPage)
            }else{
                navigateToMainActivity()
            }
        }
        binding.btnSkip.setOnClickListener {
            navigateToMainActivity()
        }

        binding.onboardingViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                currentPage = position
                updateProgressBar(position)
            }
        })
    }

    private fun updateProgressBar(position: Int) {
        val progress = (position + 1) * 100 / 3
        ObjectAnimator.ofInt(binding.progressBar, "progress", binding.progressBar.progress, progress).apply {
            duration = 800
            start()
        }
    }
    private fun navigateToMainActivity() {
        val intent = Intent()
        intent.setClassName(this, "com.mzhnf.kotlin.MainActivity")
        startActivity(intent)
        finish()
    }
}