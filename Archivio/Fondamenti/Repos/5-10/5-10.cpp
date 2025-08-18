#include <iostream>
using namespace std;

int x,y,tmp;


int main(){
    cout << "Inserisci il primo numero: ";
    cin >> x;
    cout << "Inserisci il secondo numero: ";
    cin >> y;
    tmp = x;
    x = y;
    y = tmp;
    cout << "I numeri sono stati scambiati di posto: " << x << " " << y;
}