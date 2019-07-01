package main

import (
	"fmt"
	"sync"
	"os"
	"strconv"
)

func main() {
	//Cria um WaitGroup
	var wg sync.WaitGroup
	//Recebe o numero de go routines a executar no teste
	var routinesNumber, _ = strconv.Atoi(os.Args[1])
	
	for i := 0; i < routinesNumber; i++ {
		// Adiciona um ao WaitGroup para cada go routine inicializada
		wg.Add(1)
		var atualId = i + 1

		go func(id int) {
			// Decrementa o WaitGroup cada vez que uma das go routines finaliza
			defer wg.Done()

			for j := 0; j < 1000000; j++ {
				if j % 10000 == 0 {
					// Ocasionalmente, imprime o resultado parcial da contagem para uma go routine
					fmt.Println("go routine",id,"countou até", j)
				}
			}
		}(atualId)
	}
	
	// Dorme até que o valor do WaitGroup chegue a zero
	wg.Wait()
}