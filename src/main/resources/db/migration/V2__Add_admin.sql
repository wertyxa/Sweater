insert into sweater.user(id, username, password, active)
    values(1, 'Admin', '1',true);
insert into sweater.user_role(user_id, roles)
    value(1,'USER'), (1,'ADMIN');