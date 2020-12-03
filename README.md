# Stream Processing
There are two ways of run (sbt)
1. Bloom Filters
Collection of input data is hashed (MurmurHash3) -> ***hash table*** (represents our input data). Then, we can check if the given item is in the data collection representing by ***hash table***. This method let us save memory and complexity of data.
```
run BloomFilter <sizeOfHashTable: Int> <"seeds"> <elementsToCheck>
```

2. Mirsa - Gries Algorithm
The frequency algorithm which finds elements in the stream that occur more than ***streamLength/k***, ***k*** is the parameter of the algorithm. There is returned ***k-1*** elements.

```
run MirsaGries <k: Int>
```
