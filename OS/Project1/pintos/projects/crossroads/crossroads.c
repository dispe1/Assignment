#include <stdio.h>
#include <string.h>
#include <list.h>

#include "threads/init.h"
#include "threads/malloc.h"
#include "threads/synch.h"
#include "threads/thread.h"

#include "devices/timer.h"

#include "projects/crossroads/crossroads.h"
#include "projects/crossroads/mapdata.h"

struct semaphore end;
struct semaphore delay;
struct semaphore crossroads_end;
//cross1:(2,2), cross2(2,4), cross3(4,2), cross4(4,4)
struct semaphore cross1, cross2, cross3, cross4;
//A1 : (4, 0), A2 : (4, 1), B1 : {6, 4}, B2 : {5,4}, C1 : {2, 6}, C2 : {2, 5}
struct semaphore A1, A2, B1, B2, C1, C2;

struct semaphore mutex_num_cars;
struct semaphore mutex_num_blocked_cars;
struct semaphore mutex_map;

int num_cars;
int num_blocked_cars = 0;
char map[7][7];

void run_crossroads(char **argv)
{
	int i;
	char *input = argv[1];
	char **parsed_input = NULL;

	printf("crossroads start.\n");

	input_parse(input, &parsed_input);
	crossroads_init();
	thread_create("crossroads_timer", PRI_DEFAULT, timer_thread, NULL);
	for (i = 0; i<num_cars; i++)
	{
		thread_create("car", PRI_DEFAULT, car_thread, parsed_input[i]);
	}
	sema_down(&crossroads_end);
	free(parsed_input);
	printf("crossroads end.\n");
	thread_exit();
}

void input_parse(char *input, char ***parsed_input)
{
	int i = 0;
	num_cars = strlen(input) / 4 + 1;
	*parsed_input = (char**)malloc((sizeof(char)) * (num_cars * 3));

	char *token, *save_ptr;
	for (token = strtok_r(input, ":", &save_ptr); token != NULL; token = strtok_r(NULL, ":", &save_ptr), i++)
	{
		(*parsed_input)[i] = token;
	}
}

void crossroads_init()
{
	map_init();
	sema_init(&end, 0);
	sema_init(&delay, 0);
	sema_init(&mutex_num_cars, 1);
	sema_init(&mutex_num_blocked_cars, 1);
	sema_init(&mutex_map, 1);
	sema_init(&cross1,1);
	sema_init(&cross2,1);
	sema_init(&cross3,1);
	sema_init(&cross4,1);
	sema_init(&crossroads_end, 0);
	sema_init(&A1, 1);
	sema_init(&A2, 1);
	sema_init(&B1, 1);
	sema_init(&B2, 1);
	sema_init(&C1, 1);
	sema_init(&C2, 1);
}

void timer_thread()
{
	int i;
	while (1)
	{
		timer_msleep(1000);
		map_print();
		if (num_cars == 0)
		{
			sema_up(&crossroads_end);
			thread_exit();
		}
		for (i = 0; i<num_cars - num_blocked_cars; i++)
		{
			sema_up(&end);
		}
	}
}

void end_check()
{
	int i;
	if (end.value == 0)
	{
		for (i = 0; i<num_cars - num_blocked_cars; i++)
		{
			sema_up(&delay);
		}
	}
}

