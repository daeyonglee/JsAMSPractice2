package kr.or.kosta.ams.main.service;

import java.util.List;

import org.json.simple.JSONObject;

import kr.or.kosta.ams.common.dao.DaoFactory;
import kr.or.kosta.ams.main.dao.AmsDao;
import kr.or.kosta.ams.main.dao.JdbcAmsDao;
import kr.or.kosta.ams.main.domain.Account;

/**
 * AMS 계좌 관련 Service 구현체
 * @author 이대용
 *
 */
public class AmsServiceImpl implements AmsService{

	AmsDao dao = (AmsDao)DaoFactory.getInstance().getDao(JdbcAmsDao.class);
	
	/* 계좌 전체 목록 조회 */
	@Override
	public List<Account> listAll() {
		return dao.listAll();
	}

	/* 계좌 생성 */
	@Override
	public void create(Account account) {
		dao.create(account);
	}
	/* 계좌 검색 */
	@Override
	public List<Account> search(String accNm) {
		return dao.search(accNm);
	}
	
	/* 계좌 조회 */
	@Override
	public Account read(String accNum) {
		return dao.read(accNum);
	}
	
	/* 계좌 삭제 */
	@Override
	public void remove(String accNum) {
		dao.remove(accNum);
	}
}
