#Le liste sono mutabili e si dichiarano nel seguente modo
#Calcolo della somma di una lista

test = [1,2,3,4,5]

def sum(L):
    total = 0
    for item in L:
        total = total + item
    return total

print(sum(test))
#Aggiunge al termine l'elemento 6
test.append(6) 

