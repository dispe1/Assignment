/**
* " Package Delivery"
* - ./package_delivery <input_file_name>
*
* Im Gichan / 20145720
**/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#pragma warning(disable : 4996) 

int main(int argc, char* argv[]) {
	int i, j, p = 0, q, r, len, changed = 1;
	/* n = number of itmes, w = number of warehouse, l = number of locations */
	int n, w, l;
	/*min_total = final finishing time of all delivery*/
	int min_total = 99999999, min_time = 0;
	/*k = number of drones at each warehouse, d = destination location number of each item*/
	int *k, *d;
	/*warehouse = source warehouse of items, min_time = minimum delibery time of item*/
	int *warehouse, *item_time;
	/*drone = source drone in warehouse of items*/
	int *drone;
	/*Delivery time of the drones in the warehouse*/
	int **drone_time;
	/*t = delivery time (distance) between each warehouse and location*/
	int **t;
	char temp[100], buffer[100];
	char* ep;
	FILE *fp;

	if (argc != 2) {
		printf("The program execution type should be ./<your_program> <input_file>.\n");
		return 1;
	}
	/*open file*/
	fp = fopen(argv[1], "r");
	if (fp == NULL) {
		printf("File Open Error\n");
		return 1;
	}
	/*read W, number of warehouse*/
	if (fgets(temp, sizeof(temp), fp) != NULL) {
		w = strtol(temp, &ep, 10);
	}
	/*read L, number of locations*/
	if (fgets(temp, sizeof(temp), fp) != NULL) {
		l = strtol(temp, &ep, 10);
	}

	k = (int *)malloc(sizeof(int) * w);
	t = (int **)malloc(sizeof(int *)* w);
	t[0] = (int *)malloc(sizeof(int) * w * l);
	for (i = 1; i < w; i++) {
		t[i] = t[i - 1] + l;
	}
	/*read w[n], k[n], t[n][0]~t[n][L-1] (n : 0~W-1)*/
	for (n = 0; n < w; n++) {
		if (fgets(temp, sizeof(temp), fp) != NULL) {
			ep = strtok(temp, " ");
			while (ep != NULL) {
				ep = strtok(NULL, " ");
			}

			i = strtol(temp, &ep, 10) - 1;
			len = sprintf(buffer, "%d", i + 1) + 1;
			k[i] = strtol(temp + len, &ep, 10);
			if (k[i] > p) {
				p = k[i];
			}

			len += (sprintf(buffer, "%d", k[i]) + 1);
			for (j = 0; j < l; j++) {
				t[i][j] = strtol(temp + len, &ep, 10);
				len += (sprintf(buffer, "%d", t[i][j]) + 1);
			}
		}
	}

	drone_time = (int **)malloc(sizeof(int *) * w);
	drone_time[0] = (int*)malloc(sizeof(int) * w * p);
	for (i = 1; i < w; i++) {
		drone_time[i] = drone_time[i - 1] + p;
	}
	for (i = 0; i < w; i++) {
		for (j = 0; j < k[i]; j++) {
			drone_time[i][j] = 0;
		}
	}
	/*read N, number of items*/
	if (fgets(temp, sizeof(temp), fp) != NULL) {
		n = strtol(temp, &ep, 10);
	}

	d = (int *)malloc(sizeof(int) * n);
	warehouse = (int *)malloc(sizeof(int) * n);
	item_time = (int *)malloc(sizeof(int) * n);
	drone = (int *)malloc(sizeof(int) * n);

	/*read item[j], D[j](j : 0~N-1)*/
	for (j = 0; j < n; j++) {
		if (fgets(temp, sizeof(temp), fp) != NULL) {
			ep = strtok(temp, " ");
			while (ep != NULL) {
				ep = strtok(NULL, " ");
			}

			i = strtol(temp, &ep, 10) - 1;
			len = sprintf(buffer, "%d", i + 1) + 1;
			d[i] = strtol(temp + len, &ep, 10);
		}
	}
	/*initialize*/
	for (i = 0; i < n; i++) {
		warehouse[i] = -1;
		item_time[i] = 99999999;
		p = 99999999;
		for (j = 0; j < w; j++) {
			if (p > t[j][d[i] - 1]) {
				q = j;
				p = t[j][d[i] - 1];
			}
		}
		for (j = 0; j < k[q]; j++) {
			if (drone_time[q][j] + p < item_time[i]) {
				if (warehouse[i] != -1) {
					drone_time[warehouse[i]][drone[i]] -= t[warehouse[i]][d[i] - 1];
				}
				drone_time[q][j] += p;
				item_time[i] = drone_time[q][j];
				warehouse[i] = q;
				drone[i] = j;
			}
		}
	}
	/*find short delivery time*/
	while (changed) {
		changed = 0;
		min_time = 0;
		for (i = 0; i < n; i++) {
			for (j = 0; j < w; j++) {
				for (p = 0; p < k[j]; p++) {
					if (drone_time[j][p] + t[j][d[i] - 1] < item_time[i]) {
						drone_time[warehouse[i]][drone[i]] -= t[warehouse[i]][d[i] - 1];
						drone_time[j][p] += t[j][d[i] - 1];
						item_time[i] = drone_time[j][p];
						warehouse[i] = j;
						drone[i] = p;
						changed = 1;
					}
				}
			}

			r = 99999999;
			if (n > 100 && n <= 10000) {
				r = item_time[i];
			}

			for (j = 0; j < n && (min_time < r); j++) {
				if (n > 10000) {
					r = item_time[j];
				}
				if (item_time[i] < item_time[j]) {
					p = item_time[j];
				}
				else {
					p = item_time[i];
				}
				if ((drone_time[warehouse[i]][drone[i]] - t[warehouse[i]][d[i] - 1] + t[warehouse[i]][d[j] - 1]) < p &&
					(drone_time[warehouse[j]][drone[j]] - t[warehouse[j]][d[j] - 1] + t[warehouse[j]][d[i] - 1]) < p) {
					drone_time[warehouse[i]][drone[i]] -= (t[warehouse[i]][d[i] - 1] - t[warehouse[i]][d[j] - 1]);
					drone_time[warehouse[j]][drone[j]] -= (t[warehouse[j]][d[j] - 1] - t[warehouse[j]][d[i] - 1]);
					item_time[j] = drone_time[warehouse[i]][drone[i]];
					item_time[i] = drone_time[warehouse[j]][drone[j]];

					q = warehouse[i];
					warehouse[i] = warehouse[j];
					warehouse[j] = q;

					q = drone[i];
					drone[i] = drone[j];
					drone[j] = q;
					changed = 1;
				}
			}
			if (min_time < item_time[i]) {
				min_time = item_time[i];
			}
		}

		if (min_total > min_time) {
			min_total = min_time;
		}
	}

	printf("%d\n", n);
	for (i = 0; i < n; i++) {
	printf("%d %d\n", i + 1, warehouse[i] + 1);
	}
	printf("Total delivery time = %d\n", min_total);

	fclose(fp);
	free(k);
	free(t[0]);
	free(t);
	free(drone_time[0]);
	free(drone_time);
	free(d);
	free(warehouse);
	return 0;

}