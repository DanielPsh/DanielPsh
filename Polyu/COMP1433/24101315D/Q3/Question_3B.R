library(readr)

temperature_data <- read_csv("temperature.csv")

temperature <- ts(temperature_data$AvgTemp, frequency = 12, start = c(2020, 1))

temperature_fluctuations <- diff(temperature)

plot(temperature_fluctuations, 
     main = "Month-to-Month Temperature Fluctuations", 
     xlab = "Month", 
     ylab = "Temperature Difference", 
     col = "red", 
     lwd = 2)

decomposed <- decompose(temperature)

plot(decomposed)