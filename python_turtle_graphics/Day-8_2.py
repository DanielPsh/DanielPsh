import random

famousSaying = ['After all, life is too short to be anything but happy',
                'Be patient and understanding. Life is too short to be vengeful or malicious',
                'Life is short. Stop worrying so much. Have fun. Be grateful. Be yourself.Don’t allow others to bring you down',
                'Life is short it is up to you to make it sweet',
                'Life is too short for long term grudges',
                'Life is a first impression. You get one shot at it. Make it everlasting',
                'Life is short. Be silly. Have fun. Love the people who treat you right. Forget the ones who don’t. Regret nothing',
                'Life is too short to spend time with people who suck the happiness out of you',
                'Enjoy every moment you have. Because in life, there are no rewinds, only flashbacks',
                'Life is too short to harbor any hostilities towards anybody']
while True:
    input('Press Enter to see today"s famous saying!!!');
    ret = random.choice(famousSaying)
    print("==========================================")
    print('₩n', ret, '₩n')
    print("==========================================")
