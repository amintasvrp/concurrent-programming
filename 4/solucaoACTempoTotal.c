#include <stdlib.h>
#include <stdio.h>
#include <pthread.h>
#include <math.h>
#include <unistd.h>

pthread_mutex_t mutex;
int total = 0;
int qtdThreads = 0;

void* request(void* args){
    long int numberToSleep;

    numberToSleep = (rand() % 30) + 1;

    printf("Tempo: %ld\n", numberToSleep);

    sleep(numberToSleep);

    pthread_mutex_lock(&mutex);
    total += numberToSleep;
    if(total < 8){
        qtdThreads++;
    }
    pthread_mutex_unlock(&mutex);

    pthread_exit(NULL);
}

int gateway(int num_replicas) {
    pthread_t pthreads[num_replicas];

    for (int i = 0; i < num_replicas; i++) {
        pthread_create(&pthreads[i], NULL, &request, NULL);
    }

    for (int i = 0; i < num_replicas; i++) {
        pthread_join(pthreads[i], NULL);
        if(qtdThreads == 0){
            printf("Nenhum terminou em menos de 8 segundos\n");
            return -1;
        }
    }
    
    printf("\nTempo Total: %d\n", total);
    return total;
}

int main(int argc, char *argv[]){
    int num_replicas;

    scanf("%d", &num_replicas);

    gateway(num_replicas);

    return 0;
}
