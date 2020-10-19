import os

l = os.listdir(".")
c = 13
l.sort()

for d in l:
    if "Sept" in d:
        idx = d.index("Sept")
        day = int(d[idx+4:])
        if day > 17:
            print("| " + str(c) + "    | " + str(day) + "-09-2020  |       | [link](" + d + "/README.md) |")
            c = c+1
    elif "Oct" in d:
        idx = d.index("Oct")
        day = int(d[idx+3:])
        if day >= 10:
            print("| " + str(c) + "    | " + str(day) + "-10-2020  |       | [link](" + d + "/README.md)  |")
        else:
            print("| " + str(c) + "    | 0" + str(day) + "-10-2020  |       | [link](" + d + "/README.md)   |")
        c = c+1