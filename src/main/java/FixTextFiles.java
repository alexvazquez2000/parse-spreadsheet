import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FixTextFiles {

	public static void main(String[] args) {
		String rootPath = "C:\\Users\\alexv\\workspace\\gs-spring-boot";
		List<String> fileList = findFiles(rootPath);
		System.out.println("files found");
		for (String fileName : fileList) {
			//System.out.println(fileName);
			//step_1_FixFile(fileName);
			step_CheckAndFixEOL(fileName);
			//step_2_UndoFix(fileName);
		}
	}


	private static void step_1_FixFile(String fileName) {
		
		try {
			// Read the entire file content into a String
			String content = "XX " + Files.readString(Path.of(fileName));
			// write the file
			Files.writeString(Path.of(fileName), content);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	private static void step_2_UndoFix(String fileName) {
		
		try {
			// Read the entire file content into a String
			String content = Files.readString(Path.of(fileName));
			if (content.startsWith("XX ")) {
				content = content.substring(3);
			}
			// write the file
			Files.writeString(Path.of(fileName), content);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	private static void step_CheckAndFixEOL(String fileName) {
		try {
			// Read the entire file content into a String
			String content = Files.readString(Path.of(fileName));
			if (content.contains("\r\n")) {
				//content = content.substring(3);
				System.out.println("fileName " + fileName + " needs fixing");
				content = content.replace("\r\n", "\n");
			}
			// write the file
			Files.writeString(Path.of(fileName), content);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
	}

	private static List<String> findFiles(String rootPath) {
		List<String> filesFound = new ArrayList<>();
		File rootDir = new File(rootPath);
		if (rootDir.isDirectory()) {
			File[] listOfFiles = rootDir.listFiles();
			for (File f : listOfFiles) {
				if (f.isDirectory()) {
					filesFound.addAll(findFiles(f.getAbsolutePath() ) );
				} else if (f.isFile() && isCodeFile(f.getName()) ) {
					filesFound.add(f.getAbsolutePath());
				}
			}
		}
		return filesFound;
	}

	private static boolean isCodeFile(String name) {
		if (name.endsWith(".java") 
				|| name.endsWith(".prefs")
				|| name.equals("pom.xml")
				|| name.equals(".classpath")
				|| name.equals(".project")
				|| name.equals(".classpath")
				|| name.equals("README.adoc")
				|| (name.startsWith("org.eclipse") && name.endsWith(".prefs"))
				) {
			return true;
		}
		return false;
	}


}
