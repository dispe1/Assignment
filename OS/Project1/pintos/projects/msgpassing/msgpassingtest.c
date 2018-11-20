#include <stdio.h>
#include <random.h>

#include "projects/msgpassing/msgpassingtest.h"

#define LOOP_EXE_NUM 5

int i;
struct mailbox p1, p2;
struct mailbox c1, c2;

void run_message_passing_test(void)
{
	struct one_to_one_box box1;
	struct one_to_one_box box2;
	
	init_one_to_one_box(&box1, &p1, &c1);
	init_one_to_one_box(&box2, &p2, &c2);
	parbegin(producer, consumer, &box1);
	parbegin(producer, consumer, &box2);

	return;
}


void init_one_to_one_box(struct one_to_one_box* box, struct mailbox* src, struct mailbox* dest)
{
	init_mailbox(src);
	init_mailbox(dest);
	box->src = src;
	box->dest = dest;
}

void parbegin(void* func1, void* func2, struct one_to_one_box *box)
{
	thread_create("producer", PRI_DEFAULT, func1, box);
	thread_create("consumer", PRI_DEFAULT, func2, box);
	send(box->src, NULL);
}

struct message produce()
{
	struct message pmsg;
	int temp;
	random_bytes(&temp,sizeof(int));
	pmsg.contents = temp % 100 + 100;	//random(0 ... 200)
	printf("\n %d producer produce message : %d\n", thread_current()->tid, pmsg.contents);
	return pmsg;
}
void producer(struct one_to_one_box *box)
{ 
	struct message pmsg;
	int i;

	printf("\n %d producer start.\n", thread_current()->tid);
	for(i = 0; i < LOOP_EXE_NUM; i++)
	{
		receive(box->src,&pmsg);
		pmsg = produce();
		send(box->dest,&pmsg);
	}
	printf("\n %d producer end.\n", thread_current()->tid);
	thread_exit();
}

void consume(struct message cmsg) {
	printf("\n %d consumer consume message : %d\n", thread_current()->tid, cmsg.contents);
}

void consumer(struct one_to_one_box *box)
{ 
	struct message cmsg;
	int i;

	printf("\n %d consumer start.\n", thread_current()->tid);
	for (i = 0; i < LOOP_EXE_NUM; i++)
	{
		receive(box->dest, &cmsg);
		consume (cmsg);
		if (i == LOOP_EXE_NUM - 1)
		{
			printf("\n %d consumer end.\n", thread_current()->tid);
			thread_exit();
		}
		send(box->src, NULL);
	}
}
