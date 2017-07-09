#include <stdio.h>
#include <memory.h>
#include <stdbool.h>


bool unsafeChecka(int prozessZaehler, int betriebsmittelZaehler, int gesamtanforderungsMatrix[prozessZaehler][betriebsmittelZaehler], int belegungsMatrix[prozessZaehler][betriebsmittelZaehler], int verfuegbarVektor[betriebsmittelZaehler], int restanforderungsMatrix[prozessZaehler][betriebsmittelZaehler], int freiVektor[betriebsmittelZaehler]){
    int freiVektorKopie[betriebsmittelZaehler];
    for (int i = 0; i<betriebsmittelZaehler; i++) {
        freiVektorKopie[i] = freiVektor[i];
    }
    bool Konstantins_Betriebssystem_istSchlecht = false;                 // MacOS
    bool rowDun[prozessZaehler];                                        // da checka for da dunness of da row (if row ain't dun it is tru (like a thug from the hood))
    for(int i = 0; i < prozessZaehler; i++){
        rowDun[i] = false;
    }
    int compareChecka = 0;
    int safeExecutionOrder[prozessZaehler];
    for(int i = 0; i < prozessZaehler; i++){                            // initialise array for order of the safe execution
        safeExecutionOrder[i] = -1;
    }
    while(!Konstantins_Betriebssystem_istSchlecht){
        Konstantins_Betriebssystem_istSchlecht = true;
        for(int i = 0; i < prozessZaehler; i++){
            if(rowDun[i] == false) {
                for (int j = 0; j < betriebsmittelZaehler; j++) {
                    if (freiVektorKopie[j] >= restanforderungsMatrix[i][j]) {
                        compareChecka++;
                    }
                }
                if (compareChecka == betriebsmittelZaehler) {
                    for (int k = 0; k < betriebsmittelZaehler; k++) {
                        freiVektorKopie[k] = freiVektorKopie[k] + belegungsMatrix[i][k];
                        rowDun[i] = true;
                        Konstantins_Betriebssystem_istSchlecht = false;
                        for(int l = 0; l < prozessZaehler; l++){
                            if(safeExecutionOrder[l] == -1){
                                safeExecutionOrder[l] = i;
                                break;
                            }
                        }
                    }
                }
                compareChecka = 0;
            }
        }

    }
    bool unsafe = false;
    for(int i = 0; i < prozessZaehler; i++){
        if(rowDun[i] == false){
            unsafe = true;
        }
    }
return unsafe;
}

