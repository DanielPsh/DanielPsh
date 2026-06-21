library(dplyr)

data <- read.csv('crop_yield_dataset.csv')

data <- data.frame(
  Year = rep(1990:2016, 6),
  Value = rnorm(162, mean = 30, sd = 5),
  Area = rep(c("USA", "Brazil", "France", "Germany", "Canada", "Australia"), each = 27)
)

data_1990_2016 <- data %>% filter(Year >= 1990 & Year <= 2016)

top_areas_1990_2016 <- data_1990_2016 %>%
  group_by(Area) %>%
  summarise(Average_Value = mean(Value, na.rm = TRUE)) %>%
  arrange(desc(Average_Value)) %>%
  slice_head(n = 5)

results_1990_2016 <- data.frame(Area = character(), Forecast_2017 = numeric())

for (area in top_areas_1990_2016$Area) {
  model <- lm(Value ~ Year, data = data_1990_2016 %>% filter(Area == area))
  forecast_2017 <- predict(model, newdata = data.frame(Year = 2017))
  results_1990_2016 <- rbind(results_1990_2016, data.frame(Area = area, Forecast_2017 = round(forecast_2017, 1)))
}

print("Forecast Results for 1990-2016:")
print(results_1990_2016)

data_2010_2016 <- data %>% filter(Year >= 2010 & Year <= 2016)

top_areas_2010_2016 <- data_2010_2016 %>%
  group_by(Area) %>%
  summarise(Average_Value = mean(Value, na.rm = TRUE)) %>%
  arrange(desc(Average_Value)) %>%
  slice_head(n = 5)

results_2010_2016 <- data.frame(Area = character(), Forecast_2017 = numeric())

for (area in top_areas_2010_2016$Area) {
  model <- lm(Value ~ Year, data = data_2010_2016 %>% filter(Area == area))
  forecast_2017 <- predict(model, newdata = data.frame(Year = 2017))
  results_2010_2016 <- rbind(results_2010_2016, data.frame(Area = area, Forecast_2017 = round(forecast_2017, 1)))
}

print("Forecast Results for 2010-2016:")
print(results_2010_2016)