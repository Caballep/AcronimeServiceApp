package com.example.acronimeserviceapp.network.entities

data class AcronimeMeaningResponse (
    val sf: String,
    val lfs: List<LF>
) {
    data class LF (
        val lf: String,
        val freq: Long,
        val since: Long,
        val vars: List<LF>? = null
    )
}
