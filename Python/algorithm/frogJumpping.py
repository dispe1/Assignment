# Problem: https://www.codeground.org/practice/practiceProblemViewNew

import sys
inf = open('input.txt')
#inf = sys.stdin

T = int(inf.readline())

for t in range(0, T):
    N = int(inf.readline())
    arr = list(map(int, inf.readline().rstrip().split()))
    K = int(inf.readline())
    arr.insert(0, 0)

    Answer = 0
    cur = 0
    while cur < N:
        next = arr[cur] + K
        pre = cur

        while arr[cur] <= next and cur < N:
            cur += 1
        if arr[cur] > next:
            cur -= 1

        if pre == cur:
            Answer = -1
            break

        Answer += 1

    print('Case #%d' %(int(t)+1))
    print(Answer)
inf.close()
