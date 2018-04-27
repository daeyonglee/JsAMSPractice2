package kr.or.kosta.ams.main.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosta.ams.common.controller.Controller;
import kr.or.kosta.ams.common.controller.ModelAndView;
public class IndexController implements Controller {
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		
		ModelAndView mav = new ModelAndView();
		
		mav.setView("ams.mall");
			
		return mav;
	}
}
