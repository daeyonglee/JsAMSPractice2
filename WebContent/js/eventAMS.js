/**
 * global variable
 */
var accountManager = null;


/**
 * 이벤트 등록
 * @returns
 */
function eventRegist() {
	// 버튼 클릭 이벤트
	btnIns();
	/*btnSelByAccOwner();
	btnSelByAccNum();
	btnDelByAccNum();
	
	// 텍스트 필드 엔터키 이벤트
	inputSelByAccNum();
	inputSelByAccOwner();
	inputIns();
	
	// 계좌종류 변경 될때 이벤트 처리
	selectSel();*/
	
}

/**
 * 신규등록
 * @returns
 */
function btnIns(){
	$('#ins').on('click', function(e){
		
		err('');
		
		var accType   = $('#accType option:selected').val();
		var accNum    = $('input[name="accNum"]').val();
		var accNm     = $('input[name="accNm"]').val();
		var accPw     = $('input[name="accPw"]').val();
		var restMoney = $('input[name="restMoney"]').val();
		var borrowMoney = $('input[name="borrowMoney"]').val();
		console.log('accType: ' + accType);
		console.log('accNum: ' + accNum);
		console.log('accNm: ' + accNm);
		console.log('accPw: ' + accPw);
		console.log('restMoney: ' + restMoney);
		console.log('borrowMoney: ' + borrowMoney);
		
		var data = {
			accType: accType,
			accNum: accNum,
			accNm: accNm,
			accPw: accPw,
			restMoney: restMoney,
			borrowMoney: borrowMoney
		};
		
		$.ajax({
			url: "amsIns.mall",
			data: $.param(data),
			type: "POST",
			success: function(data){
				alert("정상적으로 등록되었습니다.");
				window.location.href='ams.mall';
			},
			error : function() {
				alert("계좌등록에 실패하였습니다.");
			}
		});
		
		/*if (checkValid(3)){
			
		}*/
		
	});
	
	/*
	document.getElementById('ins').addEventListener('click', function(e) {
		err('');
		
		var accountNum   = document.getElementsByTagName('table').item(0).getElementsByTagName('tr').item(1).getElementsByTagName('td').item(1).getElementsByTagName('input').item(0).value;
		var accountOwner = document.getElementsByTagName('table').item(0).getElementsByTagName('tr').item(2).getElementsByTagName('td').item(1).getElementsByTagName('input').item(0).value;
		var passwd       = document.getElementsByTagName('table').item(0).getElementsByTagName('tr').item(3).getElementsByTagName('td').item(1).getElementsByTagName('input').item(0).value;
		var restMoney    = document.getElementsByTagName('table').item(0).getElementsByTagName('tr').item(3).getElementsByTagName('td').item(3).getElementsByTagName('input').item(0).value;
		
		if(checkValid(3)) {
			var accountType  = document.getElementsByTagName('table').item(0).getElementsByTagName('tr').item(0).getElementsByTagName('td').item(1).getElementsByTagName('select').item(0).value;
			
			if (accountType == '입출금계좌') {
				accountManager.open(new Account(accountNum, accountOwner, passwd, restMoney));
			} else if (accountType == '마이너스계좌') {
				var borrowMoney = document.getElementsByTagName('table').item(0).getElementsByTagName('tr').item(4).getElementsByTagName('td').item(1).getElementsByTagName('input').item(0).value;
				accountManager.open(new MinusAccount(accountNum, accountOwner, passwd, restMoney, borrowMoney));
			}
			
			err('정상적으로 등록되었습니다.');
			
			setTimeout(function() {
				err('');
				clear();
				document.getElementById('selAll').click();
			}, 2000);
			
		}
	}, false);
	*/
}

/**
 * 소유주로 계좌 조회 (다건 조회)
 * @returns
 */
function btnSelByAccOwner(){
	document.getElementById('selByAccOwner').addEventListener('click', function(e) {
		err('');
		 
		if(checkValid(1)){
			var accountOwner = document.getElementsByTagName('table').item(0).getElementsByTagName('tr').item(2).getElementsByTagName('td').item(1).getElementsByTagName('input').item(0).value;
			var acc = accountManager.search(accountOwner);
			
			if (acc == null || acc == "") {
				err('검색된 결과가 없습니다.');
				accountNum.focus();
				return false;
			} else {
				print(acc);
			}
 		}
		
	}, false);
}

