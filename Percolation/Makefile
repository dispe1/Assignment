
programs = Percolation.class PercolationStats.class PercolationVisualizer.class

all: $(programs)

Percolation.class: Percolation.java WeightedQuickUnionUF.java
	javac $^

PercolationStats.class: PercolationStats.java Percolation.java WeightedQuickUnionUF.java
	javac $^

PercolationVisualizer.class: PercolationVisualizer.java Percolation.java WeightedQuickUnionUF.java StdDraw.java
	javac $^

clean:
	rm -f *.class

#rm -f $(programs)
