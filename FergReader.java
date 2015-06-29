package ferguson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;


public class FergReader {

	private int[][] nonwhite, white;

	public FergReader(String filename, int rows, int cols) {
		try {
			nonwhite = new int[rows][cols];
			white = new int[rows][cols];

			File f = new File(filename);
			BufferedReader lines = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
			for (int r=0; r < rows; ++r) {
				String s = lines.readLine();
				String[] data = s.split("[,]");
				for (int c=0; c < cols; ++c) {
					String cell = data[c].replaceAll("[()]", "");
					String[] both = cell.split("[ ]");
					white[r][c] = Integer.parseInt(both[0]);
					nonwhite[r][c] = Integer.parseInt(both[1]);
				}
			}
		}
		catch (Throwable t) {
			throw new Error("oops " + t);
		}

	}

	public int getBlack(int r, int c) {
		return nonwhite[r][c];
	}
	
	public int getWhite(int r, int c) {
		return white[r][c];
	}


	public static void main(String[] args) {
		FergReader fr = new FergReader("data/ferguson.csv", 40, 36);
		for (int r=0; r<40; ++r) {
			for (int c=0; c<36; ++c) {
				System.out.print(fr.getWhite(r, c) + " ");
			}
			System.out.println();
		}
	}

}