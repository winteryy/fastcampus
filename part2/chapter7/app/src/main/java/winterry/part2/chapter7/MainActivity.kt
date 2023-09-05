package winterry.part2.chapter7

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.net.Uri
import android.provider.Settings
import android.view.LayoutInflater
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.CurrentLocationRequest
import com.google.android.gms.location.LocationServices
import winterry.part2.chapter7.databinding.ActivityMainBinding
import winterry.part2.chapter7.databinding.ItemForecastBinding
import java.util.Locale
import kotlin.Exception


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    val locationPermissionRequest = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        when {
            permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                updateLocation()
            }

            else -> {
                Toast.makeText(this, "위치 권한이 필요합니다.", Toast.LENGTH_SHORT).show()
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                    data = Uri.fromParts("package", packageName, null)
                }
                startActivity(intent)
                finish()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        locationPermissionRequest.launch(arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION))

    }

    private fun updateLocation() {
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            locationPermissionRequest.launch(arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION))

            return
        }

        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->

            location ?: return@addOnSuccessListener
            Log.d(
                "lastLocation",
                location.latitude.toString() + " " + location.longitude.toString()
            )

            Thread {
                try {
                    val addressList = Geocoder(this, Locale.KOREA).getFromLocation(
                        location.latitude,
                        location.longitude,
                        1
                    )
                    runOnUiThread {
                        binding.locationTextView.text = addressList?.get(0)?.thoroughfare.orEmpty()
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }.start()

            WeatherRepository.getVillageForecast(
                longitude = location.longitude,
                latitude = location.latitude,
                successCallback = { list ->

                    val currentForecast = list.first()

                    binding.temperatureTextView.text =
                        getString(R.string.temperature_text, currentForecast.temperature)
                    binding.skyTextView.text = currentForecast.weather
                    binding.precipitationTextView.text =
                        getString(R.string.precipitation_text, currentForecast.precipitation)

                    binding.childForecastLayout.apply {
                        list.forEachIndexed { index, forecast ->
                            if (index == 0) return@forEachIndexed

                            val itemView = ItemForecastBinding.inflate(layoutInflater)
                            itemView.timeTextView.text = forecast.forecastTime
                            itemView.weatherTextView.text = forecast.weather
                            itemView.temperatureTextView.text =
                                getString(R.string.temperature_text, forecast.temperature)

                            addView(itemView.root)
                        }
                    }
                },
                failureCallback = {
                    it.printStackTrace()
                },
                key = getString(R.string.api_key)
            )
        }
    }
}