int main(int argc, char *argv[]){

    FILE* fpin;
    FILE* fpout;


    fpin = fopen(argv[1], "r");

    if(fpin == NULL){
        printf("File does not exist \n");
        return 0;
    }

    int prozessZaehler = -1;
    int betriebsmittelZaehler = -1;
    fscanf(fpin, "%d %d", &prozessZaehler, &betriebsmittelZaehler);
    int gesamtanforderungsMatrix[prozessZaehler][betriebsmittelZaehler];
    int belegungsMatrix[prozessZaehler][betriebsmittelZaehler];
    int verfuegbarVektor[betriebsmittelZaehler];
    int restanforderungsMatrix[prozessZaehler][betriebsmittelZaehler];
    int freiVektor[betriebsmittelZaehler];
    for(int i = 0; i < prozessZaehler; i++){
        for(int j = 0; j < betriebsmittelZaehler; j++){
            fscanf(fpin, "%d", &gesamtanforderungsMatrix[i][j]);
        }
    }
    for(int i = 0; i < prozessZaehler; i++){
        for(int j = 0; j < betriebsmittelZaehler; j++){
            fscanf(fpin, "%d", &belegungsMatrix[i][j]);
        }
    }
    for(int i = 0; i < betriebsmittelZaehler; i++){
        fscanf(fpin, "%d", &verfuegbarVektor[i]);
    }
    for(int i = 0; i < prozessZaehler; i++){
        for(int j = 0; j < betriebsmittelZaehler; j++){
            restanforderungsMatrix[i][j] = gesamtanforderungsMatrix[i][j] - belegungsMatrix[i][j];
        }
    }
    for(int i = 0; i < betriebsmittelZaehler; i++){
        freiVektor[i] = verfuegbarVektor[i];
    }
    for(int i = 0; i < prozessZaehler; i++){
        for(int j = 0; j < betriebsmittelZaehler; j++){
            freiVektor[j] = freiVektor[j] - belegungsMatrix[i][j];
        }
    }


    fpout = fopen(argv[2], "w");

    fprintf(fpout, "Prozesse:  %d / Betriebsmittel:  %d\n\n Gesamtanforderungen:\n", prozessZaehler, betriebsmittelZaehler);

    for(int i = 0; i < prozessZaehler; i++){
        for(int j = 0; j < betriebsmittelZaehler; j++) {
            if (j == betriebsmittelZaehler - 1) {
                fprintf(fpout, "%d\n", gesamtanforderungsMatrix[i][j]);
            } else {
                fprintf(fpout, "%d  ", gesamtanforderungsMatrix[i][j]);
            }
        }
    }
    fprintf(fpout, "\n Belegungen:\n");
    for(int i = 0; i < prozessZaehler; i++){
        for(int j = 0; j < betriebsmittelZaehler; j++) {
            if (j == betriebsmittelZaehler - 1) {
                fprintf(fpout, "%d\n", belegungsMatrix[i][j]);
            } else {
                fprintf(fpout, "%d  ", belegungsMatrix[i][j]);
            }
        }
    }
    fprintf(fpout, "\n verfügbar:  ");
    for(int i = 0; i < betriebsmittelZaehler; i++){
        fprintf(fpout, "%d  ", verfuegbarVektor[i]);
    }
    fprintf(fpout, "\n\n Restanforderungen:\n");
    for(int i = 0; i < prozessZaehler; i++){
        for(int j = 0; j < betriebsmittelZaehler; j++) {
            if (j == betriebsmittelZaehler - 1) {
                fprintf(fpout, "%d\n", restanforderungsMatrix[i][j]);
            } else {
                fprintf(fpout, "%d  ", restanforderungsMatrix[i][j]);
            }
        }
    }
    fprintf(fpout, "\n\n frei:  ");
    for(int i = 0; i < betriebsmittelZaehler; i++){
        fprintf(fpout, "%d  ", freiVektor[i]);
    }


    //Banker's Algorithm

    bool Konstantins_Betriebssystem_istSchlecht = false;                 // MacOS
    bool rowDun[prozessZaehler];                                        // da checka for da dunness of da row (if row ain't dun it is tru)
    int freiVektorKopie[betriebsmittelZaehler];
    for (int x = 0; x< betriebsmittelZaehler; x++) {
        freiVektorKopie[x] = freiVektor[x];
    }
    for(int i = 0; i < prozessZaehler; i++){
        rowDun[i] = false;
    }
    int compareChecka = 0;
    int safeExecutionOrder[prozessZaehler];
    for(int i = 0; i < prozessZaehler; i++){                            // initialise array for order of the safe execution
        safeExecutionOrder[i] = -1;
    }
    while(!Konstantins_Betriebssystem_istSchlecht){
        Konstantins_Betriebssystem_istSchlecht = true;
        for(int i = 0; i < prozessZaehler; i++){
            if(rowDun[i] == false) {
                for (int j = 0; j < betriebsmittelZaehler; j++) {
                    if (freiVektorKopie[j] >= restanforderungsMatrix[i][j]) {
                        compareChecka++;
                    }
                }
                if (compareChecka == betriebsmittelZaehler) {               //war compareChecka == 3. Aber #BM muss nicht unbedingt gleich 3 sein
                    for (int k = 0; k < betriebsmittelZaehler; k++) {
                        freiVektorKopie[k] = freiVektorKopie[k] + belegungsMatrix[i][k];
                        rowDun[i] = true;
                        Konstantins_Betriebssystem_istSchlecht = false;
                        for(int l = 0; l < prozessZaehler; l++){
                            if(safeExecutionOrder[l] == -1){
                                safeExecutionOrder[l] = i;
                                break;
                            }
                        }
                    }
                }
                compareChecka = 0;
            }
        }

    }
    bool unsafe = false;
    for(int i = 0; i < prozessZaehler; i++){
        if(rowDun[i] == false){
            unsafe = true;
        }
    }
    if(unsafe == false){
        fprintf(fpout, "\n\n SICHER");
    }
    else{
        fprintf(fpout, "\n\n UNSICHER");
    }


    // Deadlock Avoidance


    char op;
    int processa;
    int ressourca;
    int numba;
    while(feof(fpin) == 0){
        fscanf(fpin, "%c %d %d %d", &op, &processa, &ressourca, &numba);
        if (op == 'A' || op == 'R') {
            if (op == 'A') {
                restanforderungsMatrix[processa][ressourca] -= numba;
                belegungsMatrix[processa][ressourca] += numba;
                freiVektor[ressourca] -= numba;
            }
            if (op == 'R') {
                //restanforderungsMatrix[processa][ressourca] += numba;
                //belegungsMatrix[processa][ressourca] -= numba;
                freiVektor[ressourca] += numba;
            }
            if (unsafeChecka(prozessZaehler, betriebsmittelZaehler, gesamtanforderungsMatrix, belegungsMatrix,
                             verfuegbarVektor, restanforderungsMatrix, freiVektor) == false) {

                fprintf(fpout, "\nOperation: %c %d %d %d", op, processa, ressourca, numba);
                fprintf(fpout, "\n\nRestanforderungen:\n");
                for (int i = 0; i < prozessZaehler; i++) {
                    for (int j = 0; j < betriebsmittelZaehler; j++) {
                        if (j == betriebsmittelZaehler - 1) {
                            fprintf(fpout, "%d\n", restanforderungsMatrix[i][j]);
                        } else {
                            fprintf(fpout, "%d  ", restanforderungsMatrix[i][j]);
                        }
                    }
                }
                fprintf(fpout, "\n\nf:  ");
                for (int i = 0; i < betriebsmittelZaehler; i++) {
                    fprintf(fpout, "%d  ", freiVektor[i]);
                }
                fprintf(fpout, "\n");
            }
            else {
                if (op == 'A') {
                    restanforderungsMatrix[processa][ressourca] += numba;
                    belegungsMatrix[processa][ressourca] -= numba;
                    freiVektor[ressourca] += numba;
                }
                else if (op == 'R') {
                    //restanforderungsMatrix[processa][ressourca] -= numba;
                    //belegungsMatrix[processa][ressourca] += numba;
                    freiVektor[ressourca] -= numba;
                }
            }
        }
    }

    fclose(fpin);
    fclose(fpout);





    return 0;
}