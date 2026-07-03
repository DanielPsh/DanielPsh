N = int(input())
if N < 1 or N > 100000:
  print('out of range')
  quit()
arraySwifts = [0] * N
arraySemaphores = [0] * N
w = input().split(' ')
m = input().split(' ')
arraySwifts[0] = int(w[0])
arraySemaphores[0] = int(m[0])
sw = arraySwifts[0]
sm = arraySemaphores[0]
if sw == sm:
  print('0')
for K in range (1, N):
  if sw == sm:
    print(K)
    quit()
  arraySwifts[K] = int(w[K])
  arraySemaphores[K] = int(m[K])
  sw = sw + int(arraySwifts[K])
  sm = sm + int(arraySemaphores[K])
