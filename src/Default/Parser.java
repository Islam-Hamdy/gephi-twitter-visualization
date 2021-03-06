package Default;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import twitter4j.Status;

public class Parser {
	private Status currentStatus;
	private String filePath;
	// private File directory;
	// private File[] filesList;
	// private int fileIndex;
	private FileInputStream fin;
	private ObjectInputStream ois;
	private int numOfStatus;

	// private String[] fileNames;

	public Parser(String filePath) {
		this.filePath = filePath;
		this.numOfStatus = 0;
		// this.directory = new File(this.filePath);
		// this.filesList = this.directory.listFiles();
		// this.fileIndex = 0;
		// this.fileNames = new String[this.filesList.length];
		// for (int i = 0; i < this.filesList.length; i++)
		// this.fileNames[i] = this.filesList[i].getName();
	}

	public void initializeParser() throws IOException {
		// fin = new FileInputStream(this.filesList[fileIndex]);
		fin = new FileInputStream(this.filePath);
		ois = new ObjectInputStream(fin);
	}

	// public String[] getFileNames() {
	// return this.fileNames;
	// }

	public File getCurrentfile() {
		return new File(this.filePath);
	}

	// public String getFileName() {
	// return this.filesList[fileIndex].getName();
	// }

	public int countNumberOfStatus() throws IOException {
		try {
			while (true) {
				ois.readObject();
				this.numOfStatus++;
			}
		} catch (Exception e) {
			fin.close();
			ois.close();
			// System.out.println("Number of status read = " +
			// this.numOfStatus);
		}
		return numOfStatus;
	}

	public Status getNextStatus() throws IOException {
		try {
			return currentStatus = (Status) ois.readObject();
		} catch (Exception e) {
			fin.close();
			ois.close();

			return null;

			// if (fileIndex + 1 >= filesList.length)
			// return null;
			//
			// fin = new FileInputStream(this.filesList[++fileIndex]);
			// ois = new ObjectInputStream(fin);
			// return getNextStatus();
		}
	}
}
