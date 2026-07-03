#UTF-8 encoding when using Korean

import random

print('가위바위보 게임을 시작합니다!')
game = ['가위','바위','보']
count = 0
win = 0

while True:
    computer = random.choice(game)
    answer = input('무엇을 낼까?')

    if answer == '그만':
        break

    count = count + 1

    print('컴퓨터: ' + computer)
    print('나: ' + answer)

    if computer == '가위':
        if answer == '가위':
            print('비겼다!')
        elif answer == '바위':
            print('이겼다!')
            win = win + 1
        elif answer == '보':
                print('졌다!')
    elif computer == '바위':
        if answer == '가위':
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
