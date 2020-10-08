#include <cstdio>
#include <iostream>
#include <pthread.h>
#include <time.h>
#include <fstream>

long int counter = 0;
using namespace std;

void* run(void* args){
    long int my_id;
    long int j;

    my_id = (long int) args;

    for (j = 0; j < 1e7; j++) {
        counter++;
    }

    pthread_exit(reinterpret_cast<void *>(my_id));
   // printf("my_id= %ld j=%ld counter=%ld \n", my_id,j, counter);
}

int main(int argc, char *argv[]) {
    int i;
    int k = 1;
    pthread_t pthreads[100];

    printf("Threads Concorrentes \n");
    ofstream file;
    file.open ("concTime.txt");
    file << "threads,concTime" << "\n";

    while(k < 101){
        clock_t clock1;
        clock1 = clock();
        for(i = 0; i < k; i++){
            pthread_create(&pthreads[i], NULL, &run, (void*) i);
        }

        for (i = 0; i < k; i++) {
            pthread_join(pthreads[i], NULL);
        }
        printf("Tempo para %d threads: %fms\n", k, ((clock() - clock1) / (double) CLOCKS_PER_SEC));
        file << k << "," << (clock() - clock1) / (double) CLOCKS_PER_SEC << "\n";
        k++;
    }

    file.close();
    return 0;
}