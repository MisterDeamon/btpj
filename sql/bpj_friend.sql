use bpj;
set sql_safe_updates = 0;

show tables;




create table bpj_friend(
user_id varchar(30),
friend_user_id varchar(30),
friend_comment_name varchar(30),
friend_group_id varchar(30),
create_by varchar(50) DEFAULT NULL,
create_date datetime DEFAULT NULL,
update_by varchar(50) DEFAULT NULL,
update_date datetime DEFAULT NULL,
delete_by varchar(50) DEFAULT NULL,
delete_date datetime DEFAULT NULL,
is_deleted int(1) DEFAULT '0'
);


select * from security_user;

insert into bpj_friend(user_id,friend_user_id,friend_comment_name,friend_group_id) 
values('24732322397945855','24726539291590663','Tom_bro','14226539291590522');

select * from security_user;

select * from bpj_friend;

insert into bpj_friend_group(group_id,group_name,group_priority,user_id) values('14226539291590522','myGoodFriends',2,'24726539291590655');

create table bpj_friend_group(
group_id varchar(30) primary key,
group_name varchar(50) default '未命名',
group_priority int(2) default 99,
user_id varchar(30),
create_by varchar(50) DEFAULT NULL,
create_date datetime DEFAULT NULL,
update_by varchar(50) DEFAULT NULL,
update_date datetime DEFAULT NULL,
delete_by varchar(50) DEFAULT NULL,
delete_date datetime DEFAULT NULL,
is_deleted int(1) DEFAULT '0'
);


select * from bpj_friend;
select * from bpj_friend_group g LEFT JOIN bpj_friend f on f.user_id = g.user_id;


SELECT 
distinct
g.group_id, 
f.friend_group_id,
g.group_name, 
g.group_priority, 
g.user_id, 
g.create_by, 
g.create_date, 
g.update_by,
g.update_date,
u.user_name,
f.friend_user_id,
f.friend_comment_name 
FROM bpj_friend_group g
LEFT JOIN bpj_friend f on f.user_id = g.user_id and f.friend_group_id = g.group_id
left join security_user u on u.id = f.friend_user_id
WHERE f.user_id = '24732322397945855' 
ORDER BY g.group_priority;

select * from bpj_friend f left join  security_user u on f.friend_user_id = u.id;
select * from bpj_friend_group g 
left join bpj_friend f on  f.user_id = g.user_id and f.friend_group_id = g.group_id
group by g.group_id;

delete from bpj_friend_group where user_id is null;

