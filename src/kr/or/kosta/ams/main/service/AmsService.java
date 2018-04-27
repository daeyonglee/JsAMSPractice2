package kr.or.kosta.ams.main.service;

import java.util.List;

import kr.or.kosta.ams.main.domain.Account;

/**
 * AMS 계좌 관련 Service
 * @author 이대용
 *
 */
public interface AmsService {

	/* 계좌 전체 목록 조회 */
	public List<Account> listAll();
	/* 계좌 생성 */
	public void create(Account account);
	/* 계좌 검색 */
	public List<Account> search(String accNm);
	/* 계좌 조회 */
	public Account read(String accNum);
	/* 계좌 삭제 */
	public void remove(String accNum);
}
