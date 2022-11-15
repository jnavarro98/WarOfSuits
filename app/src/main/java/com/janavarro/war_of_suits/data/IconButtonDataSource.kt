package com.janavarro.war_of_suits.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.janavarro.war_of_suits.model.IconButton

/* Handles operations on iconButtonsLiveData and holds details about it. */
class IconButtonDataSource(iconButtons: List<IconButton>) {
    private val initialIconButtonList = iconButtons
    private val iconButtonLiveData = MutableLiveData(initialIconButtonList)

    /* Adds iconButton to liveData and posts value. */
    fun addIconButton(iconButton: IconButton) {
        val currentList = iconButtonLiveData.value
        if (currentList == null) {
            iconButtonLiveData.postValue(listOf(iconButton))
        } else {
            val updatedList = currentList.toMutableList()
            updatedList.add(0, iconButton)
            iconButtonLiveData.postValue(updatedList)
        }
    }

    /* Removes iconButton from liveData and posts value. */
    fun removeIconButton(iconButton: IconButton) {
        val currentList = iconButtonLiveData.value
        if (currentList != null) {
            val updatedList = currentList.toMutableList()
            updatedList.remove(iconButton)
            iconButtonLiveData.postValue(updatedList)
        }
    }

    //TODO: Maybe is useful in the future

    /* Returns iconButton given an ID.
    fun getIconButtonForId(id: Long): IconButton? {
        iconButtonLiveData.value?.let { iconButtons ->
            return iconButtons.firstOrNull{ it.id == id}
        }
        return null
    } */

    fun getIconButtonList(): LiveData<List<IconButton>> {
        return iconButtonLiveData
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