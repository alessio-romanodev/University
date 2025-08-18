#include <iostream>
#include <unistd.h>
using namespace std;

int numero;

void registra_numero(){
  cout << "Digitare un numero: " << endl;
  cin >> numero; 
}

int main(){
  string start;

  cout << "Benvenuto! Desideri procedere? [SI] [NO]" << endl;
  cin >> start;
  if (start == "SI" or start == "Si" or start == "si"){
    registra_numero();
    sleep(2);
    cout << "Il tuo numero è: " << numero << endl;
  }
  else{
    cout << "Arrivederci!" << endl;
    return 0;
  }
  cout << "Il tuo numero occupa: " << sizeof(numero) << " byte";
}