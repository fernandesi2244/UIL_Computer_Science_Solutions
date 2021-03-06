import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class pr130 {

	static class Point {
		Point parent;
		int location;

		public Point(Point parent, int location) {
			this.parent = parent;
			this.location = location;
		}
	}

	static class Pair {
		int firstNum, secondNum;

		public Pair(int a, int b) {
			firstNum = a;
			secondNum = b;
		}
	}

	LinkedList<Integer>[] vertices;

	public pr130(int v) {
		vertices = new LinkedList[v];
		for (int i = 0; i < vertices.length; i++)
			vertices[i] = new LinkedList<>();
	}

	public void addEdge(int a, int b) {
		vertices[a].add(b);
		vertices[b].add(a);
	}

	public Point bfs(int start, int end) {
		boolean[] visited = new boolean[vertices.length];
		Queue<Point> toVisit = new LinkedList<>();
		visited[start] = true;
		if (start == end) {
			return new Point(null, start);
		}
		toVisit.add(new Point(null, start));

		while (!toVisit.isEmpty()) {
			Point p = toVisit.remove();
			Iterator<Integer> it = vertices[p.location].iterator();
			while (it.hasNext()) {
				int adjacentVertex = it.next();
				if (!visited[adjacentVertex]) {
					if (adjacentVertex == end)
						return new Point(p, adjacentVertex);
					visited[adjacentVertex] = true;
					toVisit.add(new Point(p, adjacentVertex));
				}
			}

		}

		return null;
	}

	public static void main(String[] args) throws IOException {
		Scanner scan = new Scanner(new File("pr130.dat"));
		int no = Integer.parseInt(scan.nextLine());
		HashMap<String, Integer> stringToInt = new HashMap<>();
		HashSet<Integer> vertexSet = new HashSet<>();
		ArrayList<Pair> pairs = new ArrayList<>();
		int x = 0;
		while (no-- > 0) {
			String[] currentLine = scan.nextLine().split(" ");
			String first = currentLine[0];
			String second = currentLine[1];
			if (!stringToInt.containsKey(first)) {
				stringToInt.put(first, x++);
			}
			if (!stringToInt.containsKey(second)) {
				stringToInt.put(second, x++);
			}

			pairs.add(new Pair(stringToInt.get(first), stringToInt.get(second)));
			vertexSet.add(stringToInt.get(first));
			vertexSet.add(stringToInt.get(second));
		}

		pr130 graph = new pr130(vertexSet.size());
		for (Pair a : pairs) {
			graph.addEdge(a.firstNum, a.secondNum);
		}

		int noTestCases = Integer.parseInt(scan.nextLine());
		while (noTestCases-- > 0) {
			String[] currentLine = scan.nextLine().split(" ");
			String first = currentLine[0];
			String second = currentLine[1];
			Point p = graph.bfs(stringToInt.get(first), stringToInt.get(second));
			if (p == null) {
				System.out.println("NO PATH");
			} else {
				int pathLength = 0;
				while (p.parent != null) {
					pathLength++;
					p = p.parent;
				}
				System.out.println(pathLength);
			}

		}

		scan.close();
	}
}
