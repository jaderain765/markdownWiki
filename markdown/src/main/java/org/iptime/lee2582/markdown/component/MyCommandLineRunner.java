package org.iptime.lee2582.markdown.component;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyCommandLineRunner implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {
		System.out.println("마크다운 서버를 준비합니다.\n");
		
		// 프로젝트가 실행 될때 한번 설정된 파일들을 가져옵니다.
		new PostBean().copyAllFilesToProject();

	}
}