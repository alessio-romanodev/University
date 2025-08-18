#include <iostream>
using namespace std;

int numero_televisioni;
int stop = 0;
int seleziona_tv = 0;

class tv{
  public:
    int channel;
    int volume;
    bool power;
    string nome;
    void power_button_on(){
      power = true;
    }
    void power_button_off(){
      power = false;
    }
    void volume_up(){
      volume = volume + 1;
      if (volume > 30){
        cout << "Il volume è al massimo!";
        volume = 30;
      }
    };
    void volume_down(){
      volume = volume - 1;
      if (volume < 0){
        cout << "Il volume è al minimo!";
        volume = 0;
      }
    }
    void switch_channel(){
      cout << "seleziona il canale desiderato: ";
      cin >> channel;
      if (channel > 1000 or channel == 0){
        cout << "il canale selezionato non esiste";
        channel = 1;
      } 
    }
    tv(){
      power = false;
      volume = 10;
      channel = 1;
    }
};

void registra_televisioni(){
  cout << "Ciao, quante televisoni vuoi registrare? (Max 5): ";
  cin >> numero_televisioni;
}

string nomina_televisioni(int i){
  string nome;
    cout << "Televisione numero " << i + 1 << ": ";
    cin >> nome;
  return nome;
}

int main(){
  registra_televisioni();
  tv televisione[numero_televisioni];
  int selector, x;  

  cout << "Assegna un nome a ciascun televisore" << endl;
  for (int i = 0; i < numero_televisioni; i++){
    televisione[i].nome = nomina_televisioni(i);
  }

while (stop != 1){  
  cout << "A quale televisore vuoi collegarti?" << endl;
  for (int i = 0; i < numero_televisioni; i++){
    cout << i+1 << ")" << " " << televisione[i].nome << endl; 
  }
  cin >> x;

while (seleziona_tv != 1){
  cout << "Sei connesso alla tv " << televisione[x].nome << ", cosa vuoi fare? " << endl << "1) Accendi la tv" << endl << "2) Cambia televisione";
  cin >> selector;
  if (selector == 1){
    televisione[0].power_button_on();
    cout << "La tv è stata accesa!";
    selector = 0;
    seleziona_tv = 1;
  } else
  seleziona_tv = 0;
}

  while (televisione[x].power == true){
    cout <<"Sei connesso alla tv \"salone\", cosa vuoi fare? " << endl << "1) Alza il volume" << endl << "2) Abbassa il volume" << endl << "3) Cambia canale" << endl << "4) Spegni la tv" << endl;
    cin >> selector;
    switch (selector){
      case 1:
        televisione[x].volume_up();
        cout << "Il volume è: " << televisione[x].volume << endl;
        selector = 0;
        break;
      case 2:
        televisione[x].volume_down();
        cout << "Il volume è: " << televisione[x].volume << endl;
        selector = 0;
        break;
      case 3:
        televisione[x].switch_channel();
        cout << "Il canale attualmente selezionato è: " << televisione[x].channel << endl;
        selector = 0;
        break;
      case 4:
        televisione[x].power_button_off();
        cout << "La tv è stata spenta, arrivederci!" << endl;
        selector = 0;
        break;
    }
  } 
} 
}  

