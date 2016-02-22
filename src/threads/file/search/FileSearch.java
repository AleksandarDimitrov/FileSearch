package threads.file.search;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Alex
 * 
 */
public class FileSearch {

	private String fileNameToSearch;
	private List<String> directory = new ArrayList<String>();
	private List<String> result = new ArrayList<String>();

	public String getFileNameToSearch() {
		return fileNameToSearch;
	}

	public void setFileNameToSearch(String fileNameToSearch) {
		this.fileNameToSearch = fileNameToSearch;
	}

	/**
	 * @return the result
	 */
	public List<String> getResult() {
		return result;
	}

	/**
	 * @return the directory
	 */
	public List<String> getDirectory() {
		return directory;
	}

	/**
	 * @param directory
	 * @param fileNameToSearch
	 */
	public void searchDirectory(File directory, String fileNameToSearch) {
		setFileNameToSearch(fileNameToSearch);
		if (directory.isDirectory()) {
			search(directory);
		} else {
			System.out.println("\n\""+directory + "\" is not a directory!");
		}
	}

	/**
	 * @param file
	 */
	private void search(File file) {
		// If we have permission to read this directory.
		if (file.canRead()) {
			for (File temp : file.listFiles()) {
				if (temp.isDirectory()) {
					search(temp);
				} else {
					if (temp.getName().toLowerCase()
							.contains(getFileNameToSearch().toLowerCase())) {
						result.add(temp.getName() + " : " + temp.getParent());
					}
				}
			}
		} else {
			System.out.println(file.getAbsoluteFile() + "Permission Denied");
		}
	}
}
