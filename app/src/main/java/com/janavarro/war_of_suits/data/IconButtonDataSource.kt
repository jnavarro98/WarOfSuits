package com.janavarro.war_of_suits.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.janavarro.war_of_suits.model.IconButton

/* Handles operations on iconButtonsLiveData and holds details about it. */
class IconButtonDataSource(iconButtons: List<IconButton>) {
    private val initialIconButtonList = iconButtons
    private val iconButtonListLiveData = MutableLiveData(initialIconButtonList)

    /* Adds iconButton to liveData and posts pokerValue. */
    fun addIconButton(iconButton: IconButton) {
        val currentList = iconButtonListLiveData.value
        if (currentList == null) {
            iconButtonListLiveData.postValue(listOf(iconButton))
        } else {
            val updatedList = currentList.toMutableList()
            updatedList.add(0, iconButton)
            iconButtonListLiveData.postValue(updatedList)
        }
    }

    /* Removes iconButton from liveData and posts updated list. */
    fun removeIconButton(iconButton: IconButton) {
        val currentList = iconButtonListLiveData.value
        if (currentList != null) {
            val updatedList = currentList.toMutableList()
            updatedList.remove(iconButton)
            iconButtonListLiveData.postValue(updatedList)
        }
    }

    fun getIconButtonList(): LiveData<List<IconButton>> {
        return iconButtonListLiveData
    }

    companion object {
        private var INSTANCE: IconButtonDataSource? = null

        fun getIconButtonDataSource(iconButtons: List<IconButton>): IconButtonDataSource {
            return synchronized(IconButtonDataSource::class) {
                val newInstance = INSTANCE ?: IconButtonDataSource(iconButtons)
                INSTANCE = newInstance
                newInstance
            }
        }
    }
}