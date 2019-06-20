package main

import (
	"fmt"
	"math/rand"
	"os"
	"strconv"
	"sync"
	"time"
)

func gateway(numReplicas int) int {
	counterCh := make(chan int, numReplicas)
	var wg sync.WaitGroup
	wg.Add(numReplicas)

	for i := 0; i < numReplicas; i++ {
		go request(&wg, counterCh)
	}

	wg.Wait()

	close(counterCh)

	return getTimer(counterCh)
}

func getTimer(counterCh chan int) int {
	counter := 0
	for elem := range counterCh {
		counter += elem
	}
	return counter
}

func request(wg *sync.WaitGroup, counter chan int) int {
	rand.Seed(time.Now().UTC().UnixNano())
	timer := rand.Intn(30) + 1
	fmt.Println(timer)
	defer wg.Done()
	time.Sleep(time.Duration(timer) * time.Second)
	counter <- timer
	return timer
}

func getNumReplicas() int {
	if len(os.Args)-1 < 1 {
		fmt.Println("One arg is requerid for this program")
		os.Exit(1)
	}

	numReplicas, err := strconv.Atoi(os.Args[1])

	if err != nil {
		fmt.Printf("The input value is not a number\n")
		os.Exit(1)
	}

	return numReplicas
}

func main() {
	fmt.Println(gateway(getNumReplicas()))
}
