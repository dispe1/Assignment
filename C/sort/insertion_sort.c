/**
* "Insertion Sort"
* - ./basic_quick_sort <N> <input_file_name>
* - measure running time of 'Insertion Sort'
*
* Im Gichan / 20145720
**/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#pragma warning(disable : 4996)  

void InsertionSort(int arr[], int n) {
	int i, j;
	int temp;

	for (i = 1; i < n; i++) {
		temp = arr[i];
		j = i-1;
		while (j >= 0 && temp > arr[j]) {
			arr[j+1] = arr[j];
			j--;
		}
		arr[j + 1] = temp;
	}
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


int main(int argc, char* argv[]) {
	char* ep;
	int N = strtol(argv[1], &ep, 10);
	int k = 0;
	int i = 0;
	int *Number = malloc(sizeof(int) * N);
	char temp[30];
	float duration = 0;
	FILE *fp;
	clock_t start, end;

	srand(time(NULL));

	if (argc != 3 || (argv[1] == ep)) {
		printf("The program execution type should be ./<your_program> <N> <input_file>.\n");
		free(Number);
		return 1;
	}

	fp = fopen(argv[2], "r");
	if (fp == NULL) {
		printf("File Open Error\n");
		free(Number);
		return 1;
	}

	while ((fgets(temp, sizeof(temp), fp) != NULL) && (N > k)) {
		Number[k] = (int)strtol(temp, &ep, 10);
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
		InsertionSort(Number, N);
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
