#include<iostream>
#include<stdlib.h>
#include<stdio.h>
#include <fstream>
#include <unistd.h>
#include "matrixMultiplication.h"
#include <sys/time.h>


struct timeval start, stop;

size_t getMemoryUsage() {
    std::ifstream statm("/proc/self/statm");
    size_t totalMemoryPages;
    statm >> totalMemoryPages;
    size_t pageSize = sysconf(_SC_PAGESIZE); 
    return totalMemoryPages * pageSize;      
}

int main(int argc, char** argv) {
    int sizes[] = {100, 200, 300, 500, 1000};
    for(int i = 0; i < 5; i++){
    	int n = sizes[i];
    	size_t memory_before= getMemoryUsage();    
    	gettimeofday(&start, NULL);
    	matrixMultiplication(n);
    	gettimeofday(&stop, NULL);   
    	size_t memory_after= getMemoryUsage();
    	double diff = stop.tv_sec - start.tv_sec + 1e-6 * (stop.tv_usec - start.tv_usec); 
    	printf("\nExecution time for %d size: %0.6f\n", n, diff);
    	std::cout << "Memory Used for "<<n<<" size: " << (memory_after - memory_before) << " Bytes" << std::endl;
    }
    
    return 0;
}
