package io.mglad.clubmobile.utils

interface ItemSelector<T> {
    fun itemSelected(item: T)
}