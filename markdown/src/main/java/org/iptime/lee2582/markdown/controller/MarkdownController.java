package org.iptime.lee2582.markdown.controller;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MarkdownController {
//	private final static String LOCAL_MANUAL_PATH = "D:/Markdown/PDF/";
//	private final static String LOCAL_MANUAL_PATH = "static/manuals/";
	private final static String LOCAL_MANUAL_PATH = "markdown/";
	
	@RequestMapping("/")
	public String index() {
		return "index";
	}

	@GetMapping("/doc/{page}")
	public String markdownView(@PathVariable("page") String page, Model model) {
		String markdownValueFormLocal;
		
		try {
			markdownValueFormLocal = getMarkdownValueFormLocal(page);
			Parser parser = Parser.builder().build();
			Node document = parser.parse(markdownValueFormLocal);
			HtmlRenderer renderer = HtmlRenderer.builder().build();
			
			model.addAttribute("contents", renderer.render(document));
			return "viewType1";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "error";
	}
	
	/**
	 * markdown 파일을 읽어, html 문법으로 치환 합니다.
	 * 
	 * @param manualPage 마크다운 파일경로를 가져옵니다. 확장자(.md)는 제외합니다. 
	 * @return 변환된 html을 문자열 형태로 반환합니다.
	 * @throws Exception
	 */
	public String getMarkdownValueFormLocal(String manualPage) throws Exception {
		String filePath = LOCAL_MANUAL_PATH + manualPage + ".md";
		
        StringBuilder stringBuilder = new StringBuilder();
        ClassPathResource classPathResource = new ClassPathResource(filePath);
        

        //BufferedReader br = Files.newBufferedReader(Paths.get(filePath)); // 로컬경로에서 읽어올 경우
        BufferedReader br = Files.newBufferedReader(Paths.get(classPathResource.getURI()));
        br.lines().forEach(line -> stringBuilder.append(line).append("\n"));

        return stringBuilder.toString();
    }
}
