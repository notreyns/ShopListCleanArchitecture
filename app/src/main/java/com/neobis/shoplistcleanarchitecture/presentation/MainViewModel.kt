package com.neobis.shoplistcleanarchitecture.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.neobis.shoplistcleanarchitecture.data.ShopListRepositoryImpl
import com.neobis.shoplistcleanarchitecture.domain.DeleteItemUseCase
import com.neobis.shoplistcleanarchitecture.domain.EditItemUseCase
import com.neobis.shoplistcleanarchitecture.domain.GetShopListUseCase
import com.neobis.shoplistcleanarchitecture.domain.ShopItem

class MainViewModel: ViewModel() {
    private val repository =  ShopListRepositoryImpl

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val editShopItemUseCase = EditItemUseCase(repository)
    private val deleteItemUseCase = DeleteItemUseCase(repository)

    val shopList =MutableLiveData<List<ShopItem>>()

    fun getShopList(){
        val list = getShopListUseCase.getShopList()
        shopList.value = list
    }

    fun editShopItem(shopItem: ShopItem){
        val newItem = shopItem.copy(isActive = !shopItem.isActive)
        editShopItemUseCase.editItem(newItem)
        getShopList()
    }

    fun deleteShopItem(shopItem: ShopItem){
        deleteItemUseCase.deleteItem(shopItem)
        getShopList()
    }

}