library(ggplot2)
library(dplyr)

data <- read.csv('crop_yield_dataset.csv')

data <- data.frame(
  Year = rep(2000:2020, 3),
  Value = c(rnorm(21, mean = 30, sd = 5), rnorm(21, mean = 25, sd = 5), rnorm(21, mean = 20, sd = 5)),
  Area = rep(c("USA", "Brazil", "France"), each = 21)
)

areas <- c("USA", "Brazil", "France")
filtered_data <- data %>% filter(Area %in% areas)

models <- list()
slopes <- c()

for (area in areas) {
  model <- lm(Value ~ Year, data = filtered_data %>% filter(Area == area))
  models[[area]] <- model
  slopes[area] <- coef(model)[2]  
}

slope_df <- data.frame(Area = areas, Slope = slopes)

print(slope_df)

ggplot(filtered_data, aes(x = Year, y = Value, color = Area)) +
  geom_point() +
  geom_smooth(method = "lm", se = FALSE) +  # Add linear regression lines
  labs(title = "Pesticide Usage Over Time",
       x = "Year",
       y = "Pesticide Usage (Value)",
       color = "Area") +
  theme_minimal()