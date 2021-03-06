import java.io.*;
import java.util.*;

public class Rocio {
	public static void main(String[] args) throws IOException {
		Scanner scan = new Scanner(new File("rocio"));
		while (scan.hasNextInt()) {
			int a = scan.nextInt(); // a is length of 2D array
			int b = scan.nextInt(); // b is arm length
			int bcopy = b;
			String[][] arr = new String[a][a];
			arr[0][0] = "*"; // making corner parts of arms on all four sides
			arr[0][arr.length - 1] = "*";
			b--;
			arr[arr.length - 1][0] = "*";
			arr[arr.length - 1][arr.length - 1] = "*";
			for (int i = 1; i < b + 1; i++) {
				int count = 1;
				for (int j = 1; j < arr[i].length - 1; j++) {
					if ((arr[i - 1][j - 1] == "*" && count > 0)) {
						arr[i][j] = "*";
						count--; // this makes one arm in one quadrant and limits it so you can only have one dot
									// per each arm per line
					}
				}
			}
			for (int i = 1; i < b + 1; i++) {
				int count = 1;
				for (int j = arr.length - 2; j > 0; j--) {
					if (arr[i - 1][j + 1] == "*" && count > 0) {
						arr[i][j] = "*";
						count--; // same concept as the one before except this is the top right arm
					}
				}
			}
			int counter = a - (b * 2);
			for (int i = b + 1; i < b + 2; i++) {
				for (int j = bcopy; j < b + (a - (b * 2)) - 1; j++) {
					if (arr[i - 1][j] == null && counter > 0) {
						arr[i][j] = "*";
						counter--; // this makes the top row of the center
					}
				}
			}
			for (int i = bcopy + 1; i < arr.length - b - 1; i++) {
				int co = a - (b * 2);
				for (int j = bcopy; j < arr[i].length; j++) {
					if (arr[i - 1][j] != null && co > 0) {
						arr[i][j] = "*";
						co--; // this makes the rest of the center
					}
				}
			}
			for (int i = arr.length - 2; i > arr.length - (b + 2); i--) {
				int count = 1;
				for (int j = 1; j < arr[i].length - 1; j++) {
					if (arr[i + 1][j - 1] == "*" && count > 0) {
						arr[i][j] = "*";
						count--; // bottom left arm
					}
				}
			}
			for (int i = arr.length - 2; i > arr.length - (b + 2); i--) {
				int count = 1;
				for (int j = arr.length - 2; j > 0; j--) {
					if (arr[i + 1][j + 1] == "*" && count > 0) {
						arr[i][j] = "*";
						count--; // bottom right arm
					}
				}
			}
			for (int i = 0; i < arr.length; i++) {
				for (int j = 0; j < arr[i].length; j++) {
					if (arr[i][j] == null) {
						System.out.print(" ");
					} else {
						System.out.print("*");
					}
				}
				System.out.println();
			}
			for (int i = 0; i < a; i++) {
				System.out.print("=");
			}
			System.out.println();
		}
	}
}
