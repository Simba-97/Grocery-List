package com.example.grocerylist.ui.shoppinglist

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import com.example.grocerylist.R
import com.example.grocerylist.data.db.entities.ShoppingItems
import kotlinx.android.synthetic.main.add_item_page.*

class ShoppingItemPage(context: Context, var addDialogListener: AddDialogListener): AppCompatDialog(context){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.shopping_item)

        tvAdd.setOnClickListener {
            val name = etName.text.toString()
            val amount = etAmount.text.toString()

            if(name.isEmpty() || amount.isEmpty()) {
                Toast.makeText(context, "Please enter valid information", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val item = ShoppingItems(name, amount.toInt())
            addDialogListener.onAddButtonClicked(item)
            dismiss()
        }

        tvCancel.setOnClickListener {
            cancel()
        }
    }
}