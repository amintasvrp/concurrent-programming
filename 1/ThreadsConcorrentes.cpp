#include <cstdio>
#include <iostream>
#include <pthread.h>

long int counter = 0;

void* run(void* args){
    long int my_id;
    long int j;

    my_id = (long int) args;

    for (j = 0; j < 1e7; j++) {
        counter++;
    }

    printf("my_id= %ld j=%ld counter=%ld \n", my_id,j, counter);
    pthread_exit(reinterpret_cast<void *>(my_id));
}

int main(int argc, char *argv[]){
    int i;
    pthread_t pthreads[3];

    for (i = 0; i < 3; i++) {
        pthread_create(&pthreads[i], NULL, &run, (void*) i);
    }

    for (i = 0; i < 3; i++) {
        pthread_join(pthreads[i], NULL);
    }

    return 0;
}
