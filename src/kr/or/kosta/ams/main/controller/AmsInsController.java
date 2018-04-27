package kr.or.kosta.ams.main.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosta.ams.common.controller.Controller;
import kr.or.kosta.ams.common.controller.ModelAndView;
import kr.or.kosta.ams.main.domain.Account;
import kr.or.kosta.ams.main.service.AmsService;
import kr.or.kosta.ams.main.service.AmsServiceImpl;
/**
 * index.mall 요청에 대한 처리 클래스(세부컨트롤러)
 * @author 이대용
 *
 */
public class AmsInsController implements Controller {
	
	AmsService amsService = new AmsServiceImpl();
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		
		Account account = new Account();
		
		ModelAndView mav = new ModelAndView();
		
		Enumeration enums = request.getParameterNames();
		
		while(enums.hasMoreElements()) {
			String key = enums.nextElement().toString();
			String value = request.getParameter(key);
			switch(key) {
				case "accType":
					account.setAccType(Integer.valueOf(value));
					break;
				case "accNum":
					account.setAccNum(value);
					break;
				case "accNm":
					account.setAccNm(value);
					break;
				case "restMoney":
					account.setRestMoney(Integer.valueOf(value));
					break;
				case "borrowMoney":
					if (account.getAccType() == 2) account.setBorrowMoney(Integer.valueOf(value));
					break;
			}
		}
		
		amsService.create(account);
		
		return null;
	}
}
