/**
* " MSD Radix Sort"
* - ./msd_radix_sort <input_file_name> <N>
* - measure running time of 'MSD Radix Sort'
*
* Im Gichan / 20145720
**/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#pragma warning(disable : 4996) 
#define R 255
#define digit(data,d) ( (data>> (d * 8)) & 255 )

void insertion_sort(unsigned int arr[], int n) {
	int i, j;
	unsigned int temp;

	for (i = 1; i < n; i++) {
		temp = arr[i];
		j = i - 1;
		while (j >= 0 && temp < arr[j]) {
			arr[j + 1] = arr[j];
			j--;
		}
		arr[j + 1] = temp;
	}
}

void msd_radix_sort(unsigned int *data, unsigned int *aux, unsigned int first, unsigned int last, int d) {
	if (last <= first) return;
	unsigned int i, count[R + 2] = { 0 };

	if ((last - first + 1) < R + 1) {
		insertion_sort(data + first, last - first + 1);
		return;
	}

	for (i = first; i <= last; i++) {
		count[digit(data[i], d) + 1]++;
	}
	for (i = 0; i < R + 1; i++) {
		count[i + 1] += count[i];
	}
	for (i = first; i <= last; i++) {
		aux[count[digit(data[i], d)]++] = data[i];
	}
	for (i = first; i <= last; i++) {
		data[i] = aux[i - first];
	}
	if (d > 0) {
		msd_radix_sort(data, aux, first, first + count[0] - 1, d - 1);
		for (i = 0; i < R; i++) {
			msd_radix_sort(data, aux, first + count[i], first + count[i + 1] - 1, d - 1);
		}
	}
}

//Print an array from the beginning to N.
void PrintArr(unsigned int arr[], unsigned int n) {
	unsigned int i = 0;
	for (i = 0; i < n; i++) {
		if (arr[i] > 0) {
			printf("%u\n", arr[i]);
		}
	}
}

int main(int argc, char* argv[]) {
	char* ep;
	unsigned int N = (unsigned int)strtol(argv[2], &ep, 10);
	unsigned int k = 0;
	int i = 0;
	unsigned int *Number = malloc(sizeof(unsigned int) * N);
	unsigned int *aux = malloc(sizeof(unsigned int) * N);
	char temp[30];
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
	if (fp == NULL) {
		printf("File Open Error\n");
		free(Number);
		return 1;
	}

	while ((fgets(temp, sizeof(temp), fp) != NULL) && (N > k)) {
		Number[k] = (unsigned int)strtoul(temp, &ep, 10);

		if (temp != ep) {
			k++;
		}
	}

	//if N > K, then program should sort all K, and exactly K, numbers in the file correctly.
	if (N > k) {
		N = k;
		//Check if realloc() works well.
		if ((Number = realloc(Number, sizeof(unsigned int)*N)) == NULL) {
			printf("Out of storage\n");
			free(Number);
			fclose(fp);
			return 1;
		}
	}

	start = clock();
	while (duration < 1000) {
		msd_radix_sort(Number, aux, 0, N - 1, 3);
		end = clock();
		duration = (float)(end - start) / CLOCKS_PER_SEC * 1000;
		i++;
	}
	duration = duration / i;

	PrintArr(Number, N);
	// Please keep these printf statements!
	printf("N = %7d,\tRunning_Time = %.3f ms\n", N, duration);

	fclose(fp);
	free(aux);
	free(Number);
	return 0;
}