import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class GenericsKbAVLApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean decision = true;
        AVLOptions avlOptions = new AVLOptions();

        do {
            System.out.println("GenericsKbAVLApp!");
            System.out.println("1. Load a file.");
            System.out.println("2. Add a term.");
            System.out.println("3. Search for a node by term.");
            System.out.println("4. Search for a node by term and statement.");
            System.out.println("5. Search for nodes via a file. ");
            System.out.println("6. Operations");
            System.out.println("7. Exit.");
            System.out.println("Choose an option: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1: {
                    String directory = new File("").getAbsolutePath();
                    System.out.println("Enter file name: ");
                    String file = scanner.nextLine();
                    String fileFound = avlOptions.fileFinder(directory, file);
                    System.out.println("Enter amount of lines to read in: ");
                    String fileLines = scanner.nextLine();
                    if (fileFound != null){
                        avlOptions.fillEntries(fileFound, Integer.parseInt(fileLines));
                        System.out.println("AVL Tree filled!");
                    } else {
                        System.out.println(file + " not found!");
                    }
                    break;
                }

                case 2: {
                    System.out.println("Enter term: ");
                    String entryTerm = scanner.nextLine();
                    System.out.println("Enter statement: ");
                    String entryStatement = scanner.nextLine();
                    System.out.println("Enter confidence score: ");
                    double entryConScore = Double.parseDouble(scanner.nextLine());
                    Entry newEntry = new Entry(entryTerm, entryStatement, entryConScore);
                    avlOptions.insertElement(newEntry);
                    System.out.println("Entry added!");
                    break;
                }

                case 3: {
                    System.out.println("Enter term to search for: ");
                    String term = scanner.nextLine();
                    avlOptions.searchElement(term);
                    break;
                }

                case 4: {
                    System.out.println("Enter term to search for: ");
                    String part1 = scanner.nextLine();
                    System.out.println("Enter statement to search for: ");
                    String part2 = scanner.nextLine();
                    avlOptions.searchBoth(part1, part2);
                    break;
                }

                case 5: {
                    String dir1 = new File("").getAbsolutePath();
                    System.out.println("Enter file to search for: ");
                    String searchFile = scanner.nextLine();
                    String queryFound = avlOptions.fileFinder(dir1, searchFile);
                    if (queryFound != null) {
                        try {
                            File fileToSearch = new File(queryFound);
                            Scanner reader = new Scanner(fileToSearch);
                            String data = "";
                            while (reader.hasNextLine()) {
                                data = reader.nextLine();
                                avlOptions.searchElement(data);
                            }
                            reader.close();
                        } catch (IOException io) {
                            io.printStackTrace();
                        }
                    } else {
                        System.out.println(searchFile + " not found!");
                    }
                    break;
                }

                case 6: {
                    avlOptions.printOpCounter();
                    break;
                }

                case 7: {
                    decision = false;
                    break;
                }
            }
        } while (decision);
    }
}
