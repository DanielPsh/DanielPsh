library(ggplot2)

data <- read.csv('crop_yield_dataset.csv')

str(data)

model <- lm(Value ~ Year, data = data)

summary(model)

ggplot(data, aes(x = Year, y = Value)) +
  geom_point(color = 'blue') + 
  geom_smooth(method = 'lm', se = FALSE, color = 'red') + 
  labs(title = "Pesticide Usage in Mainland China (1990-2016)",
       x = "Year",
       y = "Pesticide Usage (Value)") +
  theme_minimal() +
  annotate("text", x = 2010, y = max(data$Value), 
           label = paste("y =", round(coef(model)[1], 2), "+", 
                         round(coef(model)[2], 2), "* Year"), 
           color = "red", size = 5)


slope <- coef(model)[2]
cat("Interpretation of the slope coefficient:\n")
cat("The slope coefficient is", 
    round(slope, 2), 
    ". This indicates that for each additional year, the pesticide usage in mainland China increases by approximately", 
    round(slope, 2), "units.\n")
