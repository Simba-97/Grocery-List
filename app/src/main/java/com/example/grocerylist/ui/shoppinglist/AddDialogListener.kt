package com.example.grocerylist.ui.shoppinglist

import com.example.grocerylist.data.db.entities.ShoppingItems

interface AddDialogListener {
    fun onAddButtonClicked(item: ShoppingItems)
}