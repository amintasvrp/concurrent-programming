package main

import (
	"fmt"
	"math/rand"
	"os"
	"strconv"
	"time"
)

func gateway(numReplicas int) int {
	counterCh := make(chan int, numReplicas+1)
	go timeout(counterCh)
	for i := 0; i < numReplicas; i++ {
		go request(counterCh)
	}

	return getTimer(counterCh, numReplicas)
}

func timeout(counter chan int) int {
	timer := 16
	time.Sleep(time.Duration(timer) * time.Second)
	counter <- (-1)
	return timer
}

func getTimer(counterCh chan int, numReplicas int) int {
	counter := 0
	for i := 0; i < numReplicas; i++ {
		elem := <-counterCh
		if elem == -1 {
			return elem
		} else {
			counter += elem
		}
	}
	return counter
}

func request(counter chan int) int {
	rand.Seed(time.Now().UTC().UnixNano())
	timer := rand.Intn(30) + 1
	fmt.Println(timer)
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
	fmt.Println("O tempo total foi:", gateway(getNumReplicas()))
}
