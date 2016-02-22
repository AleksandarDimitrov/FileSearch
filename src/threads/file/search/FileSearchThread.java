package threads.file.search;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Alex
 *
 */
public class FileSearchThread extends Thread {

	private String directory;
	private String file;
	FileSearch fileSearch = new FileSearch();
	List<String> noResult = new ArrayList<String>();
	List<String> result = new ArrayList<String>();

	/**
	 * @return the directory
	 */
	public String getDirectory() {
		return directory;
	}

	/**
	 * @param directory
	 *            the directory to set
	 */
	public void setDirectory(String directory) {
		this.directory = directory;
	}

	/**
	 * @return the file
	 */
	public String getFile() {
		return file;
	}

	/**
	 * @param file
	 *            the file to set
	 */
	public void setFile(String file) {
		this.file = file;
	}

	@Override
	public void run() {
		findFile(getDirectory(), getFile());
		System.out.println("Find finished");
	}

	/**
	 * @param directory
	 * @param file
	 * @return List<String>
	 */
	public List<String> findFile(String directory, String file) {
		fileSearch.searchDirectory(new File(directory), file);
		int count = fileSearch.getResult().size();
		if (count == 0) {
			noResult.add("\nNo result found!");
			System.out.println("\nNo result found!");
			return noResult;
		} else {
			for (String matched : fileSearch.getResult()) {
				result.add("Found: " + matched);
			}
			System.out.println("\nSearch finished !");
			return result;
		}
	}

	/**
	 * Print List<String>
	 */
	public void listFile() {
		if (!(file == null)) {
			if (!noResult.isEmpty()) {
				for (String strings : noResult) {
					System.out.println(strings);
				}
			} else {
				System.out.println("Result " + result.size());
				for (String strings : result) {
					System.out.println(strings);
				}
			}
		} else {
			System.out.println("Enter command \"find\" first !");
		}

	}
}
