#include <stdio.h>
#include <string.h>

#include "threads/thread.h"
#include "threads/synch.h"
#include "devices/timer.h"
#include "projects/scheduling/schedulingtest.h"


#define MAX_THREAD_CNT 10

struct thread_info 
{
	int id;
	int64_t start_time;
	int tick_count;
	struct semaphore sema_join;
};

static void load_thread (void *__ti) 
{
	struct thread_info *ti = (struct thread_info *) __ti;
	int64_t sleep_time = 3 * TIMER_FREQ;
	int64_t spin_time = sleep_time + 3 * TIMER_FREQ;
	int64_t last_time = 0;

	timer_sleep (sleep_time - timer_elapsed (ti->start_time));
	while (timer_elapsed (ti->start_time) < spin_time) {
		int64_t cur_time = timer_ticks ();
		if (cur_time != last_time) {
			printf("Thread %d got tick. \n", ti->id);
			ti->tick_count++;
		}
		last_time = cur_time;
	}

	sema_up(&ti->sema_join);
}


void run_scheduling_test(char **argv UNUSED)
{
	int i;
	struct thread_info info[MAX_THREAD_CNT];
	int64_t start_time;

	start_time = timer_ticks();
	for (i=0; i<MAX_THREAD_CNT; i++) {
		struct thread_info *ti = &info[i];
		char name[16];

		ti->id = i;
		ti->start_time = start_time;
		ti->tick_count = 0;
		sema_init(&ti->sema_join, 0);

		snprintf(name, sizeof name, "load %d", i);
		thread_create(name, PRI_DEFAULT, load_thread, ti);
	}

	printf("Starting threads took %lld ticks.\n", timer_elapsed (start_time));
	printf("Sleeping until threads join, please wait ... \n");

	for (i=0; i<MAX_THREAD_CNT; i++) {
		sema_down(&info[i].sema_join);
		printf("Thread %d received %d ticks.\n", i, info[i].tick_count);
	}
}