set.seed(1234)

n <- 10000  

samples_importance <- rexp(n, rate = 2)

weights <- dexp(samples_importance, rate = 1) / dexp(samples_importance, rate = 2)

function_values_importance <- exp(cos(samples_importance)) - samples_importance

expected_value_importance <- sum(weights * function_values_importance) / sum(weights)

weighted_values <- weights * function_values_importance
variance_importance <- sum((weighted_values - expected_value_importance)^2) / sum(weights)

cat("Estimated Expected Value (Importance Sampling):", expected_value_importance, "\n")
cat("Variance of the Sampled Values (Importance Sampling):", variance_importance, "\n")

library(ggplot2)

data <- data.frame(
  samples = samples_importance,
  target_function = exp(cos(samples_importance)) - samples_importance,
  weights = weights
)

p1 <- ggplot(data, aes(x = samples)) +
  geom_density(aes(y = ..density.., fill = "Exp(2)"), alpha = 0.5) +
  geom_density(aes(y = ..density.. * sum(weights) / max(..density..), 
                   color = "Weighted Function"), size = 1) +
  labs(title = "Importance Sampling: Exp(2) and Target Function",
       x = "Sample Value", y = "Density") +
  scale_fill_manual(name = "Distributions", 
                    values = c("Exp(2)" = "lightblue")) +
  scale_color_manual(name = "Distributions", 
                     values = c("Weighted Function" = "red")) +
  theme_minimal()

print(p1)
