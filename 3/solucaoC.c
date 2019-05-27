#include <stdlib.h>
#include <stdio.h>
#include <pthread.h>
#include <math.h>
#include <unistd.h>

pthread_mutex_t mutex;
pthread_cond_t cond;

int request(){
    int numberToSleep;

    numberToSleep = rand() % 30;

    printf("Tempo: %d\n", numberToSleep);
    fflush(stdout);
    sleep(numberToSleep);
    fflush(stdout);
    pthread_mutex_lock(&mutex);
    printf("TERMINEI\nTempo: %d\n", numberToSleep);
    pthread_cond_signal(&cond);
    pthread_mutex_unlock(&mutex);
    pthread_exit(NULL);
    
    return numberToSleep;
}

void gateway(int num_replicas){
    pthread_t pthreads[num_replicas];

    for (int i = 0; i < num_replicas; i++) {
        pthread_create(&pthreads[i], NULL, &request, NULL);
    }

    for (int i = 0; i < num_replicas; i++) {
        pthread_join(pthreads[i], NULL);
    }

}

int main(int argc, char *argv[]){
    int num_replicas;

    scanf("%d", &num_replicas);

    gateway(num_replicas);

    return 0;
}
