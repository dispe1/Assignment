#ifndef __PROJECTS_PROJECT1_MAPDATA_H__
#define __PROJECTS_PROJECT1_MAPDATA_H__

#include "projects/crossroads/position.h"

#define MAP_CROSS_START_X	2
#define MAP_CROSS_START_Y	2
#define MAP_CROSS_END_X		4
#define MAP_CROSS_END_Y		4

extern const char map_draw_default[7][7];
extern const struct position path[3][3][10];

#endif /* __PROJECTS_PROJECT1_MAPDATA_H__ */