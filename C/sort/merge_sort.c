/**
* "Merge Sort"
* - ./merge_sort <N> <input_file_name>
* - measure running time of 'Merge Sort'
*
* Im Gichan / 20145720
**/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#pragma warning(disable : 4996)  

void Merge(int arr[], int TempArr[], int left, int mid, int right) {
	int i = left;
	int j = mid + 1;
	int m = 0;
	int n = 0;

	for (m = n; m <= right - left; m++) {
		while (i <= mid && j <= right) {
			if (arr[i] < arr[j]) {
				TempArr[n] = arr[j];
				j++;
			}
			else {
				TempArr[n] = arr[i];
				i++;
			}
			n++;
		}

		if (i > mid) {
			while (j <= right) {
				TempArr[n] = arr[j];
				j++;
				n++;
			}
		}
		else {
			while (i <= mid) {
				TempArr[n] = arr[i];
				i++;
				n++;
			}
		}
	}

	for (m = left; m <= right; m++) {
		arr[m] = TempArr[m - left];
	}
}

void MergeSort(int arr[], int TempArr[], int left, int right) {
	int mid;

	if (left < right) {
		mid = (left + right) / 2;

		MergeSort(arr, TempArr, left, mid);
		MergeSort(arr, TempArr, mid + 1, right);
		Merge(arr, TempArr, left, mid, right);
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
	int *Number = (int*)malloc(sizeof(int) * N);
	int *TempArr = (int*)malloc(sizeof(int) * N);
	char temp[20];
	float duration = 0;
	FILE *fp;
	clock_t start, end;

	srand(time(NULL));

	if (argc != 3 || (argv[1] == ep)) {
		printf("The program execution type should be ./<your_program> <N> <input_file> .\n");
		free(Number);
		free(TempArr);
		return 1;
	}

	fp = fopen(argv[2], "r");
	if (fp == NULL) {
		printf("File Open Error\n");
		free(Number);
		free(TempArr);
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
			free(TempArr);
			fclose(fp);
			return 1;
		}
	}

	start = clock();
	while (duration < 1000) {
		MergeSort(Number, TempArr, 0, N - 1);
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
	free(TempArr);

	return 0;
}
