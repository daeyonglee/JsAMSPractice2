package kr.or.kosta.ams.common.controller;

import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import kr.or.kosta.ams.common.exception.MallException;

/**
 * Factory 패턴 적용 ControllerFactory
 * 세부컨트롤러 매핑 파일로 XML 사용
 * DOM API 활용 XML 파싱 처리
 * 
 * @author 김기정
 */
public class XMLControllerFactory {

	// 요청에 대한 세부 컨트롤러 클래스 생성 및 관리
	private HashMap<String, Controller> controllerMap;
	
	private DocumentBuilder parser;
	private Document document;

	public XMLControllerFactory(String controllerMapperLocation) {
		controllerMap = new HashMap<String, Controller>();
		try {
			parser = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			document = parser.parse(controllerMapperLocation);
	
			NodeList beanList = document.getElementsByTagName("bean");
			System.out.println("--- 컨트롤러 생성 목록 ---");
			for (int i = 0; i < beanList.getLength(); i++) {
				Element bean = (Element)beanList.item(i);
				String name = bean.getAttribute("name");
				String className = bean.getAttribute("class");
				Controller controllerObject = null;
				controllerObject = (Controller) Class.forName(className).newInstance();
				controllerMap.put(name, controllerObject);
				System.out.println(name + "=" + controllerObject);
			}
		}catch (Exception e) {
			throw new MallException("ControllerFactory 세부컨트롤러 생성 중 예외 발생", e);
		}
	}

	public Controller getController(String uri) {
		return controllerMap.get(uri);
	}
}
