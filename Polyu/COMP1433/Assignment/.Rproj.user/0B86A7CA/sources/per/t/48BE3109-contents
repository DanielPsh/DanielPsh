library(readr)

temperature_data <- read_csv("temperature.csv")

print(head(temperature_data))
str(temperature_data)

column_names <- colnames(temperature_data)
print(column_names)

if ("AvgTemp" %in% column_names) {
  temperature <- ts(temperature_data$AvgTemp, frequency = 12, start = c(2020, 1))

  plot(temperature, main = "Monthly Temperature Over Time", 
       xlab = "Time (Years)", ylab = "AvgTemp", 
       col = "blue", lwd = 2)
} else {
  stop("Column 'Temperature' not found in the dataset.")
}