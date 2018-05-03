/**
 * "Quick Sort"
 * - ./basic_quick_sort <input_file_name> <N>
 * - measure running time of 'Quick Sort'
 *
 * Im Gichan / 20145720
  **/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#pragma warning(disable : 4996)  

// choose pivot and  always place it at first element of array
void choose_pivot (int *data, int n) {
	int pivot = rand() % n;		//choose pivot at random
	int temp;
	
	//Move the pivot first.
	temp = data[0];
	data[0] = data[pivot];
	data[pivot] = temp;
}

unsigned long quick_sort (int *data, int n) {
	unsigned long cnt = (n - 1); // number of comparisons
	int i = 1;	//index of the area of elements smaller than pivot.
	int j = 1;	//Variable for scanning
	int temp;

	if (n <= 1) {
		return 0;
	}
	
	// choose pivot and  always place it at first element of array
	choose_pivot(data, n);		
	
	for (j = 1; j < n; j++) {
		//if data[j] < pivot, move data[j] to the left of pivot.
		if (data[j] < data[0]) {
			temp = data[i];
			data[i] = data[j];
			data[j] = temp;
			i++;
		}
	}
	//Move the pivot to its rightful position.
	temp = data[0];
	data[0] = data[i - 1];
	data[i - 1] = temp;

	//Recursively sort Array on the left and right of the pivot.
	cnt = cnt + quick_sort(data, i - 1) + quick_sort(data + i, n - i);
	
	return cnt;
}

//Print an array from the beginning to N.
void PrintArr(int arr[], int n) {
	int i = 0;
	for (i = 0; i < n; i++) {
		if (arr[i] > 0) {
			printf("%d\n", arr[i]);
		}
	}
}



int main (int argc, char* argv[]) {
	char* ep;
	int N = strtol(argv[2], &ep, 10);
	int k = 0;
	int i = 0;
	int *Number = malloc(sizeof(int) * N);
	char temp[20];
	float duration = 0;
	FILE *fp;
	clock_t start, end;

	srand(time(NULL));

	if (argc != 3 || (argv[2] == ep)) {	
		printf("The program execution type should be ./<your_program> <input_file> <N>.\n");
		free(Number);
		return 1;
	}

	fp = fopen(argv[1], "r");
	if(fp == NULL) {
		printf("File Open Error\n");
		free(Number);
		return 1;
		}

	while ((fgets(temp, sizeof(temp), fp) != NULL) && (N > k)) {
		Number[k] = (int) strtol(temp, &ep, 10);
		if (temp != ep) {
			k++;
		}
	}

	//if N > K, then program should sort all K, and exactly K, numbers in the file correctly.
	if (N > k) {
		N = k;
		//Check if realloc() works well.
		if ((Number = realloc(Number, sizeof(int) * N)) == NULL) {
			printf("Out of storage\n");
			free(Number);
			fclose(fp);
			return 1;
		}
	}

	start = clock();
	while (duration < 1000) {
		quick_sort(Number, N);
		end = clock();
		duration = (float)(end - start) / CLOCKS_PER_SEC * 1000;
		i++;
	}
	duration = duration / i;

	PrintArr(Number, N);
	// Please keep these printf statements!
	printf("N = %7d,\tRunning_Time = %.3f ms\n", N, duration);

	fclose(fp);
	free(Number);

	return 0;
}

