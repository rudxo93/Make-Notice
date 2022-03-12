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

CREATE TABLE public.board_attach (
	board_type varchar(10) NOT NULL,
	board_id int4 NOT NULL,
	file_num int4 NOT NULL,
	file_name varchar(200) NOT NULL,
	saved_file_name varchar(200) NOT NULL,
	file_size int4 NULL,
	upload_dt varchar(14) NULL DEFAULT to_char(now(), 'YYYYMMDDHH24MISS'::text),
	insert_dt varchar(14) NULL DEFAULT to_char(now(), 'YYYYMMDDHH24MISS'::text),
	CONSTRAINT sys_board_file_pk PRIMARY KEY (board_id, file_num)
);

create table gallery_info(
	gi_no int4 primary key,
	gi_title varchar(50) not null,
	gi_content varchar(200) not null,
	gi_writer varchar(50) not null,
	gi_insert_dt varchar(14) NULL DEFAULT to_char(now(), 'YYYYMMDDHH24MISS'::text),
	gi_upload_dt varchar(14) NULL DEFAULT to_char(now(), 'YYYYMMDDHH24MISS'::text)
	
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
	'관리자1',
	'010125678',
	'02145678',
	'admin@admin.admin',
	'몰라'
)

select count(*) from member_info where mi_id = 'admin';

select * from member_info mi
where mi_id = 'admin@naver.com' and mi_pw = '113'

select * from member_info mi where mi_id = 'admin1'

select * from member_info

update member_info set mi_pw = '12',mi_tell = '111', mi_moblie = '13332', mi_email = 'asdf@avvx.vx', mi_addr = '부산산산' where mi_id = 'admin2';

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
	'nextval('seq_ni_no')' ,
	'14132',
	'123123dqdasd',
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

update notice_info set ni_title = '공지요', ni_content = '공지입니다요' where ni_no = '1'

select count(*) from notice_info 

//리밋은 pagePerCnt
//offset 1번 2번 누를때마다 얘가 바껴서 조회

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

select
			ni_no,
			ni_title,
			ni_content,
			ni_writer,
			TO_CHAR(TO_TIMESTAMP(ni_insertdt, 'YYYYMMDDHH24MISS'), 'YYYY-MM-DD HH24:MI:SS') "ni_insertdt" 
		from
			notice_info ni
		where all like concat('%', '', '%') 
		order by
			ni_no desc
		limit 4 offset 0
		

		
		select *
		from notice_info ni 
		order by ni_no  desc

select
	ni.ni_no,
	ni.ni_title,
	ni.ni_content,
	ni.ni_writer,
	ba.file_name, 
	ba.saved_file_name 
from 
	notice_info ni 
	left outer join board_attach ba on(ba.board_id  = ni.ni_no)
where
	ni.ni_no = '73'
	
	
	
	
	
	
	ni.ni_no  = '70'     // 결과 파일을 가지고 있다면 결과가 나오고 게시글에 파일이 존재하지 않다면 결과가 나오지 않는다.
 
		
select * from notice_info ni 

select * from board_attach ba 

	ni.ni_no,
	ni.ni_title,
	ni.ni_content,
	ni.ni_writer,
	ba.file_name 	
		
	파일을 올리면 >> 웹서버저장
	저장에 문제 없으면
	notice_info에 글 등록
	등록한 sequence 조회해서
	board_attach
	
select 
	ba.board_id, 
	ba.file_num,
	ba.file_name,
	ba.saved_file_name,
	ba.file_size
from board_attach ba

select
	saved_file_name 
from
	board_attach ba 
where 
	board_id = '82'

update
			notice_info
		set
			ni_title = #{ni_title},
			ni_content = #{ni_content}
		where
			ni_no = #{ni_no}
			
			
select * from board_attach ba
			
update
			board_attach
		set
			file_name = 'qe4rff.jpg',
			saved_file_name ='F_1645686385485207446844253.jpg',
			file_size = '77952'
		where
			board_id = #{ni_no}
			

insert into board_attach(
			board_type,
			board_id,
			file_num,
			file_name,
			saved_file_name,
			file_size
		)values(
			'Notice',
			#{board_id},
			nextval('seq_file_num'),
			#{file_name},
			#{saved_file_name},
			#{file_size}
		)
		
delete from board_attach where file_num = '39'

select * from notice_info ni 

delete from notice_info  where ni_no = '83'

select * from board_attach ba order by board_id  desc 

select
			file_num,
			file_name,
			saved_file_name,
			file_size
		from
			board_attach
		where
			board_id = '80'

select * from notice_info ni 
			
select * from gallery_info gi 

select * from board_attach ba 

insert into gallery_info (
	gi_no,
	gi_title,
	gi_content,
	gi_writer
) values (
	nextval('seq_gi_no'),
	'갤러리1',
	'사진사진사진사진사진사진사진사진',
	'admin2'
)

delete from gallery_info where gi_title  = '333'

delete from board_attach where board_id = '42'

insert into board_attach(
			board_type,
			board_id,
			file_num,
			file_name,
			saved_file_name,
			file_size
		)values(
			'Gallery',
			'1',
			nextval('seq_file_num'),
			'asdf.jpg',
			'F_123871234551234',
			'1031'
		)

select 
	gi_no ,
	gi_writer ,
	gi_content ,
	gi_title ,
	TO_CHAR(TO_TIMESTAMP(gi_insert_dt, 'YYYYMMDDHH24MISS'), 'YYYY-MM-DD HH24:MI:SS') "gi_insert_dt" 
from
		gallery_info gi

select count(*)
		from gallery_info
		
select
		gi_no,
		gi_title,
		gi_content,
		gi_writer,
		ba.file_name,
		ba.saved_file_name
	from
		gallery_info gi
		left outer join board_attach ba on(ba.board_id = gi.gi_no)
	where
		gi.gi_no  = '43'



	
	
	
	