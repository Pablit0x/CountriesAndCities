package domain.shared

interface CountryCachePath {
    val name: String
}
expect fun getCountryCachePath() : CountryCachePath