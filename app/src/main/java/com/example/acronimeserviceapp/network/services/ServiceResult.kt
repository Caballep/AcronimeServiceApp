package com.example.acronimeserviceapp.network.services

data class ServiceResult<out T>(val status: Status, val data: T?, val message: String?) {

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object {
        fun <T> success(data: T): ServiceResult<T> {
            return ServiceResult(Status.SUCCESS, data, null)
        }

        fun <T> error(message: String, data: T? = null): ServiceResult<T> {
            return ServiceResult(Status.ERROR, data, message)
        }

        fun <T> loading(data: T? = null): ServiceResult<T> {
            return ServiceResult(Status.LOADING, data, null)
        }
    }
}