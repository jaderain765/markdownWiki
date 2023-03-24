package org.iptime.lee2582.markdown.component;

import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.w3c.dom.ls.LSOutput;

//@Component
public class PostBean {

//	@PostConstruct
//	public void copyFileToProject() throws IOException {
//	    Path sourcePath = Paths.get("D:/PDF");
//	    Path destinationPath = Paths.get("src/main/resources/file.txt");
//	    Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
//	    
//	    System.out.println(System.getProperty("java.io.tmpdir"));
//	}

//	@PostConstruct
	public void copyAllFilesToProject() throws IOException {
		String sourcePathString = "D:/Markdown File/";

		System.out.println("'" + sourcePathString + "'의 경로에서 마크다운 파일들을 가져옵니다.");
		System.out.print("마크다운 서버를 시작합니까? (anyKey Enter:시작, N: 경로수정) : ");

//		Scanner scanner = new Scanner(System.in);
//
//		String tem = scanner.next();
//
//		if (tem.trim().toUpperCase().charAt(0) == 'N') {
//			System.out.print("마크다운 폴더 경로를 입력해주세요. : ");
//			sourcePathString = scanner.next();
//		}
//		scanner.close();

		Path sourcePath = Paths.get(sourcePathString);
		Path destinationPath = Paths.get("src/main/resources/markdown");

		try {
//			Files.delete(destinationPath);
			
			Files.walk(sourcePath).forEach(source -> {
				Path destination = destinationPath.resolve(sourcePath.relativize(source));
				
				System.out.println(destination);
				
				try {
					Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
				} catch (DirectoryNotEmptyException e) {
					String[] temp = destination.toString().split("\\\\");
					System.out.println("'" + temp[temp.length - 1] + "'의 내부자료를 초기화 합니다.");
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
		} catch (NoSuchFileException e) {
			System.out.println("해당경로에 폴더가 없습니다.");
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("마크 다운서버가 실행중입니다.(:5555)");
	}

}
