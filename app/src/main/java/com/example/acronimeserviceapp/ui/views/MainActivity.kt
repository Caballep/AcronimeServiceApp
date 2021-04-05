package com.example.acronimeserviceapp.ui.views

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView.OnEditorActionListener
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.acronimeserviceapp.databinding.ActivityMainBinding
import com.example.acronimeserviceapp.model.AcronimeLF
import com.example.acronimeserviceapp.network.entities.toAcronimeLFList
import com.example.acronimeserviceapp.network.services.ServiceResult
import com.example.acronimeserviceapp.ui.adapters.AcronimeAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setViews()
        setObservers()
    }

    private fun setViews() {
        with(binding) {
            acronimeRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
            searchBox.setOnEditorActionListener(OnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    viewModel.fetchAcronimeMeaning(searchBox.text.toString())
                    return@OnEditorActionListener true
                }
                false
            })
        }
    }

    private fun setObservers() {
        viewModel.acronimeMeaningLiveData.observe(this, {
            with(binding) {
                when (it.status) {
                    ServiceResult.Status.SUCCESS -> {
                        it.data?.let { data ->
                            if (data.first().toAcronimeLFList().isNotEmpty()) {
                                setSuccessResultViews(it.data.first().toAcronimeLFList())
                            } else {
                                setEmptyOrNullResultViews()
                            }
                        } ?: run {
                            setEmptyOrNullResultViews()
                        }
                    }
                    ServiceResult.Status.ERROR -> setErrorResultViews(it.message)
                    ServiceResult.Status.LOADING -> setLoadingResultViews()
                }
            }
        })
    }

    private fun setSuccessResultViews(acronimeLFList: List<AcronimeLF>) {
        with(binding) {
            acronimeRecyclerView.adapter = AcronimeAdapter(acronimeLFList)
            textStateInfo.visibility = View.GONE
            acronimeRecyclerView.visibility = View.VISIBLE
        }
    }

    private fun setEmptyOrNullResultViews() {
        with(binding) {
            textStateInfo.text = "No results found"
            textStateInfo.visibility = View.VISIBLE
            acronimeRecyclerView.visibility = View.GONE
        }
    }

    private fun setErrorResultViews(errorMessage: String?) {
        with(binding) {
            textStateInfo.text = errorMessage
            textStateInfo.visibility = View.VISIBLE
            acronimeRecyclerView.visibility = View.GONE
        }
    }

    private fun setLoadingResultViews() {
        with(binding) {
            textStateInfo.text = "Loading..."
            textStateInfo.visibility = View.VISIBLE
            acronimeRecyclerView.visibility = View.GONE
        }
    }
}