package tsp;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Scanner;

public class Tsp {
	public static void main(String[] args) {
		Scanner in;
		String inputFileName = "131.txt";
		try {
			in = new Scanner(new File(inputFileName), "UTF-8");
		} catch (Exception e) {
			System.out.println("invalid or no input file ");
			return;
		}
		for(int i = 0; i<5 && in.hasNextLine();i++) {
			in.nextLine();
		}

		String s = in.nextLine();
		int N = Integer.parseInt(s.substring(12));
		int x[] = new int[N];
		int y[] = new int[N];
		double distance[][] = new double[N][N];
		int[] result = new int[N+1];
		int[] minResult = new int[N+1];

		for(int i = 0; i<2 && in.hasNextLine();i++) {
			in.nextLine();
		}

		while(in.hasNextInt()) {
			int i = in.nextInt();
			x[i-1] = in.nextInt();
			y[i-1] = in.nextInt();
		}

		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				distance[i][j] = getDistance(x[i],y[i],x[j],y[j]);
			}
		}

		double limitTime = 120;
		int count = 1;
		double minDistance = 99999999;
		double resultDistance;
		double time = 0;
		long end;
		long start = System.currentTimeMillis();
		/*
      while((time + (time/count)) < limitTime){
         count++;
         result = randomAlgorithm(N);
         resultDistance = getTotalDistance(result, distance);
         if(minDistance > resultDistance) {
            minDistance = resultDistance;
            System.arraycopy(result, 0, minResult, 0, N+1);
         }
         end = System.currentTimeMillis();
         time = ( end - start )/1000.0;
      }
      writeResult("random.txt",minResult);
      System.out.println("Random Algorithm : " + getTotalDistance(minResult, distance));
      System.out.println("실행시간 : " + time);
      System.out.println("실행 횟수 : " + (count-1));
		 */
		/*
      minDistance = 99999999;
      time = 0;
      count = 1;
      start = System.currentTimeMillis();
      while((time + (time/count)) < limitTime){
         count++;
         result = greedyAlgorithm(N, distance);
         resultDistance = getTotalDistance(result, distance);
         if(minDistance > resultDistance) {
            minDistance = resultDistance;
            System.arraycopy(result, 0, minResult, 0, N+1);

         }
         end = System.currentTimeMillis();
         time = ( end - start )/1000.0;
      }
      writeResult("greedy.txt",minResult);
      System.out.println("Greedy Algorithm : " + getTotalDistance(minResult, distance));
      System.out.println("실행시간 : " + time);
      System.out.println("실행 횟수 : " + (count-1));
		 */
		/*
      minDistance = 99999999;
      time = 0;
      count = 1;
      start = System.currentTimeMillis();
      while((time + (time/count)) < limitTime){
         count++;
         result = twoOptAlgorithm(N, distance, start,limitTime);;
         resultDistance = getTotalDistance(result, distance);
         if(minDistance > resultDistance) {
            minDistance = resultDistance;
            System.arraycopy(result, 0, minResult, 0, N+1);
         }
         end = System.currentTimeMillis();
         time = ( end - start )/1000.0;
      }
      writeResult("2opt.txt",minResult);
      System.out.println("2opt Algorithm : " + getTotalDistance(minResult, distance));
      System.out.println("실행시간 : " + time);
      System.out.println("실행 횟수 : " + (count-1));


      minDistance = 99999999;
      time = 0;
      count = 1;
      start = System.currentTimeMillis();
      while((time + (time/count)) < limitTime){
         count++;
         result = SAAlgorithm(N, distance, start,limitTime);;
         resultDistance = getTotalDistance(result, distance);
         if(minDistance > resultDistance) {
            minDistance = resultDistance;
            System.arraycopy(result, 0, minResult, 0, N+1);
         }
         end = System.currentTimeMillis();
         time = ( end - start )/1000.0;
      }
      writeResult("SA.txt",minResult);
      System.out.println("SA Algorithm : " + getTotalDistance(minResult, distance));
      System.out.println("실행시간 : " + time);
      System.out.println("실행 횟수 : " + (count-1));
		 */
		
		minDistance = 99999999;
		time = 0;
		count = 1;
		start = System.currentTimeMillis();
		while((time + (time/count)) < limitTime){
			count++;
			result = midDemo(N, distance, start,limitTime);
			resultDistance = getTotalDistance(result, distance);
			if(minDistance > resultDistance) {
				minDistance = resultDistance;
				System.arraycopy(result, 0, minResult, 0, N+1);
			}
			end = System.currentTimeMillis();
			time = ( end - start )/1000.0;
		}
		writeResult("midDemo.txt",minResult);
		System.out.println("midDemo : " + getTotalDistance(minResult, distance));
		System.out.println("실행시간 : " + time);
		System.out.println("실행 횟수 : " + (count-1));
		
