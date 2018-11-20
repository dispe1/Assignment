#ifndef __PROJECTS_MSGPASSING_MSGPASSINGTEST_H__
#define __PROJECTS_MSGPASSING_MSGPASSINGTEST_H__

#include "threads/thread.h"
#include "threads/synch.h"

struct one_to_one_box 
{
	struct mailbox* src;
	struct mailbox* dest;
};

void run_message_passing_test(void);
void init_one_to_one_box(struct one_to_one_box *, struct mailbox *, struct mailbox *);
void parbegin(void *, void *, struct one_to_one_box *);

struct message produce(void);
void producer(struct one_to_one_box *);

void consume(struct message);
void consumer(struct one_to_one_box *);

#endif