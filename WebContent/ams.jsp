<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ams 메인 화면</title>
<link rel="stylesheet" href="./common/css/ams.css" type="text/css">
<script type="text/javascript" src="./common/js/jquery-3.2.0.js"></script>
<script type="text/javascript" src="js/Account.js"></script>
<script type="text/javascript" src="js/MinusAccount.js"></script>
<script type="text/javascript" src="js/AccountManager.js"></script>
<script type="text/javascript" src="js/eventAMS.js"></script>
<script type="text/javascript">

$(function(){
	eventRegist();
});

</script>
</head>
<body>
  <header>
    <span class="title">계좌관리시스템 version1</span>
    <img class="by" src="img/byLDY.png" alt=" ">
  </header>
  
  <article>
   <table>
      <tr>
        <td>계좌종류</td>
        <td colspan="3">
          <select id="accType">
            <option value="1">입출금계좌</option>
            <option value="2">마이너스계좌</option>
          </select>
        </td>
      </tr>
      <tr>
        <td>계좌번호</td>
        <td colspan="3">
          <input type="text" name="accNum">
          <button id="selByAccNum">조회</button>
          <button id="delByAccNum">삭제</button>
        </td>
      </tr>
      <tr>
        <td>예금주명</td>
        <td colspan="3">
          <input type="text" name="accNm">
          <button id="selByAccOwner">검색</button>
        </td>
      </tr>
      <tr>
        <td>비밀번호</td>
        <td><input type="text" name="accPw"></td>
        <td>입금금액</td>
        <td><input type="text" name="restMoney"></td>
      </tr>
      <tr>
        <td>대출금액</td>
        <td colspan="3">
          <input type="text" name="borrowMoney" disabled />
          <button id="ins">신규등록</button>
        </td>
      </tr>
    </table>
  </article>
  
  <div id="accountList">
    <span class="accountListLabel1">계좌목록</span>
    <span id="warning"></span>
    <span class="accountListLabel2">(단위 : 원)</span>
  </div>
  <footer>
    <table>
      <tr>
        <th>계좌종류</th>
        <th>계좌번호</th>
        <th>예금주명</th>
        <th>현재잔액</th>
        <th>대출금액</th>
      </tr>
      <c:forEach var="account" items="${list}">
        <tr>
          <c:choose>
            <c:when test="${account.accType == '1'}">
              <td>입출금계좌</td>
            </c:when>
            <c:otherwise>
              <td>마이너스계좌</td>
            </c:otherwise>
          </c:choose>
          <td>${account.accNum}</td>
          <td>${account.accNm}</td>
          <td>${account.restMoney}</td>
          <td>${account.borrowMoney}</td>
        </tr>
      </c:forEach>
    </table>
  </footer>
</body>
</html>