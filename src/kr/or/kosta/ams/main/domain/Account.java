package kr.or.kosta.ams.main.domain;

/**
 * Account 도메인
 * @author 이대용
 *
 */
public class Account {
	
	private int accType;
	private String accNum;
	private String accNm;
	private int accPw;
	private int restMoney;
	private int borrowMoney;
	private String regdate;
	
	public Account() {}
	
	public Account(int accType, String accNum, String accNm, int accPw, int restMoney, int borrowMoney,
			String regdate) {
		super();
		this.accType = accType;
		this.accNum = accNum;
		this.accNm = accNm;
		this.accPw = accPw;
		this.restMoney = restMoney;
		this.borrowMoney = borrowMoney;
		this.regdate = regdate;
	}

	public int getAccType() {
		return accType;
	}

	public void setAccType(int accType) {
		this.accType = accType;
	}

	public String getAccNum() {
		return accNum;
	}

	public void setAccNum(String accNum) {
		this.accNum = accNum;
	}

	public String getAccNm() {
		return accNm;
	}

	public void setAccNm(String accNm) {
		this.accNm = accNm;
	}

	public int getAccPw() {
		return accPw;
	}

	public void setAccPw(int accPw) {
		this.accPw = accPw;
	}

	public int getRestMoney() {
		return restMoney;
	}

	public void setRestMoney(int restMoney) {
		this.restMoney = restMoney;
	}

	public int getBorrowMoney() {
		return borrowMoney;
	}

	public void setBorrowMoney(int borrowMoney) {
		this.borrowMoney = borrowMoney;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	@Override
	public String toString() {
		return "Account [accType=" + accType + ", accNum=" + accNum + ", accNm=" + accNm + ", accPw=" + accPw
				+ ", restMoney=" + restMoney + ", borrowMoney=" + borrowMoney + ", regdate=" + regdate + "]";
	}
}
