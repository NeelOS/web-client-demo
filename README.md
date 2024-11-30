# web-client-demo
Demo application for testing Web Client features

# /getWeatherData
This controller get the weather data from weather api using a simple web client.
Authorization used is api key which is static.
API key is hard coded in application.properties file.
Login to https://www.weatherapi.com/my/ to get the new api key in case of existing one gets expired after some day.
use id iiest.indraneel@gmail.com and password you all ready know.
Endpoint : http://localhost:8084/web-client-demo/getWeatherData


# /getUserProfile
This Controller gets basic user profile for the user whose client id and client secret is setup in the Azure portal and
added in application.properties file. This example show how you can write web client which internally takes care of calling the 
Oauth token api and manages the token in memory unti it exipires. More details of azure token setup is provided the word doc in this
project.
