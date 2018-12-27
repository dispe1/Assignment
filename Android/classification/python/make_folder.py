import os
dir = 'result/'
f = open("labels.txt", 'r')
lines = f.readlines()
for line in lines:
    os.makedirs(dir + line.strip('\n'))
f.close()