/**
 * 계좌 번호로 조회 (단건 조회)
 * @returns
 */
function btnSelByAccNum() {
	document.getElementById('selByAccNum').addEventListener('click', function(e) {
		err('');
		
		if (checkValid(0)) {
			var accountNum    = document.getElementsByTagName('table').item(0).getElementsByTagName('tr').item(1).getElementsByTagName('td').item(1).getElementsByTagName('input').item(0);
			var acc           = accountManager.get(accountNum.value);
			
			if (acc == null || acc == "") {
				err('검색된 결과가 없습니다.');
				accountNum.focus();
				return false;
			} else {
				print([acc]);
			}
		}
	}, false)
}

/**
 * 계좌 번호로 계좌 삭제 (단건 삭제)
 * @returns
 */
function btnDelByAccNum() {
	document.getElementById('delByAccNum').addEventListener('click', function(e) {
		err('');
		
		if (checkValid(2)) {
			var accountNum = document.getElementsByTagName('table').item(0).getElementsByTagName('tr').item(1).getElementsByTagName('td').item(1).getElementsByTagName('input').item(0);
			var result = accountManager.remove(accountNum.value);
			if (result == -1){
				// 삭제불가
				err('일치하는 계좌번호가 존재하지 않습니다.')
				accountNum.focus();
				return false;
			} else {
				err('정상적으로 삭제되었습니다');
				print(accountManager.listAll());
			}
		}
	}, false)
}

/**
 * 계좌번호 input태그에 엔터키 입력 시 조회버튼 수행
 * @returns
 */
function inputSelByAccNum() {
	document.getElementById('selByAccNum').parentElement.firstChild.nextSibling.addEventListener('keypress', function(e) {
		if (e.charCode == 13) {
			document.getElementById('selByAccNum').click();
		}
	}, false);
}

/**
 * 소유주 input태그에 엔터키 입력 시 검색버튼 수행
  * @returns
 */
function inputSelByAccOwner() {
	document.getElementById('selByAccOwner').parentElement.firstChild.nextSibling.addEventListener('keypress', function(e) {
		if (e.charCode == 13) {
			document.getElementById('selByAccOwner').click();
		}
	}, false);
}

/**
 * 대출금액 input태그에 엔터키 입력 시 신규등록 수행
 * @returns
 */
function inputIns() {
	document.getElementById('ins').parentElement.firstChild.nextSibling.addEventListener('keypress', function(e) {
		if (e.charCode == 13) {
			document.getElementById('ins').click();
		}
	}, false);
}

/**
 * 
 * @returns flag => 0: 조회버튼 유효성 검사
 * 					1: 검색버튼 유효성 검사
 * 					2: 삭제버튼 유효성 검사
 * 					3: 신규등록 유효성 검사
 * 					4: 전체조회 유효성 검사
 */
