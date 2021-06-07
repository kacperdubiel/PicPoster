create table comments (id uuid not null, added_date timestamp, comment varchar(255), commentator_id uuid, post_id uuid, primary key (id))
create table follows (id uuid not null, follow_date timestamp, followed_id uuid, follower_id uuid, primary key (id))
create table likes (id uuid not null, added_date timestamp, liker_id uuid, post_id uuid, primary key (id))
create table posts (id uuid not null, added_date timestamp, allow_comments boolean, description varchar(255), image_path varchar(255), post_order int4, poster_id uuid, primary key (id))
create table users (id uuid not null, created_date timestamp, description varchar(255), email varchar(255), last_login_date timestamp, login varchar(255), password varchar(255), profile_image_path varchar(255), primary key (id))
alter table users add constraint UK_6dotkott2kjsp8vw4d0m25fb7 unique (email)
alter table users add constraint UK_ow0gan20590jrb00upg3va2fn unique (login)
alter table comments add constraint FKq35ylatmydkgpbu09mlf6ddx2 foreign key (commentator_id) references users
alter table comments add constraint FKh4c7lvsc298whoyd4w9ta25cr foreign key (post_id) references posts
alter table follows add constraint FK45sy1jkos9oy1j4by9y7225nm foreign key (followed_id) references users
alter table follows add constraint FKqnkw0cwwh6572nyhvdjqlr163 foreign key (follower_id) references users
alter table likes add constraint FKmnsakmpre3vbc3bp9wb21bcby foreign key (liker_id) references users
alter table likes add constraint FKry8tnr4x2vwemv2bb0h5hyl0x foreign key (post_id) references posts
alter table posts add constraint FKs8moyosnmwa2sis800p96jajh foreign key (poster_id) references users
