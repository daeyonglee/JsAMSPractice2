-- ���� ���̺� ����
drop table accounts;

-- ���̺� ����
create table accounts (
  accType number(1) not null,
  accNum varchar2(14) not null,
  accNm varchar2(20) not null,
  accPw number(4) not null,
  restMoney number(8),
  borrowMoney number(8),
  regdate date default sysdate
);

-- ���� ���� �߰�
alter table accounts add constraint acc_pk primary key (accNum);
alter table accounts add constraint acc_ck check(accType in (1, 2));

-- ������ ����
insert into accounts
       (
         accType
       , accNum
       , accNm
       , accPw
       , restMoney
       , borrowMoney
       ) values (
         1
       , '1111-2222-3333'
       , '�Ӳ���'
       , 1234
       , 100000
       , 50000
       );

insert into accounts
       (
         accType
       , accNum
       , accNm
       , accPw
       , restMoney
       , borrowMoney
       ) values (
         1
       , '1112-2223-3334'
       , 'ȫ�浿'
       , 4567
       , 120000
       , 5000
       );

insert into accounts
       (
         accType
       , accNum
       , accNm
       , accPw
       , restMoney
       , borrowMoney
       ) values (
         1
       , '2242-3333-4444'
       , '�Ѹ�'
       , 5555
       , 400000
       , 110000
       );

commit;
rollback;

-- ������ ��ȸ
select *
  from accounts;
  
-- ����� ��ü ��ȸ
select accType
     , accNum
     , accNm
     , accPw
     , restMoney
     , borrowMoney
     , to_char(regdate, 'yyyy-mm-dd HH:MM:ss') as regdate
  from accounts
 order by regdate desc;
  