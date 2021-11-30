create table if not exists users (
	id serial primary key,
	password varchar(255),
	email varchar(50),
	username varchar(20),
	unique(username),
	unique(email)
);

create table if not exists roles (
	id serial primary key,
	name varchar(20)
);

create table if not exists user_role (
	user_id bigint not null,
	role_id bigint not null,
	constraint fk_user_user foreign key(user_id) references users(id),
	constraint fk_role_user foreign key(role_id) references roles(id)
);

create table if not exists follower (
	follower_id bigint not null,
	author_id bigint not null,
	constraint fk_follower_user foreign key(follower_id) references users(id),
	constraint fk_author_user foreign key(author_id) references users(id)
);

create table if not exists blog_post (
	id serial primary key,
	title varchar (255) not null,
	author_id bigint not null,
	content text not null,
	time_created timestamp not null,
	time_updated timestamp,
	constraint fk_blog_post_user foreign key (author_id) references users(id)
);

create table if not exists rating (
	user_id bigint not null,
	blog_post_id bigint not null,
	numeric_rating decimal not null,
	constraint fk_rating_user foreign key(user_id) references users(id),
	constraint fk_rating_blog_post foreign key(blog_post_id) references blog_post(id)
);


insert into roles (name) values ('ROLE_USER');
insert into roles (name) values ('ROLE_ADMIN');

insert into users (username, email) values ('admin', 'admin@blogger.com');

insert into user_role (user_id, role_id) values (1, 2);

/* 
 * Add $2a$12$D24uS.P1Mrt.wGIRjZQt7OL5bsg8Gax5VOStgk1s0.XdOqaFA7h7m as password to admin user. The password should be 'admin'.
 * If it doesn't work, head over to https://bcrypt-generator.com/ and encrypt a password. Then put the hash into the password of the admin user.
 */