package kr.or.kosta.ams.main.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

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
public class AmsSrhController implements Controller {
	
	AmsService amsService = new AmsServiceImpl();
	
	@SuppressWarnings("unchecked")
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		
		String accNm = request.getParameter("accNm");
		
		response.setContentType("text/plain; charset=utf-8");
		
		List<Account> list = amsService.search(accNm);
		JSONArray array = new JSONArray();
		
		for (Account account : list) {
			JSONObject obj = new JSONObject();
			obj.put("accType", account.getAccType());
			obj.put("accNum", account.getAccNum());
			obj.put("accNm", account.getAccNm());
			obj.put("accPw", account.getAccPw());
			obj.put("restMoney", account.getRestMoney());
			obj.put("borrowMoney", account.getBorrowMoney());
			array.add(obj);
		}
		
		try {
			PrintWriter out = response.getWriter();
			out.print(array.toJSONString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return null;
	}
}
