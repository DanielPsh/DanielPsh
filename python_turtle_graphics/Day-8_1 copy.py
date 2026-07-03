#UTF-8 encoding when using Korean

import random

print('scissor, paper, rock!')
game = ['scissor','paper','rock']
count = 0
win = 0

while True:
    computer = random.choice(game)
    answer = input('Input?')

    if answer == 'Stop':
        break

    count = count + 1

    print('Computer: ' + computer)
    print('Me: ' + answer)

    if computer == 'scissor':
        if answer == 'scissor':
            print('Draw!')
        elif answer == 'Rock':
            print('Win!')
            win = win + 1
        elif answer == 'Paper':
                print('Lose!')
    elif computer == 'Rock':
        if answer == 'Scissor':
            print('졌다!')
        elif answer == '바위':
            print('비겼다!')
        elif answer == '보':
            print('이겼다!')
            win = win + 1
    elif computer == '보':
        if answer == '가위':
            print('이겼다!')
            win = win + 1
        elif answer == '바위':
            print('졌다!')
        elif answer == '보':
            print('비겼다!')
            
print(count, '번 승부 중', win, '번 이겼습니다!')
