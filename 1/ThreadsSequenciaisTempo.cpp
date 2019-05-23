#include <cstdio>
#include <iostream>
#include <pthread.h>
#include <mutex>
#include <time.h>
#include <fstream>

using namespace std;

long int counter = 0;
mutex mtx;

void* run(void* args){
    long int my_id;
    long int j;

    my_id = (long int) args;

    mtx.lock();
    for (j = 0; j < 1e7; j++) {
        counter++;
    }
    mtx.unlock();
    pthread_exit(reinterpret_cast<void *>(my_id));

    //printf("my_id= %ld j=%ld counter=%ld\n", my_id,j, counter);
}

int main(int argc, char *argv[]){
    int i;
    int k = 1;
    pthread_t pthreads[100];

    printf("Threads Sequenciais \n");

    ofstream file;
    file.open ("seqTime.txt");
    file << "threads,seqTime" << "\n";
  
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
