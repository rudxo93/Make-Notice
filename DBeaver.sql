create table member_info(
	mi_id varchar(50) primary key,
	mi_pw varchar(500) not null,
	mi_name varchar(50) not null,
	mi_moblie varchar(12) not null,
	mi_tell varchar(12),
	mi_email varchar(50) not null,
	mi_addr varchar(200) not null,
	mi_insertdt varchar(50) default to_char(now(), 'YYYYMMDDHH24MISS'::text)
)

select * from member_info 	


insert into member_info (
	mi_id,
	mi_pw,
	mi_name,
	mi_moblie,
	mi_tell,
	mi_email,
	mi_addr
)values(
	'adm2',
	'13',
	'������1',
	'010125678',
	'02145678',
	'admin@admin.admin',
	'����'
)

select count(*) from member_info where mi_id = 'admin';

select * from member_info mi
where mi_id = 'admin@naver.com' and mi_pw = '113'

select * from member_info mi where mi_id = 'admin1'

select * from member_info

update member_info set mi_pw = '12',mi_tell = '111', mi_moblie = '13332', mi_email = 'asdf@avvx.vx', mi_addr = '�λ���' where mi_id = 'admin2';

delete from member_info where mi_id = 'asd';


create table notice_info(
	ni_no int primary key ,
	ni_title varchar(50) not null,
	ni_content varchar(500) not null,
	ni_writer varchar(50) not null,
	ni_insertdt varchar(50) default to_char(now(), 'YYYYMMDDHH24MISS'::text)
)

select * from notice_info where ni_no = 1

insert into notice_info(
	ni_no,
	ni_title,
	ni_content ,
	ni_writer ,
	TO_CHAR(TO_TIMESTAMP(ni_insertdt, 'YYYYMMDDHH24MISS'), 'YYYY-MM-DD HH24:MI:SS') "ni_insertdt" 
) values (
	nextval('seq_ni_no') ,
	'����45134567123',
	'�����ΰͰ��� �ƴѰͰ��� �ΰͰ��� ����',
	'admin13',
	now()
)

nextval('seq_ni_no') 

insert into notice_info (ni_no) values (seq_ni_no.nextval)

delete from notice_info where ni_no = '3'

select
	ni_no,
	ni_title,
	ni_content,
	ni_writer,
	TO_CHAR(TO_TIMESTAMP(ni_insertdt, 'YYYYMMDDHH24MISS'), 'YYYY-MM-DD HH24:MI:SS') "ni_insertdt" 
from notice_info ni order by ni_no desc 

select * from notice_info

select
	ni_no,
	ni_title,
	ni_content,
	ni_writer
from notice_info ni where ni_no = '1'

select * from notice_info ni where ni_no = '1'

delete from notice_info where ni_no = '1	'

update notice_info set ni_title = '������', ni_content = '�����Դϴٿ�' where ni_no = '1'

select count(*) from notice_info 

//������ pagePerCnt
//offset 1�� 2�� ���������� �갡 �ٲ��� ��ȸ

select * from notice_info ni limit 2

select
			ni_no,
			ni_title,
			ni_content,
			ni_writer,
			TO_CHAR(TO_TIMESTAMP(ni_insertdt, 'YYYYMMDDHH24MISS'), 'YYYY-MM-DD HH24:MI:SS') "ni_insertdt" 
		from
			notice_info ni
		order by
			ni_no desc
		limit 2 offset 2
			
			
			
			
limit 2 offset 0
limit 4-2 offset 2
limit 6-4 offset 4
limit 8-6 offset 6


