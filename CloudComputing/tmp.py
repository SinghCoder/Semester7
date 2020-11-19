import os
dates = ['Nov11', 'Nov17', 'Nov18']
dd = ['11-11', '17-11', '18-11']
start = 30

for i in range(len(dates)):
    d = dates[i]
    ddd = dd[i]
    print('| {0}    | {1}-2020  |       | [link](Lec{0}{2}/README.md)  |'.format(start, ddd, d))
    fname = 'Lec{}{}'.format(start, d)    
    os.mkdir(fname)
    with open('{}/README.md'.format(fname), 'w') as f:
        f.write('# Lecture {}\n\n'.format(start))
    start = start+1