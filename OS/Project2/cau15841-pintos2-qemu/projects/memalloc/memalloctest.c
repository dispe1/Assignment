#include <stdio.h>
#include <string.h>

#include "threads/thread.h"
#include "threads/malloc.h"
#include "threads/palloc.h"

#include "projects/memalloc/memalloctest.h"

void run_memalloc_test(char **argv UNUSED)
{
	size_t i;
	char* dynamicmem[12];

	dynamicmem[0] = (char *)malloc(4096);
	memset(dynamicmem[0], 0x00, 4096);
	dynamicmem[1] = (char *)malloc(65536);
	memset(dynamicmem[1], 0x00, 65536);
	dynamicmem[2] = (char *)malloc(131072);
	memset(dynamicmem[2], 0x00, 131072);
	dynamicmem[3] = (char *)malloc(8192);
	memset(dynamicmem[3], 0x00, 8192);
	dynamicmem[4] = (char *)malloc(131072);
	memset(dynamicmem[4], 0x00, 131072);
	dynamicmem[5] = (char *)malloc(32768);
	memset(dynamicmem[5], 0x00, 32768);
	dynamicmem[6] = (char *)malloc(65536);
	memset(dynamicmem[6], 0x00, 65536);
	dynamicmem[7] = (char *)malloc(16384);
	memset(dynamicmem[7], 0x00, 16384);
	dynamicmem[8] = (char *)malloc(65536);
	memset(dynamicmem[8], 0x00, 65536);
	dynamicmem[9] = (char *)malloc(262144);
	memset(dynamicmem[9], 0x00, 262144);
	dynamicmem[10] = (char *)malloc(4096);
	memset(dynamicmem[10], 0x00, 4096);
	dynamicmem[11] = (char *)malloc(32768);
	memset(dynamicmem[11], 0x00, 32768);
	printf("dump page status : \n");
	palloc_get_status(0);

	thread_sleep(100);

	for (i = 0; i<12; i++) {
		free(dynamicmem[i]);
		printf("dump page status : \n");
		palloc_get_status(0);
	}
}