function checkValid(flag) {
	
	var accType   = $('#accType option:selected').val();
	var accNum    = $('input[name="accNum"]').val();
	var accNm     = $('input[name="accNm"]').val();
	var accPw     = $('input[name="accPw"]').val();
	var restMoney = $('input[name="restMoney"]').val();
	var borrowMoney = $('input[name="borrowMoney"]').val();
	
	switch(flag) {
	case 0:
		if (accNum == "" || accNum == null || accNum == undefined) {
			err('계좌번호를 입력하세요.');
			accNum.focus();
			return false;
		}
		return true;
		break;
		
	case 1:
		
		if (accNm == "" || accNm == null || accNm == undefined) {
			err('예금주명을 입력하세요.');
			accNm.focus();
			return false;
		}
		
		break;
		
	case 2:
		
		if (accNum == "" || accNum == null) {
			err('계좌번호를 입력하세요.');
			accNum.focus();
			return false;
		}
		return true;
		
		break;
	case 3:
		var regAccNum      = /^[0-9]{4}-[0-9]{4}-[0-9]{4}$/;
		var regAccOnwer    = /^[가-힣]{1,5}$/;
		var regRestMoney   = /^[^0]+[0-9]{0,10}$/;
		var regBorrowMoney = /^[^0]+[0-9]{0,10}$/;
		
		if (!regAccNum.test(accNum)) {
			err('계좌번호 형식이 유효하지 않습니다.ex) 1111-2222-3333');
			accountNum.focus();
			return false;
		}
		
		if (!regAccOnwer.test(accNm)) {
			err('소유주는 한글만 가능하며, 최대 5글자까지 가능합니다.');
			accountOwner.focus();
			return false;
		}
		
		if (accPw == "" || accPw == null || accPw == undefined) {
			err('비밀번호를 입력하세요.');
			passwd.focus();
			return false;
		}
		
		if (!regRestMoney.test(restMoney)) {
			err('입금금액은 최소 1원이상입니다.');
			restMoney.focus();
			return false;
		}
		
		var accType   = $('#accType option:selected').val();
		
		if (accType == '1') {
			if (!regBorrowMoney.test(borrowMoney.value)) {
				if (!/^[^1-9]+$/.test(borrowMoney.value)) {
					err('대출금액을 입력하세요.');
					borrowMoney.focus();
					return false;
				}		
			}
		}
		
		/*var list = accountManager.listAll();
		
		for (var i in list) {
			if(list[i].accountNum == accountNum.value) {
				err('이미 존재하는 계좌번호입니다.');
				accountNum.focus();
				return false;
			}
		}*/
		
		return true;
	}
}

/**
 * 계좌종류 변경될때 대출금액 input 활성화 / 비활성화
 * 입출금계좌  => 활성화
 * 마이너스계좌 => 비활성화
 * @returns
 */
function selectSel() {
	/*document.getElementById('sel').addEventListener('change', function(e) {
		var accountType = e.target.value;
		var borrowMoney  = document.getElementsByTagName('table').item(0).getElementsByTagName('tr').item(4).getElementsByTagName('td').item(1).getElementsByTagName('input').item(0);
		
		if (accountType == '입출금계좌') {
			borrowMoney.disabled = true;
		} else {
			borrowMoney.disabled  = false;
		}
		
	}, false);*/
}

/**
 * 입력된 모든 값들 초기화
 * @returns
 */
function clear() {
	document.getElementById('ins').parentElement.firstChild.nextSibling.value           = "";
	document.getElementById('selByAccOwner').parentElement.firstChild.nextSibling.value = "";
	document.getElementById('selByAccNum').parentElement.firstChild.nextSibling.value   = "";
	document.getElementsByTagName('table').item(0).getElementsByTagName('tr').item(3)
	                                              .getElementsByTagName('td').item(1).getElementsByTagName('input').item(0).value = "";
	document.getElementsByTagName('table').item(0).getElementsByTagName('tr').item(3)
	                                              .getElementsByTagName('td').item(3).getElementsByTagName('input').item(0).value = "";
}

/**
 * 
 * @returns errText => 에러내용
 */
function err(errText){
	document.getElementById('warning').innerHTML = errText;
}

/**
 * 화면에 출력하기
 * @param accounts => 계좌가 담겨있는 배열
 * @returns
 */
function print(accounts) {
	var input = document.getElementsByTagName('table').item(1).getElementsByTagName('tbody').item(0);
	var tr = input.getElementsByTagName('tr');
	
	if (tr.length > 1) {
		for (var x=tr.length-1; x>0; x--) {
			input.removeChild(tr.item(x));
		}
	}
	
	for (var i in accounts) {
		var tr = document.createElement('tr');
		input.appendChild(tr);
		var arr = accounts[i].toString().split(",");
		if (accounts[i].constructor == MinusAccount){
			tr.innerHTML += "<td>"+arr[0]+"</td>";
			tr.innerHTML += "<td>"+arr[1]+"</td>";
			tr.innerHTML += "<td>"+arr[2]+"</td>";
			tr.innerHTML += "<td>"+arr[4]+"</td>";
			tr.innerHTML += "<td>"+arr[5]+"</td>";
		} else {
			tr.innerHTML += "<td>"+arr[0]+"</td>";
			tr.innerHTML += "<td>"+arr[1]+"</td>";
			tr.innerHTML += "<td>"+arr[2]+"</td>";
			tr.innerHTML += "<td>"+arr[4]+"</td>";
			tr.innerHTML += "<td>0</td>";
		}
	}
}