/*
		start = System.currentTimeMillis();
		Tour tour = GAAlgorithm(N, distance, start, limitTime);
		end = System.currentTimeMillis();
		time = ( end - start )/1000.0;

		writeResult("GA.txt",tour.result);
		System.out.println("GAAlgorithm : " + getTotalDistance(tour.result, distance));
		System.out.println("실행시간 : " + time);
*/
		


		in.close();
	}


	static class Tour implements Comparable<Tour>{
		int[] result;
		double distance;
		int length;

		public Tour(int N, double[][] distance) {
			super();
			int[] bestResult = new int[N+1];
			int[] newResult = new int[N+1];
			double bestDistance = 99999999;
			double newDistance;
			double greedyTime = 0;
			double greedyEndTime = 1;
			int greedyCount = 1;
			long end;
			long greedyStart = System.currentTimeMillis();
			
			while((greedyTime + (greedyTime/greedyCount)) < greedyEndTime){
				greedyCount++;
				newResult = greedyAlgorithm(N, distance);
				newDistance = getTotalDistance(newResult, distance);
				if(bestDistance > newDistance) {
					bestDistance = newDistance;
					System.arraycopy(newResult, 0, bestResult, 0, N+1);
				}
				end = System.currentTimeMillis();
				greedyTime = ( end - greedyStart )/1000.0;
			}
			
			this.result = bestResult;
			this.distance = getTotalDistance(this.result, distance);
			this.length = N;
		}

		public Tour(int N) {
			super();
			this.result = new int[N+1];
			this.length = N;
		}

		public Tour() {
			super();
		}

		@Override
		public int compareTo(Tour target) {
			if(this.distance > target.distance) {
				return 1;
			}
			else {
				return -1;
			}
		}
	}

	static Tour GAAlgorithm(int N, double [][] distance, long start, double limitTime) {
		int populationSize = 7;
		double mutationRate = 0.01;
		int count = 1;
		double time = 0;
		long end;

		PriorityQueue<Tour> population = new PriorityQueue<Tour>();

		for(int i = 0; i < populationSize; i++) {
			population.offer(new Tour(N, distance));
		}


		while((time + (time/count) < limitTime)) {
			PriorityQueue<Tour> newPopulation = new PriorityQueue<Tour>();

			newPopulation.offer( population.peek());

			for(int i = 1; i < populationSize && (time + (time/count) < limitTime); i++) {
				Tour parent1 = tournamentSelection(population, 3);
				Tour parent2 = tournamentSelection(population, 3);

				Tour child = crossOver(parent1, parent2, distance);

				newPopulation.offer(child);
			}

			Iterator<Tour> iter = newPopulation.iterator();
			iter.next();

			for(int i = 1; i < populationSize && (time + (time/count) < limitTime); i++) {
				mutate(iter.next(),mutationRate, distance);
			}

			population = newPopulation;
			count++;
			end = System.currentTimeMillis();
			time = ( end - start )/1000.0;
			System.out.println(count + " : " + population.peek().distance);

		}


		return population.peek();
	}

	static void mutate(Tour tour, double mutationRate, double[][] distance) {
		Random generator = new Random();
		if(generator.nextDouble() < mutationRate) {
			int i = generator.nextInt(tour.length-1)+1;
			int j = generator.nextInt(tour.length-1) +1;

			double newDistance = tour.distance - distance[tour.result[i-1]][tour.result[i]] - distance[tour.result[j]][tour.result[j+1]] + distance[tour.result[i-1]][tour.result[j]] + distance[tour.result[i]][tour.result[j+1]];

			tour.distance = newDistance;
			tour.result = twoOptSwap(tour.result, i, j);
		}
	}

	static Tour crossOver(Tour parent1, Tour parent2, double[][] distance) {
		double crossOverRate = 0.8;
		Tour child = new Tour(parent1.length);
		boolean[] visited = new boolean[parent1.length];
		Random generator = new Random();
		int start = generator.nextInt((int)(parent1.length * (1-crossOverRate)));
		int end = start + (int)(parent1.length * crossOverRate);

		for(int i = start; i < end; i++) {
			child.result[i] = parent1.result[i];
			visited[parent1.result[i]] = true;
		}
		int j = end;
		for(int i = end; i < parent1.length; i++) {
			while(visited[parent2.result[j]]) {
				j++;
				if(j > parent2.length) {
					j = 0;
				}
			}
			child.result[i] = parent2.result[j];
			visited[parent2.result[j]] = true;
		}
		for(int i = 0; i < start; i++) {
			while(visited[parent2.result[j]]) {
				j++;
				if(j > parent2.length) {
					j = 0;
				}
			}
			child.result[i] = parent2.result[j];
			visited[parent2.result[j]] = true;
		}

		child.result[parent1.length] = child.result[0];
		child.distance = getTotalDistance(child.result, distance);

		for(int i = 1; i< child.length ;i++) {
			for(j = i+1; j < child.length ; j++) {
				double newDistance = child.distance - distance[child.result[i-1]][child.result[i]] - distance[child.result[j]][child.result[j+1]] + distance[child.result[i-1]][child.result[j]] + distance[child.result[i]][child.result[j+1]];
				if(newDistance < child.distance) {
					child.result = twoOptSwap(child.result, i, j);
					child.distance = newDistance;
				}
			}
		}

		return child;
	}

	static Tour tournamentSelection(PriorityQueue<Tour> pop, int tournamentSize) {
		PriorityQueue<Tour> tournament = new PriorityQueue<Tour>();
		Random generator = new Random();
		Tour tour = new Tour();

		for(int i = 0; i < tournamentSize; i++) {
			int random = (int) (generator.nextInt(pop.size())) + 1;
			Iterator<Tour> iter = pop.iterator();
			for(int j = 0; j < random; j++) {
				tour = iter.next();
			}
			tournament.offer(tour);
		}

		return tournament.peek();
	}

	static int[] midDemo(int N, double[][] distance, long start, double limitTime) {
		int[] currentResult = new int[N+1];
		int[] bestResult = new int[N+1];
		int[] newResult = new int[N+1];
		double bestDistance = 99999999;
		double newDistance;
		double currentDistance;
		int count = 1;
		double time = 0;
		long end;
		double temperature = 100;
		double coolingRate = 0.00003;
		double limitTemp = 1;

		double greedyTime = 0;
		double greedyEndTime = limitTime / 20;
		int greedyCount = 1;
		long greedyStart = System.currentTimeMillis();
		while((greedyTime + (greedyTime/greedyCount)) < greedyEndTime){
			greedyCount++;
			newResult = greedyAlgorithm(N, distance);
			newDistance = getTotalDistance(newResult, distance);
			if(bestDistance > newDistance) {
				bestDistance = newDistance;
				System.arraycopy(newResult, 0, bestResult, 0, N+1);
			}
			end = System.currentTimeMillis();
			greedyTime = ( end - greedyStart )/1000.0;
		}
		System.arraycopy(bestResult, 0, currentResult, 0, N+1);
		currentDistance = bestDistance;

		int divide = N / 100;
		while((time + (time/count) < limitTime) && temperature > limitTemp) {
			for(int m = 0; m < divide && (time + (time/count)) < limitTime && temperature > limitTemp ; m++) {
				for(int i = 1 + N * m / divide; i< N * (m+1) / divide && (time + (time/count)) < limitTime && temperature > limitTemp;i++) {
					for(int j = i+1; j < N * (m+1) / divide && (time + (time/count)) < limitTime && temperature > limitTemp; j++) {
						count++;
						newDistance = currentDistance - distance[currentResult[i-1]][currentResult[i]] - distance[currentResult[j]][currentResult[j+1]] + distance[currentResult[i-1]][currentResult[j]] + distance[currentResult[i]][currentResult[j+1]];
						if(probability(currentDistance,newDistance,temperature) > Math.random()) {
							currentResult = twoOptSwap(currentResult, i, j);
							currentDistance = newDistance;
						}
						else {
							currentDistance = bestDistance;
							System.arraycopy(bestResult, 0, currentResult, 0, N+1);
							temperature *= 1 / (1 - coolingRate);
						}
						if(currentDistance < bestDistance) {
							bestDistance = currentDistance;
							System.arraycopy(currentResult, 0, bestResult, 0, N+1);
							//System.out.println(count + " : " + temperature + " " + bestDistance );
						}
						end = System.currentTimeMillis();
						time = ( end - start )/1000.0;
						temperature *= (1 - coolingRate);
					}
				}
			}
			currentDistance = bestDistance;
			System.arraycopy(bestResult, 0, currentResult, 0, N+1);
			boolean changed = true;
			while((time + (time/count) < limitTime) && changed) {
				changed = false;
				for(int i = 1; i< N && (time + (time/count)) < limitTime;i++) {
					for(int j = i+1; j < N && (time + (time/count)) < limitTime; j++) {
						count++;
						newDistance = currentDistance - distance[currentResult[i-1]][currentResult[i]] - distance[currentResult[j]][currentResult[j+1]] + distance[currentResult[i-1]][currentResult[j]] + distance[currentResult[i]][currentResult[j+1]];
						if(newDistance < bestDistance) {
							currentResult = twoOptSwap(currentResult, i, j);
							currentDistance = newDistance;
							bestDistance = newDistance;
							System.arraycopy(currentResult, 0, bestResult, 0, N+1);
							//System.out.println(count + " : " + bestDistance);
							changed = true;
						}
						end = System.currentTimeMillis();
						time = ( end - start )/1000.0;
					}
				}
			}   
		}

		return bestResult;
	}

	static int[] SAAlgorithm(int N, double[][] distance, long start, double limitTime) {
		int[] currentResult = new int[N+1];
		int[] bestResult = new int[N+1];
		int[] newResult = new int[N+1];
		double bestDistance = 99999999;
		double newDistance;
		double currentDistance;
		int count = 1;
		double time = 0;
		long end;
		double temperature = 100;
		double coolingRate = 0.00003;
		double limitTemp = 1;

		double greedyTime = 0;
		int greedyCount = 1;
		long greedyStart = System.currentTimeMillis();
		while((greedyTime + (greedyTime/greedyCount)) < 1){
			greedyCount++;
			newResult = greedyAlgorithm(N, distance);
			newDistance = getTotalDistance(newResult, distance);
			if(bestDistance > newDistance) {
				bestDistance = newDistance;
				System.arraycopy(newResult, 0, bestResult, 0, N+1);
			}
			end = System.currentTimeMillis();
			greedyTime = ( end - greedyStart )/1000.0;
		}
		System.arraycopy(bestResult, 0, currentResult, 0, N+1);
		currentDistance = bestDistance;

		while((time + (time/count) < limitTime) && temperature > limitTemp) {
			for(int i = 1; i< N && (time + (time/count)) < limitTime && temperature > limitTemp;i++) {
				for(int j = i+1; j < N && (time + (time/count)) < limitTime && temperature > limitTemp; j++) {
					count++;
					newDistance = currentDistance - distance[currentResult[i-1]][currentResult[i]] - distance[currentResult[j]][currentResult[j+1]] + distance[currentResult[i-1]][currentResult[j]] + distance[currentResult[i]][currentResult[j+1]];
					if(probability(currentDistance,newDistance,temperature) > Math.random()) {
						currentResult = twoOptSwap(currentResult, i, j);
						currentDistance = newDistance;
					}
					else {
						currentDistance = bestDistance;
						System.arraycopy(bestResult, 0, currentResult, 0, N+1);
						temperature *= 1 / (1 - coolingRate);
					}
					if(currentDistance < bestDistance) {
						bestDistance = currentDistance;
						System.arraycopy(currentResult, 0, bestResult, 0, N+1);
						//System.out.println(count + " : " + temperature + " " + bestDistance );
					}
					end = System.currentTimeMillis();
					time = ( end - start )/1000.0;
					temperature *= (1 - coolingRate);
				}
			}
		}
		return bestResult;
	}

	static double probability(double distance, double newDistance, double temperature) {
		if(newDistance < distance) {
			return 1.0;
		}
		return Math.exp((distance - newDistance)/temperature);
	}

	static int[] twoOptAlgorithm(int N, double[][] distance, long start, double limitTime) {
		int[] result = greedyAlgorithm(N,distance);
		int[] newResult = new int[N+1];
		double bestDistance = getTotalDistance(result,distance);
		double newDistance;
		int count = 1;
		double time = 0;
		long end;
		boolean changed = true;

		double greedyTime = 0;
		int greedyCount = 1;
		long greedyStart = System.currentTimeMillis();
		while((greedyTime + (greedyTime/greedyCount)) < 1){
			greedyCount++;
			newResult = greedyAlgorithm(N, distance);
			newDistance = getTotalDistance(newResult, distance);
			if(bestDistance > newDistance) {
				bestDistance = newDistance;
				System.arraycopy(newResult, 0, result, 0, N+1);
			}
			end = System.currentTimeMillis();
			greedyTime = ( end - greedyStart )/1000.0;
		}

		while((time + (time/count) < limitTime) && changed) {
			changed = false;
			for(int i = 1; i< N && (time + (time/count)) < limitTime;i++) {
				for(int j = i+1; j < N && (time + (time/count)) < limitTime; j++) {
					count++;
					newDistance = bestDistance - distance[result[i-1]][result[i]] - distance[result[j]][result[j+1]] + distance[result[i-1]][result[j]] + distance[result[i]][result[j+1]];
					if(newDistance < bestDistance) {
						result = twoOptSwap(result, i, j);
						bestDistance = newDistance;
						//System.out.println(count + " : " + bestDistance);
						changed = true;
					}
					end = System.currentTimeMillis();
					time = ( end - start )/1000.0;
				}
			}
		}
		return result;
	}

	static int[] twoOptSwap(int[] tour, int a, int b) {
		int size = tour.length;
		int[] newTour = new int[size];
		System.arraycopy(tour, 0, newTour, 0, a);
		for(int i = 0; i<=b-a; i++) {
			System.arraycopy(tour, i+a, newTour, b-i, 1);
		}
		System.arraycopy(tour, b+1, newTour, b+1, size-b-1);

		return newTour;
	}

	static int[] greedyAlgorithm(int N, double[][] distance) {
		int[] result = new int[N+1];
		boolean[] visited = new boolean[N];
		int count = 0;
		Random generator = new Random();        

		//처음 출발지는 랜덤
		int start = generator.nextInt(N);
		visited[start] = true;
		result[0] = start;
		count++;

		while(count < N) {
			double min = 99999999;
			int current = result[count-1];
			int next = current;
			for(int i = 0; i < N; i++) {
				if(distance[current][i] < min && visited[i] == false) {
					min = distance[result[count-1]][i];
					next = i;
				}
			}
			visited[next] = true;
			result[count] = next;
			count++;
		}

		result[count] = result[0];      
		return result;
	}

	static int[] randomAlgorithm(int N) {
		int[] result = new int[N+1];
		boolean[] visited = new boolean[N];
		int count = 0;
		Random generator = new Random();        

		while(count < N) {
			int i = generator.nextInt(N);
			if(visited[i] == false) {
				visited[i] = true;
				result[count] = i;
				count++;
			}
		}

		result[count] = result[0];      
		return result;
	}

	static double getTotalDistance(int[] result, double[][] distance) {
		double totalDistance = 0;

		for(int i = 0; i < (result.length-1); i++) {
			totalDistance += distance[result[i]][result[i+1]]; 
		}

		return totalDistance;
	}

	static double getDistance(int x1, int y1, int x2, int y2) {
		return Math.sqrt(Math.pow(Math.abs(x1-x2), 2)+Math.pow(Math.abs(y1-y2), 2));
	}

	static void writeResult(String Filename, int[] result) {

		File file = new File(Filename);
		FileWriter writer = null;
		BufferedWriter bWriter = null;

		try {
			// 기존 파일의 내용에 이어서 쓰려면 true를, 기존 내용을 없애고 새로 쓰려면 false를 지정한다.
			writer = new FileWriter(file, false);
			bWriter = new BufferedWriter(writer);

			for(int i = 0; i < result.length; i++) {
				String message = Integer.toString(result[i]) + "\r\n";
				bWriter.write(message);
				bWriter.flush();
			}
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(bWriter != null) bWriter.close();
				if(writer != null) writer.close();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
}