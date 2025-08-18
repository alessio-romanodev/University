# Le tuple sono una sequenza di elementi con eventualmente tipi diversi, Ne sono esempi

#Tupla vuota
t = ()
#Assegnazione
t = (1,2,"fizz",4,"buzz") 
print(t[1:4])

#Swap con tuple
(x,y) = (5,10)
(x,y) = (y,x) 
print((x,y))

def get_data(aTuple):
    nums = ()
    words = ()
    for element in aTuple:
        nums = nums + (element[0],)
        if element[1] not in words:
            words = words + (element[1],)
    min_n = min(nums)
    max_n = max(nums)
    unique_strings = len(words)
    return (min_n, max_n, unique_strings)
            
def test_get_data():
    # Creiamo un input di esempio: una tupla di tuple, dove il primo elemento è un numero e il secondo una stringa
    input_data = ((1, "a"), (3, "b"), (2, "a"))
    expected = (1, 3, 2)
    # Calcoliamo il risultato usando la funzione
    result = get_data(input_data)
    # Visualizziamo i risultati
    print("Input:", input_data)
    print("Output:", result)
     print("Expected:", expected)
    # Verifichiamo che il risultato sia quello atteso
    assert result == expected, f"Expected {expected}, got {result}"
    print("Test passed!")

test_get_data()


