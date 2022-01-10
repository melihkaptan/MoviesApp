package com.melhkptn.moviesapp.core.util

import com.melhkptn.moviesapp.core.util.Status.*

data class Resource<out T>(val status: Status, val data: T?, val message: String = "") {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(SUCCESS, data)
        }

        fun <T> error(msg: String, data: T? = null): Resource<T> {
            return Resource(ERROR, data, msg)
        }

        fun <T> loading(data: T? = null): Resource<T> {
            return Resource(LOADING, data)
        }
    }
}

