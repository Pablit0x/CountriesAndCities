package domain.shared

lateinit var homeFilePath: String
actual fun getCountryCachePath(): CountryCachePath = AndroidCountryCachePath()
class AndroidCountryCachePath : CountryCachePath {
    override val name: String = homeFilePath
}