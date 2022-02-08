package com.game.roomproject

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.game.roomproject.data.Subscriber
import com.game.roomproject.data.SubscriberDatabase
import com.game.roomproject.data.SubscriberRepository
import com.game.roomproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var subscriberViewModel: SubscriberViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val dao = SubscriberDatabase.getInstance(application).subscriberDAO
        val repository = SubscriberRepository(dao)
        val factory = SubscriberViewModelFactory(repository)
        subscriberViewModel = ViewModelProvider(this, factory)[SubscriberViewModel::class.java]

        binding.viewModel = subscriberViewModel
        binding.lifecycleOwner = this

        initRecyclerView()

        subscriberViewModel.message.observe(this){
            it.getContentIfNotHandled()?.let{
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun initRecyclerView(){
        displaySubscribersList()
    }

    private fun displaySubscribersList(){
        subscriberViewModel.subscribers.observe(this){
            // Log.i("MYTAG", it.toString())
            binding.subscriberRecyclerView.setAdapter(
                this,
                RecyclerViewAdapter(this, it) { selectedItem: Subscriber ->
                    listItemClicked(
                        selectedItem
                    )
                },
                RecyclerView.VERTICAL
            )
        }
    }

    private fun listItemClicked(subscriber: Subscriber){
        subscriberViewModel.initUpdateAndDelete(subscriber)
    }

    fun <T, B: ViewBinding> RecyclerView.setAdapter(
        context: Context?,
        adapter: BaseAdapter<T, B>?,
        type: Int = RecyclerView.HORIZONTAL,
        reverseLayout: Boolean = false,
    ){
        val layoutManager: RecyclerView.LayoutManager
                = LinearLayoutManager(
            context,
            type,
            reverseLayout
        )

        this.layoutManager = layoutManager
        this.adapter = adapter
    }
}