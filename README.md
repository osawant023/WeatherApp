# Weather App

A simple and efficient weather app built with **MVVM** design pattern, **Hilt** for dependency injection, **Clean Architecture** principles, **Retrofit** for API calls, **Room Database** for caching city information, and **Lottie** animation for a smooth loading experience.

## Features
- **MVVM Design Pattern**: The app uses the Model-View-ViewModel (MVVM) architecture, which separates the UI (View) from the business logic (ViewModel) and data (Model).
- **Hilt for Dependency Injection**: **Hilt** is used to provide dependencies in a clean and efficient way, allowing for better code scalability and easier testing.
- **Clean Architecture**: Follows the principles of Clean Architecture to separate concerns, ensuring that the code is modular, testable, and maintainable.
- **Retrofit for API Calls**: **Retrofit** is used to make network requests to fetch weather data from a REST API.
- **Room Database Caching**: The app caches the weather information of searched cities locally using **Room Database** so that previously searched city data can be loaded quickly, even without an active internet connection.
- **Swipe Refresh**: Users can refresh the page to get updated weather data. The app checks if the data is out of date and fetches the latest data from the remote server.
- **Lottie Animation for Loading**: A **Lottie** animation is used as a simple loader while the data is being fetched from the network or local database.

## How it Works

### 1. **Fetching Weather Data**
- When the user enters a city name, the app first checks the **Room Database** to see if the weather data for that city is already cached.
- If cached data is found, it is displayed immediately to the user.
- If no cached data is found, the app makes a network request via **Retrofit** to fetch the weather data from a REST API.
- Once the data is retrieved, it is displayed on the screen and saved in the **Room Database** for future use.

### 2. **Updating Data**
- If the user wants to update the weather data, they can **refresh the page** by pulling down the screen or pressing a refresh button.
- The app will fetch the latest data from the remote API and update the UI with the new information.

### 3. **Local Caching with Room**
- Weather data for each city is stored in a **Room Database** for caching purposes. This ensures that previously searched city data can be quickly loaded without needing to make another network request.
- The database stores data such as city name, temperature, humidity, weather condition, and other related details.

### 4. **Lottie Animation**
- A **Lottie animation** is shown when the app is fetching data from either the network or the local database. This gives the user a smooth and interactive loading experience.

## Libraries and Technologies Used

- **MVVM** (Model-View-ViewModel) Architecture
- **Hilt** (Dependency Injection)
- **Room Database** (Local Storage for Caching)
- **Retrofit** (Networking and API Calls)
- **Lottie** (Loader Animation)
- **Clean Architecture** (Separation of Concerns)
  
## Setup Instructions

### Prerequisites

- Android Studio 4.0 or higher
- Java 8 or higher
- Internet connection (for API requests)

# APK 
![APK](https://github.com/osawant023/WeatherWise/blob/master/weather-app.apk)

# Video
![App Demo](https://github.com/osawant023/WeatherWise/blob/master/weather_sample_video.gif)

### Installation

1. Clone this repository:
   ```bash
   git clone https://github.com/yourusername/weather-app.git
