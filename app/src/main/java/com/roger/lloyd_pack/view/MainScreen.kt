package com.roger.lloyd_pack.view

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.roger.lloyd_pack.data.OpenWeatherResponseModel
import com.roger.lloyd_pack.ext.toCelsius
import com.roger.lloyd_pack.ext.toFahrenheit
import com.roger.lloyd_pack.ext.toKilometer
import com.roger.lloyd_pack.ui.theme.primnaryColor
import com.roger.lloyd_pack.ui.theme.secondaryColor
import com.roger.lloyd_pack.viewmodel.UIState
import com.roger.lloyd_pack.viewmodel.WeatherViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DisplayWeatherDetails(viewModel: WeatherViewModel = koinViewModel()) {
    val uiState by viewModel.weatherResponseState.collectAsState()
    val searchText by viewModel.searchText.collectAsState()
    val isSearching by viewModel.isSearching.collectAsState()
    Scaffold(
        topBar = {
            SearchBar(
                query = searchText,
                onQueryChange = viewModel::updateSearchText,
                onSearch = {
                    viewModel.fetchWeatherDetails()
                },
                active = isSearching,
                onActiveChange = {

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(primnaryColor)
                    .padding(16.dp),
            ) {

            }
        }
    ) {
        when (uiState) {
            is UIState.Success -> {
                DisplaySuccess(weatherResponse = (uiState as UIState.Success).data, padding = it)
            }

            UIState.Empty -> {}
            is UIState.Failure -> DisplayError(error = "Error retrieving data")
            is UIState.Loading -> CircularProgressIndicator(
                Modifier
                    .fillMaxSize()
                    .padding(top = 120.dp, start = 32.dp)
            )
        }
    }
}

@Composable
private fun DisplayError(error: String) {
    val context = LocalContext.current
    Toast.makeText(context, error, Toast.LENGTH_LONG).show()
}

@Composable
private fun DisplaySuccess(weatherResponse: OpenWeatherResponseModel, padding: PaddingValues) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Card(
            shape = RoundedCornerShape(24.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 4.dp
            ),
            modifier = Modifier
                .padding(top = padding.calculateTopPadding() + 16.dp)
                .padding(horizontal = 16.dp)
                .widthIn(0.dp, 480.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .padding(horizontal = 16.dp)
            ) {

                Row {
                    AsyncImage(
                        model = getImageUrl(weatherResponse.weatherData?.first()?.icon),
                        contentDescription = "UI_Image_Weather_Icon",
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape)
                            .background(primnaryColor)
                            .padding(start = 0.dp)
                    )
                    Column {
                        Text(
                            text = "Region Name: ${weatherResponse.name} ",
                            textAlign = TextAlign.Center,
                            fontSize = 20.sp,
                            modifier = Modifier
                                .padding(start = 16.dp)
                                .padding(top = 4.dp)
                        )
                        Text(
                            text = "Latitude ${weatherResponse.coordinates?.lat ?: "unknown"} ",
                            fontSize = 14.sp,
                            modifier = Modifier
                                .padding(start = 16.dp)
                                .padding(top = 8.dp)
                        )
                        Text(
                            text = "Longitude ${weatherResponse.coordinates?.long ?: "unknown"} ",
                            fontSize = 14.sp,
                            modifier = Modifier
                                .padding(start = 16.dp)
                                .padding(top = 8.dp)
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                ) {
                    Text(text = "${weatherResponse.main?.temp?.toInt()}K", fontSize = 20.sp)
                    Text(
                        text = "${weatherResponse.main?.temp?.toCelsius()?.toInt()}\u00B0C",
                        fontSize = 20.sp
                    )
                    Text(
                        text = "${weatherResponse.main?.temp?.toFahrenheit()?.toInt()}\u00B0F",
                        fontSize = 20.sp
                    )
                }
                Row {
                    Text(
                        text = "Feels like ${
                            weatherResponse.main?.feelsLikeTemp?.toCelsius()?.toInt()
                        }°C. "
                    )
                    Text(
                        text = "${
                            weatherResponse.weatherData?.first()?.description.toString()
                                .replaceFirstChar {
                                    it.uppercase()
                                }
                        }."
                    )
                }
                Row {
                    Text(
                        text = "Pressure: ${weatherResponse.main?.pressure} hPa",
                        modifier = Modifier.padding(top = 8.dp)
                    )
                    Text(
                        text = "Humidity: ${weatherResponse.main?.humidity}%",
                        modifier = Modifier.padding(8.dp)
                    )
                }
                Text(
                    text = "Visibility: ${weatherResponse.visibility?.toKilometer().toString()}",
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }
        }
    }
}

private fun getImageUrl(iconCode: String?): String =
    StringBuilder("https://openweathermap.org/img/wn/")
        .append(iconCode)
        .append("@4x.png")
        .toString()


