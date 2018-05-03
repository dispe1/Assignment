
programs = basic_quick_sort 3way_quick_sort

all: $(programs)

3way_quick_sort: 3way_quick_sort.c
	gcc $^ -o $@

basic_quick_sort: basic_quick_sort.c
	gcc $^ -o $@

#gcc -g $^ -o $@

clean:
	rm -f $(programs)

