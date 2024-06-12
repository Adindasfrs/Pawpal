package com.capstone.pawpal.userinterface

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.capstone.pawpal.R
import com.capstone.pawpal.adapter.StoryListAdapter
import com.capstone.pawpal.databinding.ActivityDetailBinding
import com.capstone.pawpal.dataclass.StoryDetail

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val story = intent.getParcelableExtra<StoryDetail>(EXTRA_STORY) as StoryDetail
        setStory(story)

        supportActionBar?.title = getString(R.string.detail_title, story.name)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        finish()
        return super.onSupportNavigateUp()
    }

    private fun setStory(story: StoryDetail) {
        binding.apply {
            tvDetailName.text = story.name
            tvDetailDesc.text = story.description
            tvDetailDate.text = StoryListAdapter.formatDateToString(story.createdAt)
        }
        Glide.with(this)
            .load(story.photoUrl)
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(binding.imageDetailStory)
    }

    companion object {
        const val EXTRA_STORY = "extra_story"
    }
}