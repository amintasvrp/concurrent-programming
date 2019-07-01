package main

import (
	"fmt"
	"sync"
	"os"
	"strconv"
	"runtime"
	"time"
)

var sumMem uint64 = 0
var qtSum uint64 = 0
var maxMem uint64 = 0

//Converte bytes em Megabytes
func bToMb(b uint64) uint64 {
    return b / 1024.0 / 1024.0
}

// Realiza a leitura da memória a cada segundo
func RegMemUsage() {
        for {
        	var m runtime.MemStats
	        runtime.ReadMemStats(&m)
	        var atualAlloc = bToMb(m.Alloc)

	        sumMem = atualAlloc + sumMem
	        qtSum = 1 + qtSum
	   
	        if maxMem < atualAlloc {
	        	maxMem = atualAlloc
	        }
	        time.Sleep(1 * time.Second)
        }
}


func main() {

	go RegMemUsage()
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
				if j % 100000 == 0 {
					// Ocasionalmente, imprime o resultado parcial da contagem para uma go routine
					fmt.Println("go routine",id,"countou até", j)
				}
			}
		}(atualId)
	}
	
	// Dorme até que o valor do WaitGroup chegue a zero
	wg.Wait()
	fmt.Println("Max:", maxMem,"mb","Media:", (sumMem/qtSum),"mb")
}