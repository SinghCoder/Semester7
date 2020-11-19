import os
dates = ['Oct28', 'Oct29', 'Nov02', 'Nov04', 'Nov09', 'Nov11', 'Nov11', 'Nov16']
dd = ['28-10', '29-10', '02-11', '04-11', '09-11', '11-11', '11-11', '16-11']
start = 25

for i in range(len(dates)):
    d = dates[i]
    ddd = dd[i]
    print('| {0}    | {1}-2020  |       | [link](Lec{0}{2}/README.md)  |'.format(start, ddd, d))
    # fname = 'Lec{}{}'.format(start, d)    
    # os.mkdir(fname)
    # with open('{}/README.md'.format(fname), 'w') as f:
    #     f.write('# Lecture {}\n\n'.format(start))
    start = start+1