void cross_check(struct car *car)
{
	struct position next_location = car->path[car->progress + 1];

	sema_down(&mutex_num_blocked_cars);
	num_blocked_cars++;
	sema_up(&mutex_num_blocked_cars);

	if (car->src == 'A') {
		if (next_location.row == 4 && next_location.col == 0) {
			sema_down(&A1);
		}
		if (next_location.row == 4 && next_location.col == 1) {
			sema_down(&A2);
			sema_up(&A1);
		}
		if (car->dest == 'B') {
			if (next_location.row == 4 && next_location.col == 2) {
				sema_down(&cross3);
				sema_up(&A2);
			}
			if (next_location.row == 5 && next_location.col == 2) {
				sema_up(&cross3);
			}
		}
		if (car->dest == 'C') {
			if (next_location.row == 4 && next_location.col == 2) {
				sema_down(&cross3);
				sema_down(&cross4);
				sema_up(&A2);
			}
			if (next_location.row == 4 && next_location.col == 3) {
				sema_up(&cross3);
			}
			if (next_location.row == 4 && next_location.col == 5) {
				sema_up(&cross4);
			}
		}
	}
	if (car->src == 'B') {
		if (next_location.row == 6 && next_location.col == 4) {
			sema_down(&B1);
		}
		if (next_location.row == 5 && next_location.col == 4) {
			sema_down(&B2);
			sema_up(&B1);
		}
		if (car->dest == 'A') {
			if (next_location.row == 4 && next_location.col == 4) {
				sema_down(&cross1);
				sema_down(&cross2);
				sema_down(&cross4);
				sema_up(&B2);
			}
			if (next_location.row == 3 && next_location.col == 4) {
				sema_up(&cross4);
			}
			if (next_location.row == 2 && next_location.col == 3) {
				sema_up(&cross2);
			}
			if (next_location.row == 2 && next_location.col == 1) {
				sema_up(&cross1);
			}
		}
		if (car->dest == 'C') {
			if (next_location.row == 4 && next_location.col == 4) {
				sema_down(&cross4);
				sema_up(&B2);
			}
			if (next_location.row == 4 && next_location.col == 5) {
				sema_up(&cross4);
			}
		}
	}
	if (car->src == 'C'){
		if (next_location.row == 2 && next_location.col == 6) {
			sema_down(&C1);
		}
		if (next_location.row == 2 && next_location.col == 5) {
			sema_down(&C2);
			sema_up(&C1);
		}
		if (car->dest == 'A') {
			if (next_location.row == 2 && next_location.col == 4) {
				sema_down(&cross1);
				sema_down(&cross2);
				sema_up(&C2);
			}
			if (next_location.row == 2 && next_location.col == 3) {
				sema_up(&cross2);
			}
			if (next_location.row == 2 && next_location.col == 1) {
				sema_up(&cross1);
			}
		}
		if (car->dest == 'B') {
			if (next_location.row == 2 && next_location.col == 4) {
				sema_down(&cross1);
				sema_down(&cross2);
				sema_down(&cross3);
				sema_up(&C2);
			}
			if (next_location.row == 2 && next_location.col == 3) {
				sema_up(&cross2);
			}
			if (next_location.row == 3 && next_location.col == 2) {
				sema_up(&cross1);
			}
			if (next_location.row == 5 && next_location.col == 2) {
				sema_up(&cross3);
			}
		}
	}
	sema_down(&mutex_num_blocked_cars);
	num_blocked_cars--;
	sema_up(&mutex_num_blocked_cars);
}

void car_thread(char *input)
{
	struct car car;
	car_init(input, &car);
	while (1)
	{
		cross_check(&car);
		go_one_block(&car);
		sema_down(&end);
		end_check();
		sema_down(&delay);
	}
}
void car_init(char *input, struct car *car)
{
	car->location.row = -2;
	car->location.col = -2;
	car->name = *input;
	car->src = *(input + 1);
	car->dest = *(input + 2);
	car->progress = -1;
	car->path = path_get(input + 1);
}

struct position* path_get(char *input)
{
	return path[*input - 'A'][*(input + 1) - 'A'];
}

void go_one_block(struct car *car)
{
	struct position pre_location;

	if (car->progress == -1)
	{
		pre_location.row = -2;
		pre_location.col = -2;
	}
	else
	{
	pre_location = car->path[car->progress];
	}

	car->location = car->path[car->progress + 1];
	car->progress++;
	map_update(car->name, pre_location, car->location);

	if (car->location.row < 0)
	{
		sema_down(&mutex_num_cars);
		num_cars--;
		sema_up(&mutex_num_cars);
		thread_exit();
	}
}

void map_init()
{
	int i, j;
	for (i = 0; i<7; i++)
	{
		for (j = 0; j<7; j++)
		{
			map[i][j] = map_draw_default[i][j];
		}
	}
}

void map_print()
{
	int i, j;
	for (i = 0; i<7; i++)
	{
		for (j = 0; j<7; j++)
		{
			printf("%c", map[i][j]);
		}
		printf("\n");
	}
	printf("\n");
}

void map_update(char name, struct position pre_location, struct position next_location)
{
	sema_down(&mutex_map);
	if (pre_location.row>=0)
	{
		if (map[pre_location.row][pre_location.col] == name) {
			map[pre_location.row][pre_location.col] = map_draw_default[pre_location.row][pre_location.col];
		}
	}
	if (next_location.row>=0)
	{
		map[next_location.row][next_location.col] = name;
	}
	sema_up(&mutex_map);
}