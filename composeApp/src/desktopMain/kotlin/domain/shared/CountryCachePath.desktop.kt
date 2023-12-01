package domain.shared

import java.io.File

actual fun getCountryCachePath(): CountryCachePath = JVMCountryCachePath()

class JVMCountryCachePath : CountryCachePath {
    override val name: String = System.getProperty("user.home") + File.separator + "country_cache.json"
}
