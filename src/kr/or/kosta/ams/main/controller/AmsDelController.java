package kr.or.kosta.ams.main.controller;

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
public class AmsDelController implements Controller {
	
	AmsService amsService = new AmsServiceImpl();
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		
		String accNum = request.getParameter("accNum");
		
		amsService.remove(accNum);
		
		return null;
	}
}
