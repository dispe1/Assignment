#ifndef __PROJECTS_PROJECT1_CROASSROADS_H__
#define __PROJECTS_PROJECT1_CROASSROADS_H__

#include "projects/crossroads/mapdata.h"

struct car
{
    struct position location;
    char name;
    int progress;
	char src;
	char dest;
    struct position *path;
};

void run_crossroads(char **);

void input_parse(char *, char ***);

void crossroads_init(void);
void end_check(void);

void timer_thread(void);

void cross_check(struct car *);

void car_thread(char *);
void car_init(char*, struct car *);
struct position* path_get(char *);
void go_one_block(struct car *);

void map_init(void);
void map_print(void);
void map_update(char, struct position, struct position);

#endif /* __PROJECTS_PROJECT1_CROASSROADS_H__ */