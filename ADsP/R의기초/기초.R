class('abc')
class("abc")
class('1')
class('TRUE')
class(TRUE)
class(FALSE)
sqrt(4)
class(NA)
class(NULL)
string1 <- 'abc'
'data' -> string2
string1
string2
number1 <<- 15
Inf ->> number2
logical = NA
string1 == 'abc'
string1 != 'abcd'
string2 > 'DATA'
number1 <= 15
is.na(logical)
is.null(NULL)
exp(2)
a1 <- (1:6)
dim(a1) <- c(2,3)
a1
m1 <- matrix(c(1:6), nrow = 2)
colnames(m1) <- c('c1', 'c2', 'c3')
rownames(m1) <- c('r1', 'r2')
m1
colnames(m1)
rownames(m1)
df1 <- data.frame(x = c(1,2,3), y = c(4,5,6))
colnames(df1) <- c('c1', 'c2')
rownames(df1) <- c('r1','r2','r3')
df1
colnames(df1)
rownames(df1)
m2 <- matrix(c('180','20','M','150','21','F'), nrow = 2, byrow = T)
colnames(m2) <- c('height', 'age', 'gender')
rownames(m2) <- c('Daniel', 'Bobo')
m2
v1 <- c(3,6,9,12)
v1[2]
m1 <- matrix(c(1:6), nrow = 3)
m1[2,2]
colnames(m1) <- c('c1','c2')
m1[ , 'c1']
rownames(m1) <- c('r1','r2','r3')
m1['r3','c2']
v1 <- c(1:6)
v2 <- c(7:12)
df1 <- data.frame(v1,v2)
df1$v1
df1$v2[3]
v1 <- c(1,2,3)
v2 <- c(4,5,6)
rbind(v1,v2)
cbind(v1,v2)
v1 <- c(1,2,3)
v2 <- c(4,5,6,7,8) 
rbind(v1,v2)
# for-loop
for(i in 1:3){
  print(i)
}
data <- c("a","b","c")
for(i in data){
  print(i)
}
#while-loop
i <- 0
while(i < 5){
  print(i)
  i <- i + 1
}
# if-loop
number <- 5
if(number < 5){
  print('number는 5보다 작다.')
} else if(number > 5){
  print('number는 5보다 크다.')
} else{
  print('number는 5와 같다.')
}
number <-3
if(number < 5){
  print('number는 5보다 작다.')
} else if(number > 5){
  print('number는 5보다 크다.')
} else{
  print('number는 5와 같다.')
}

number <-7
if(number < 5){
  print('number는 5보다 작다.')
} else if(number > 5){
  print('number는 5보다 크다.')
} else{
  print('number는 5와 같다.')
}
# functions
comparedTo5 <- function(number){
  if(number < 5){
    print('number는 5보다 작다.')
  } else if(number > 5){
    print('number는 5보다 크다.')
  } else{
    print('number는 5와 같다.')
  }
}
comparedTo5(10)
comparedTo5(3)
comparedTo5(5)

pi
data <- 'This is a pen'
tolower(data)
toupper(data)
nchar(data)
substr(data, 9, 13)
strsplit(data, 'is')
grepl('pen',data)
gsub('pen','banana', data)

x <- c(1:12)
head(x, 5)
tail(x, 5)
quantile(x)

# 데이터 잔처리
df1 <- data.frame(x = c(1,1,1,2,2), y = c(2,3,4,3,3))
df2 <- data.frame(x = c(1,2,3,4), z = c(5,6,7,8))
subset(df1, x==1)
merge(df1,df2,by=c('x'))
# 1은 각 행에 함수를 적용, 2는 각 열에 함수를 적용
apply(df1,2,sum)

#정규분포 (mean = 0, sd = 1이다)
dnorm
rnorm
pnorm
qnorm

#표본추출
rnuif
sample

# 날짜
Sys.Date()
Sys.time()
as.Date("2020-01-01")
format(Sys.Date(), '%y/%m/%d')
format(Sys.Date(), '%A')
unclass(Sys.time())
as.POSIXct(1784355397, origin = '1970-01-01')

# 산점도 
x <- c(1:10)
y <- rnorm(10)
plot(x,y,type = 'l', xlim = c(-2,12), ylim = c(-3,3), xlab = 'X axis', ylab = 'Y axis', main = 'Test plot')
abline(v = c(1,10), col = 'blue')

data <- c(3,4,2,2)
median(data)
