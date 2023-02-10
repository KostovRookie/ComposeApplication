package com.elders.composeapplication.domain.items

import com.elders.composeapplication.data.remote.models.SpecificGameModel

data class SpecificGameItem (

    val id: Int,
    val title: String,
    val thumbnail: String,
    val description: String
)

fun SpecificGameModel.toSpecificGameItem() = SpecificGameItem(id, title, thumbnail,description)