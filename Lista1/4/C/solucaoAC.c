#include <stdlib.h>
#include <stdio.h>
#include <pthread.h>
#include <math.h>
#include <unistd.h>

pthread_mutex_t mutex;
pthread_cond_t cond;
int first = 0;

void* request(){
    long int numberToSleep;

    numberToSleep = (rand() % 30) + 1;

    printf("Tempo: %ld\n", numberToSleep);

    sleep(numberToSleep);

    pthread_mutex_lock(&mutex);
    first = numberToSleep;
    pthread_cond_signal(&cond);
    pthread_mutex_unlock(&mutex);

    pthread_exit(NULL);
}

void* timeout(){
    sleep(8);
    pthread_mutex_lock(&mutex);
    first = -1;
    pthread_cond_signal(&cond);
    pthread_mutex_unlock(&mutex);
 
    pthread_exit(NULL);
}

int gateway(int num_replicas) {
    pthread_t pthreads[num_replicas];
    pthread_t threadTimeout;
    pthread_cond_init(&cond, NULL);

    for (int i = 0; i < num_replicas; i++) {
        pthread_create(&pthreads[i], NULL, &request, NULL);
    }

    pthread_create(&threadTimeout, NULL, &timeout, NULL);

    pthread_mutex_lock(&mutex);
    while (first == 0) {
        pthread_cond_wait(&cond, &mutex);
    }
    pthread_mutex_unlock(&mutex);
    
    printf("\nTempo do Primeiro: %d\n", first); // Se retornar -1, é porque deu timeout
    return first;
}

int main(int argc, char *argv[]){
    int num_replicas;

    scanf("%d", &num_replicas);

    gateway(num_replicas);

    return 0;
}