
#include "projects/crossroads/mapdata.h"


const char map_draw_default[7][7] = {
	{'X', 'X', 'X', 'X', 'X', 'X', 'X'}, 
	{'X', 'X', 'X', 'X', 'X', 'X', 'X'}, 
	{' ', ' ', ' ', '-', ' ', ' ', ' '}, 
	{'-', '-', '-', '-', '-', '-', '-'}, 
	{' ', ' ', ' ', '-', ' ', ' ', ' '}, 
	{'X', 'X', ' ', '-', ' ', 'X', 'X'}, 
	{'X', 'X', ' ', '-', ' ', 'X', 'X'}, 
};

/* path. A:0 B:1 C:2 */
const struct position path[3][3][10] = {
	/* from A */ {
		{{-1,-1},},
		{{4,0},{4,1},{4,2},{5,2},{6,2},{-1,-1},},
		{{4,0},{4,1},{4,2},{4,3},{4,4},{4,5},{4,6},{-1,-1},}
	},
	/* from B */ {
		{{6,4},{5,4},{4,4},{3,4},{2,4},{2,3},{2,2},{2,1},{2,0},{-1,-1}},
		{{-1,-1},},
		{{6,4},{5,4},{4,4},{4,5},{4,6},{-1,-1},}
	},
	/* from C */ {
		{{2,6},{2,5},{2,4},{2,3},{2,2},{2,1},{2,0},{-1,-1},},
		{{2,6},{2,5},{2,4},{2,3},{2,2},{3,2},{4,2},{5,2},{6,2},{-1,-1}},
		{{-1,-1},}
	}
};