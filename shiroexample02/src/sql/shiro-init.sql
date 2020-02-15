insert into users(username, password, password_salt) values('gu', '123', null);
insert into user_roles(username, role_name) values('gu', 'role1');
insert into user_roles(username, role_name) values('gu', 'role2');
insert into roles_permissions(role_name, permission) values('role1', '+user1+10');
insert into roles_permissions(role_name, permission) values('role1', 'user1:*');
insert into roles_permissions(role_name, permission) values('role1', '+user2+10');
insert into roles_permissions(role_name, permission) values('role1', 'user2:*');