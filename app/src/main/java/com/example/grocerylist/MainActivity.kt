package com.example.grocerylist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.grocerylist.data.db.ShoppingDatabase
import com.example.grocerylist.data.db.entities.ShoppingItems
import com.example.grocerylist.data.other.ShoppingListItemAdapter
import com.example.grocerylist.data.repositories.ShoppingRepository
import com.example.grocerylist.ui.shoppinglist.AddDialogListener
import com.example.grocerylist.ui.shoppinglist.ShoppingItemPage
import com.example.grocerylist.ui.shoppinglist.ShoppingViewModel
import com.example.grocerylist.ui.shoppinglist.ShoppingViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val database = ShoppingDatabase(this)
        val repository = ShoppingRepository(database)

        val factory = ShoppingViewModelFactory(repository)

        val viewModel = ViewModelProviders.of(this, factory).get(ShoppingViewModel::class.java)

        val adapter = ShoppingListItemAdapter(listOf(), viewModel)

        rvShoppingItems.layoutManager = LinearLayoutManager(this)
        rvShoppingItems.adapter = adapter

        viewModel.getAllShoppingItems().observe(this, Observer {
            adapter.items = it
            adapter.notifyDataSetChanged()
        })

        fab.setOnClickListener {
            ShoppingItemPage(this,
            object : AddDialogListener {
                override fun onAddButtonClicked(item: ShoppingItems) {
                    viewModel.upsert(item)
                }
            }).show()
        }
    }
}