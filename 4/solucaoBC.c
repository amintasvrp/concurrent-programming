#include <stdlib.h>
#include <stdio.h>
#include <pthread.h>
#include <math.h>
#include <unistd.h>
#include <errno.h>
#include <time.h>

pthread_mutex_t mutex;
pthread_cond_t cond;
int total = 0;
int finished = 0;

void* request(){
    long int numberToSleep;

    numberToSleep = (rand() % 30) + 1;

    printf("Tempo: %ld\n", numberToSleep);

    sleep(numberToSleep);

    pthread_mutex_lock(&mutex);
    if(finished == 0)
        total += numberToSleep;
    pthread_mutex_unlock(&mutex);

    pthread_exit(NULL);
}

void* timeout(){
    sleep(8);
    pthread_mutex_lock(&mutex);
    if(finished == 0){
        finished = 1;
        total = -1;
    }
    pthread_cond_signal(&cond);
    pthread_mutex_unlock(&mutex);
 
    pthread_exit(NULL);
}

void* joiner(pthread_t pthreads[]){
    pthread_mutex_lock(&mutex);
    for (int i = 0; i < sizeof(pthreads); i++) {     
        pthread_join(pthreads[i], NULL);      
    }
    if(finished == 0){
        finished = 1;
    }
    pthread_cond_signal(&cond);
    pthread_mutex_unlock(&mutex);
 
    pthread_exit(NULL);
}

int gateway(int num_replicas) {
    pthread_t pthreads[num_replicas];
    pthread_t threadTimeout;
    pthread_t threadJoin;

    for (int i = 0; i < num_replicas; i++) {
        pthread_create(&pthreads[i], NULL, &request, NULL);
    }

    pthread_create(threadTimeout, NULL, &timeout, NULL);
    pthread_create(threadJoin, NULL, &joiner, pthreads);  

    pthread_mutex_lock(&mutex);
    while (finished == 0) {
        pthread_cond_wait(&cond, &mutex);
    }
    pthread_mutex_unlock(&mutex);

    printf("\nTempo Total: %d\n", total);
    return total;
}

int main(int argc, char *argv[]){
    int num_replicas;

    scanf("%d", &num_replicas);

    gateway(num_replicas);

    return 0;
}