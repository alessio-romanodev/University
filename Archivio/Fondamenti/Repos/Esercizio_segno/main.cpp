#include <iostream>
using namespace std;

int num;

void registra_numero(){
    cout << "Inserisci il numero che desideri analizzare: ";
    cin >> num;
}

int controlla_segno(){
    if (num > 0){
        return 0;
    }
    else if (num < 0){
        return 1;
    }
    else{
        return 2;
    }
}

int controlla_pari(){
    if (num%2 == 0){
        return 0;
    }
    else{
        return 1;
    }

}

int main(){
    int selector1, selector2;
    bool quit = false;

    cout << "Ciao, cosa vuoi fare?" << endl << "1) Inserire un numero" << endl << "2) Uscire dal programma" << endl;
    cin >> selector1;
    if (selector1 == 1){
        registra_numero();
    }
    else{
        cout << "Arrivederci!";
        return 0;
    }

    while (quit == false)
    {
        cout << "Cosa desideri fare?" << endl << "1) Controllare se è positivo, negativo o nullo" << endl << "2) Controllare se è pari o dispari" << endl << "3) Cambiare numero da analizzare" << endl << "4) Uscire dal programma" << endl;
        cin >> selector2;
        
        switch (selector2)
        {
        case 1:
        if (controlla_segno() == 0){
            cout << endl << "Il tuo numero è positivo" << endl << endl;
        } else if (controlla_segno() == 1){
            cout << endl << "Il tuo numero è negativo" << endl << endl;
        } else if (controlla_segno() == 2){
            cout << endl <<"il tuo numero è nullo" << endl << endl;
        }
            break;
        case 2:
        if (controlla_pari() == 0){
            cout << endl << "Il tuo numero è pari" << endl << endl;
        } else if (controlla_pari() == 1){
            cout << endl << "Il tuo numero è dispari" << endl << endl;
        }
            break;
        case 3:
            registra_numero();
            cout << endl << endl;
            break;
        case 4:
            quit = true;
        }
    }
    cout << "Arrivederci!";
}




    