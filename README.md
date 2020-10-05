# Stream Processing

1. Bloom Filters
Represents data by so called the hash table. Each item is hashed and sign of item is saved. This method let us save memory and complexity of data.
```
scala MainBloomFilter <sizeOfHashTable> <numberOfHashFunction> <stream.txt> <elementsToCheck>
```

2. Mirsa - Gries Algorithm
The frequency algorithm finds elements in the stream which occur more than ***streamLength/k*** where ***k*** is the parameter of the algorithm. There is returned ***k-1*** elements.

```
scala MainMirsaGries <cars.txt> <k>
```
