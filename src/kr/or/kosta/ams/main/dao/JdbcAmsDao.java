package kr.or.kosta.ams.main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import kr.or.kosta.ams.main.domain.Account;

public class JdbcAmsDao implements AmsDao{
	
	Connection con          = null;
	PreparedStatement pstmt = null;
	ResultSet rs            = null;
	
	private DataSource dataSource;
		
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/* 전체 목록 조회 */
	@Override
	public List<Account> listAll() {
		String sql = "select accType" + 
					 "     , accNum" + 
					 "     , accNm" + 
					 "     , accPw" + 
					 "     , restMoney" + 
					 "     , borrowMoney" + 
					 "     , to_char(regdate, 'yyyy-mm-dd HH:MM:ss') as regdate" + 
					 "  from accounts" + 
					 " order by regdate desc";
		
		List<Account> list = new ArrayList<Account>();
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Account account = new Account();
				account.setAccType(rs.getInt("accType"));
				account.setAccNum(rs.getString("accNum"));
				account.setAccNm(rs.getString("accNm"));
				account.setAccPw(rs.getInt("accPw"));
				account.setRestMoney(rs.getInt("restMoney"));
				account.setBorrowMoney(rs.getInt("borrowMoney"));
				account.setRegdate(rs.getString("regdate"));
				list.add(account);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, con);
		}
		
		return list;
	}
	/* 계좌 등록 */
	@Override
	public void create(Account account) {
		String sql = "insert into accounts" + 
				"       (" + 
				"         accType" + 
				"       , accNum" + 
				"       , accNm" + 
				"       , accPw" + 
				"       , restMoney" + 
				"       , borrowMoney" + 
				"       ) values (" + 
				"         ?" + 
				"       , ?" + 
				"       , ?" + 
				"       , ?" + 
				"       , ?" + 
				"       , ?" + 
				"       )";
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, account.getAccType());
			pstmt.setString(2, account.getAccNum());
			pstmt.setString(3, account.getAccNm());
			pstmt.setInt(4, account.getAccPw());
			pstmt.setInt(5, account.getRestMoney());
			pstmt.setInt(6, account.getBorrowMoney());
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, con);
		}
	}
	
	/* 계좌 조회 */
	@Override
	public Account read(int accNum) {
		// TODO Auto-generated method stub
		return null;
	}
	/* 계좌 검색 */
	@Override
	public List<Account> search(String accNm) {
		// TODO Auto-generated method stub
		return null;
	}
	/* 계좌 삭제 */
	@Override
	public void remove(int accNum) {
		// TODO Auto-generated method stub
		
	}
	
	private void close(ResultSet rs, PreparedStatement pstmt, Connection con) {
		try {
			if (rs != null) rs.close();
			if (pstmt != null) pstmt.close();
			if (con != null) con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
