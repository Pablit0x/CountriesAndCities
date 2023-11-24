package domain.model

import data.model.country.AUD

data class Country(
    val name: String,
    val currency: AUD,
    val languages: String,
    val flag: String
)