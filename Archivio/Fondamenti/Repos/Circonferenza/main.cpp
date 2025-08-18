#include <iostream>
using namespace std;

const double pi = 3.14;
int r;

int main(){
    int circ;

    do{
        cout << "Inserisci un valore per il raggio: ";
        cin >> r;
        if (r > 0){
            circ = 2*r*pi;
        } else{
            cout << "La circonferenza deve essere positiva!" << endl;
        }
    }
    while (r < 0);
    cout << "La circonferenza misura: " << circ << endl;
}