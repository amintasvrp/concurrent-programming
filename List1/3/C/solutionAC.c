#include <stdlib.h>
#include <stdio.h>
#include <pthread.h>
#include <math.h>
#include <unistd.h>

pthread_mutex_t mutex;
pthread_cond_t cond;
int first = 0;

void* request(void* args){
    long int numberToSleep;

    numberToSleep = (rand() % 30) + 1;

    printf("Tempo: %ld\n", numberToSleep);

    sleep(numberToSleep);

    pthread_mutex_lock(&mutex);
    first = numberToSleep;
    pthread_cond_signal(&cond);
    printf("\nTempo do Primeiro: %ld\n", numberToSleep);
    pthread_mutex_unlock(&mutex);

    pthread_exit(NULL);
}

int gateway(int num_replicas) {
    pthread_t pthreads[num_replicas];
    pthread_cond_init(&cond, NULL);

    for (int i = 0; i < num_replicas; i++) {
        pthread_create(&pthreads[i], NULL, &request, NULL);
    }

    pthread_mutex_lock(&mutex);
    while (first == 0) {
        pthread_cond_wait(&cond, &mutex);
    }
    pthread_mutex_unlock(&mutex);

    return first;
}

int main(int argc, char *argv[]){
    int num_replicas;

    scanf("%d", &num_replicas);

    gateway(num_replicas);

    return 0;
}