package main

import (
	"fmt"
	"math/rand"
	"os"
	"strconv"
	"time"
)

func gateway(numReplicas int) int {
	operationDone := make(chan bool)
	for i := 0; i < numReplicas; i++ {
		go request(operationDone)
	}
	<-operationDone
	return 0
}

func request(operationDone chan bool) int {
	timer := rand.Intn(30) + 1
	fmt.Println(timer)
	time.Sleep(time.Duration(timer) * time.Second)
	fmt.Println("O menor tempo foi: ", timer)
	operationDone <- true
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
	gateway(getNumReplicas())
}
