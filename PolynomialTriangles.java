import java.util.Scanner;


public class PolynomialTriangles {
	
	public static void main(String[] args) throws InterruptedException {
		
		Scanner scanner = new Scanner(System.in);
		String input, input_yValues[];
		int[] topRow, currentRow, nextRow; // holds rows of inverted triangle used for iterative subtraction to achieve next y-value
		boolean validInput = false;
	
		System.out.println("This program will output successive y-values of a polynomial, given initial consecutive y-values, using an algorithm that employs only repeated subtraction!"
				+ "\nYou don't even have to supply the polynomial itself!!\n");
		
		do {
			System.out.println("Enter n consecutive y-values for a polynomial of degree less than n, separated by commas:");
			input = scanner.nextLine();
			input_yValues = input.split(" *, *");
			topRow = new int[input_yValues.length];
			currentRow = new int[topRow.length + 1];
			
			try {
				for (int index = 0; index < topRow.length; index++) {
					topRow[index] = Integer.parseInt(input_yValues[index]);
					currentRow[index] = topRow[index];
				}
				
				validInput = true;
				System.out.println("Next y-values:");
			}
			catch (NumberFormatException exception) {
				if (input.equals("Q") || input.equals("q"))
					break;
				else
					System.out.println("Invalid input.");
					System.out.println("Enter 'Q' or 'q' to quit.");
			}
		} while (!validInput);
		
		while (validInput) {
			
			currentRow[currentRow.length - 1] = 0;
			nextRow = createNextRow(topRow.length, currentRow);
			
			while (nextRow.length > 1) {
				currentRow = nextRow;
				nextRow = createNextRow(currentRow.length - 1, currentRow);
			}
			
			System.out.println(-nextRow[0]);
			
			for (int index = 0; index < topRow.length - 1; index++) {
				topRow[index] = topRow[index + 1];
			}
			
			topRow[topRow.length - 1] = -nextRow[0];
			currentRow = new int[topRow.length + 1];
			
			System.arraycopy(topRow, 0, currentRow, 0, topRow.length);
			
			Thread.sleep(100);
		}
	}
	
	public static int[] createNextRow(int iterations, int[] currentRow) {
		int[] nextRow = new int[currentRow.length - 1];
		for (int index = 0; index < iterations; index++) {
			nextRow[index] = currentRow[index + 1] - currentRow[index];
		}
		return nextRow;
	}
	
}
