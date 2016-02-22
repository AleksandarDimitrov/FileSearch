package threads.file.search;

import java.util.Scanner;

public class MainThreadDemo {
	
	static void startProgram(){
		boolean isRunningSwich = true;
		FileSearchThread fileSearchThread = new FileSearchThread();

		while (isRunningSwich) {
			System.out.print("Enter comand: ");
			Scanner input = new Scanner(System.in);
			String comand = input.nextLine();
			if (!comand.isEmpty()) {
				switch (comand) {
				case "find":
					System.out.print("Enter Directory: ");
					fileSearchThread.setDirectory(input.nextLine());
					System.out.print("Enter File: ");
					fileSearchThread.setFile(input.nextLine());
					fileSearchThread.start();
					break;
				case "list":
					fileSearchThread.listFile();
					break;
				case "stop":
					isRunningSwich = false;
					input.close();
					System.out.println("The program has stopped!");
					break;
				case "pause":
					synchronized (fileSearchThread) {
						try {
							fileSearchThread.wait();
							System.out.println("Find is interupted: " + fileSearchThread.isInterrupted());
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					break;
				case "resume":
					fileSearchThread.notify();
					break;
				case "help":
					System.out.println("Commands: find, list, stop, pause, resume");
					break;
				default:
					System.out.println("Commands: find, list, stop, pause, resume");
					break;
				}
			}
		}
	}

	public static void main(String[] args) {
		
		startProgram();
	}

}
