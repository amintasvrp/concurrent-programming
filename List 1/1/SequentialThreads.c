#include <stdio.h>
#include <pthread.h>

long int counter = 0;
pthread_mutex_t lock;

void* run(void* args){
    long int my_id;
    long int j;

    my_id = (long int) args;

    pthread_mutex_lock(&lock);
    for (j = 0; j < 1e7; j++) {
        counter++;
    }
    pthread_mutex_unlock(&lock);

    printf("my_id= %ld j=%ld counter=%ld\n", my_id,j, counter);
    pthread_exit(NULL);
}

int main(int argc, char *argv[]){
    int i;
    pthread_t pthreads[3];

    pthread_mutex_init(&lock, NULL);
    for (i = 0; i < 3; i++) {
        pthread_create(&pthreads[i], NULL, &run, (void*) i);
    }

    for (i = 0; i < 3; i++) {
        pthread_join(pthreads[i], NULL);
    }

    return 0;
}
