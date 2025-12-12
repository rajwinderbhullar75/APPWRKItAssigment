package com.rsb.appwrkitassigment.UI.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.rsb.appwrkitassigment.R
import com.rsb.appwrkitassigment.databinding.ActivityDetailBinding
import com.rsb.appwrkitassigment.viewmodel.DetailViewModel

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val itemId = intent.getIntExtra(EXTRA_ITEM_ID, -1)
        if (itemId != -1) {
            viewModel.loadItem(itemId)
        }

        /*binding.markAsCompletedButton.setOnClickListener {
            viewModel.markAsCompleted()
        }*/

        viewModel.isStatusUpdated.observe(this) { isUpdated ->
            if (isUpdated) {
                val resultIntent = Intent()
                resultIntent.putExtra("updated_item", viewModel.item.value)
                setResult(RESULT_OK, resultIntent)
                finish()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    companion object {
        const val EXTRA_ITEM_ID = "item_id"
    }
}
