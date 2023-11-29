package data.utils

object Constants {
    
    const val API_KEY =  "43ad7ddc-2699-4ea7-b081-cecc5cedba42"
    const val BASE_URL = "https://airlabs.co/api/v9"
    
    const val COUNTRIES_PARAMETER = "/countries?"
    const val CITIES_PARAMETER = "/cities?"
    const val API_PARAMETER = "\$api_key=$API_KEY"
    
    const val COUNTRIES_URL = BASE_URL + COUNTRIES_PARAMETER + API_PARAMETER
    const val CITIES_URL = BASE_URL + CITIES_PARAMETER + API_PARAMETER
    
    
}