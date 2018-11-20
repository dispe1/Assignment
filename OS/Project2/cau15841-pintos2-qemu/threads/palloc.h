#ifndef THREADS_PALLOC_H
#define THREADS_PALLOC_H

#include <stddef.h>

/* How to allocate pages. */
enum palloc_flags
  {
    PAL_ASSERT = 001,           /* Panic on failure. */
    PAL_ZERO = 002,             /* Zero page contents. */
    PAL_USER = 004              /* User page. */
  };

/* The page allocator algorithm. Default is First Fit */
enum palloc_allocator
  {
    ALLOCATOR_FF = 0,            /* 0: First Fit (default) */
    ALLOCATOR_NF,                /* 1: Next Fit  */
    ALLOCATOR_BF,                /* 2: Best Fit  */
    ALLOCATOR_BUDDY              /* 3: Buddy System  */
  };

extern enum palloc_allocator pallocator;

void palloc_init (size_t user_page_limit);
void *palloc_get_page (enum palloc_flags);
void *palloc_get_multiple (enum palloc_flags, size_t page_cnt);
void palloc_free_page (void *);
void palloc_free_multiple (void *, size_t page_cnt);
void palloc_get_status (enum palloc_flags flags);

#endif /* threads/palloc.h */
