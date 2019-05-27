#include <stdlib.h>
#include <stdio.h>
#include <pthread.h>
#include <math.h>
#include <unistd.h>

pthread_mutex_t mutex;
pthread_cond_t cond;

void* request(){
    long int numberToSleep;

    numberToSleep = rand() % 30;

    printf("Tempo: %ld\n", numberToSleep);
    fflush(stdout);
    sleep(numberToSleep);
    fflush(stdout);
    pthread_mutex_lock(&mutex);
    printf("TERMINEI\nTempo: %ld\n", numberToSleep);
    pthread_mutex_unlock(&mutex);
    pthread_exit((void*) numberToSleep);
}

int gateway(int num_replicas) {
    pthread_t pthreads[num_replicas];
    long int retorno;

    for (int i = 0; i < num_replicas; i++) {
        pthread_create(&pthreads[i], NULL, &request, NULL);
    }

    for (int i = 0; i < num_replicas; i++) {
        pthread_join(pthreads[i], &retorno);
        printf("%ld\n", (long int)retorno);
        return retorno;
    }
    return -1;
}

int main(int argc, char *argv[]){
    int num_replicas;

    scanf("%d", &num_replicas);

    gateway(num_replicas);

    return 0;
}
