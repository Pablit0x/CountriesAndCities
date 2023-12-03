package domain.shared

import platform.Foundation.NSHomeDirectory


actual fun getCountryCachePath(): CountryCachePath = IOSCountryCachePath()

class IOSCountryCachePath : CountryCachePath {
    override val name: String = "${NSHomeDirectory()}/country_cache.json"
}