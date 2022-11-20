package com.janavarro.warofsuits.ui.welcome

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.janavarro.warofsuits.R
import com.janavarro.warofsuits.components.adapters.IconButtonAdapter
import com.janavarro.warofsuits.databinding.ActivityWelcomeBinding
import com.janavarro.warofsuits.model.IconButton
import com.janavarro.warofsuits.model.launch


class WelcomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBinding
//  View model factory so we can inject dependencies
    private val welcomeActivityViewModel by viewModels<WelcomeViewModel> {
        WelcomeViewModelFactory(this)
    }
    private val layoutManager = LinearLayoutManager(this)
    private lateinit var iconButtonAdapter: IconButtonAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        initUi()
        initObservers()
    }

    private fun initUi() {
        iconButtonAdapter = IconButtonAdapter { it.launch(this) }
        binding.rvIconButtons.layoutManager = layoutManager
        binding.rvIconButtons.adapter = iconButtonAdapter
        val divider = DividerItemDecoration(this, layoutManager.orientation)
        AppCompatResources.getDrawable(this, R.drawable.divider_rv)?.let { divider.setDrawable(it) }
        binding.rvIconButtons.addItemDecoration(divider)
    }

    private fun initObservers() {
        welcomeActivityViewModel.iconButtonLiveData.observe(this) {
            it?.let {
                iconButtonAdapter.submitList(it as MutableList<IconButton>)
            }
        }
    }
}