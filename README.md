# Stream Processing

1. Bloom Filters
Collection of input data is hashed (MurmurHash3) -> ***hash table*** (represents our input data). Then, we can check if the given item is in the data collection representing by ***hash table***. This method let us save memory and complexity of data.
```
scala MainBloomFilter <sizeOfHashTable> <"seeds"> <stream.txt> <elementsToCheck>
```

2. Mirsa - Gries Algorithm
The frequency algorithm which finds elements in the stream that occur more than ***streamLength/k***, ***k*** is the parameter of the algorithm. There is returned ***k-1*** elements.

```
scala MainMirsaGries <stream.txt> <k>
```
