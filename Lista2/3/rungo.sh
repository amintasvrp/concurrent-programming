#!/bin/bash
for i in {1..5000..5}; do
  go run MultiCounter.go $i
done
