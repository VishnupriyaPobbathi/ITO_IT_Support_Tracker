
--Category (category_id, category_desc)
create table IF NOT EXISTS category(
   category_id int primary key,
   category_desc varchar(255)
);

--Sub_Category (sub_category_id, category_id , sub_category_desc)

create table IF NOT EXISTS sub_Category(
    sub_category_id int primary key,
    category_id int,
    sub_category_desc varchar(255),
    FOREIGN KEY(category_id) REFERENCES category(category_id)
);

--Admin_team (admin_id, name, emailId)
create table IF NOT EXISTS admin_team(
    admin_id int primary key,
    name varchar(255),
    email_id varchar(255)
);

--insert into admin_team(admin_id,name,email_id)values(111,'admin1','admin1@gmail.com');
--insert into admin_team(admin_id,name,email_id)values(222,'admin2','admin2@gmail.com');
--insert into admin_team(admin_id,name,email_id)values(333,'admin3','admin3@gmail.com');
--insert into admin_team(admin_id,name,email_id)values(444,'admin4','admin4@gmail.com');
--insert into admin_team(admin_id,name,email_id)values(555,'admin5','admin5@gmail.com');

--status (status_id,status_name)
create table IF NOT EXISTS status(
    status_id int primary key,
    status_name varchar(255)
);

--insert into status(status_id,status_name)values(1,'Open');
--insert into status(status_id,status_name)values(2,'Assigned');
--insert into status(status_id,status_name)values(3,'In Progress');
--insert into status(status_id,status_name)values(4,'Completed');

--priority (priority_id,priority_name)
create table IF NOT EXISTS priority(
    priority_id int primary key,
    priority_name varchar(255)
);

--insert into priority(priority_id,priority_name)values(1,'Low');
--insert into priority(priority_id,priority_name)values(2,'Medium');
--insert into priority(priority_id,priority_name)values(3,'High');
--insert into priority(priority_id,priority_name)values(4,'Critical');


--User (user_id, name, emailId)
create table IF NOT EXISTS Users(
    user_id int primary key,
    name varchar(255),
    email_Id varchar(255)
);
--
--insert into Users(user_id,name,email_Id)values(1,'user1','user1@gmail.com');
--insert into Users(user_id,name,email_Id)values(2,'user2','user2@gmail.com');
--insert into Users(user_id,name,email_Id)values(3,'user3','user3@gmail.com');
--insert into Users(user_id,name,email_Id)values(4,'user4','user4@gmail.com');
--insert into Users(user_id,name,email_Id)values(5,'user5','user5@gmail.com');

