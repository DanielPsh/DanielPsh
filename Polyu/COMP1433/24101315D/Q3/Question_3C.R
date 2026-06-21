library(forecast)

temperature_data <- read.csv("temperature.csv")
temperature_data$Month <- as.Date(paste0(temperature_data$Month, "-01"))
temperature_ts <- ts(temperature_data$AvgTemp, start = c(2020, 1), frequency = 12)

hw_model <- HoltWinters(temperature_ts)  
hw_fitted <- fitted(hw_model)[,1]    
hw_forecast <- forecast(hw_model, h = 12)

arima_model <- auto.arima(temperature_ts)
arima_fitted <- fitted(arima_model)      
arima_forecast <- forecast(arima_model, h = 12)

plot(temperature_ts, main = "Temperature Forecasting (Holt-Winters vs ARIMA)", 
     ylab = "Average Temperature", xlab = "Time", col = "black", lwd = 2)

lines(hw_fitted, col = "blue", lty = 2)
lines(arima_fitted, col = "green", lty = 2)

lines(hw_forecast$mean, col = "blue", lwd = 2)
lines(arima_forecast$mean, col = "green", lwd = 2)

legend("topleft", legend = c("Original", "HW Fitted", "ARIMA Fitted", "HW Forecast", "ARIMA Forecast"),
       col = c("black", "blue", "green", "blue", "green"),
       lty = c(1, 2, 2, 1, 1), lwd = c(2, 1, 1, 2, 2))