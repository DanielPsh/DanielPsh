set.seed(1234)
lambda_values <- c(1, 2, 3, 4, 5)

generate_exponential_samples <- function(lambda, n) {
  u <- runif(n)
  samples <- -log(1 - u) / lambda
  return(samples)
}

par(mfrow=c(3, 2))  

for (lambda in lambda_values) {
  samples <- generate_exponential_samples(lambda, 100)  
  hist(samples, 
       main = paste("Histogram of Exponential Samples (λ =", lambda, ")"),
       xlab = "Value", 
       breaks = 10, 
       col = "lightblue", 
       border ="black")
}
