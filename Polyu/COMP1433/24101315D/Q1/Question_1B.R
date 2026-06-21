set.seed(1234)
n <- 10000 

samples <- rexp(n, rate = 1)

function_values <- exp(cos(samples)) - samples

expected_value <- mean(function_values)  
variance <- var(function_values)

cat("Estimated Expected Value:", expected_value, "\n")
cat("Variance of the Sampled Values:", variance, "\n")
