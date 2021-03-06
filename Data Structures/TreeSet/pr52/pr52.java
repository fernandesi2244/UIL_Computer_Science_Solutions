import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.TreeSet;

public class pr52 {
	
	private static void process(String input) {
		TreeSet<Character> set = new TreeSet<>();
		for(int i = 0;i<input.length();i++) {
			set.add(input.charAt(i));
		}
		set.stream().forEach(x->System.out.print(x));
		System.out.print(" ");
	}

	public static void main(String[] args)throws IOException {
		Scanner scan = new Scanner(new File("pr51.dat"));
		int no = Integer.parseInt(scan.nextLine());
		while(no-->0) {
			String[] line = scan.nextLine().split(" ");
			for(String a: line)
				process(a);
			System.out.println();
		}
		scan.close();

	}

}
