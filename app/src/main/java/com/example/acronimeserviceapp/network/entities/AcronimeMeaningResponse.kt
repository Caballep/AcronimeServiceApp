package com.example.acronimeserviceapp.network.entities

import com.example.acronimeserviceapp.model.AcronimeLF

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

fun AcronimeMeaningResponse.toAcronimeLFList(): List<AcronimeLF> {
    return lfs.map {
        AcronimeLF(
            lf = it.lf,
            since = "since ${it.since}"
        )
    }
}
