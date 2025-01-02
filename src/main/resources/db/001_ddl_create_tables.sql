--liquibase formatted sql
--changeset vadimstrya:001_ddl_create_tables

-- 1. Пользователи:
create table if not exists user_auth
(
  id          serial primary key,
  login       varchar(128)                                        not null,
  email       varchar(128)                                        not null,
  password    varchar(32)                                         not null,
  jwt_token   varchar(128),
  status      varchar(1) default 'A' check (status in ('A', 'D')) not null,
  create_date date       default now()                            not null
);

comment on table user_auth is 'Пользователи';
comment on column user_auth.id is 'Ид записи';
comment on column user_auth.login is 'Имя';
comment on column user_auth.email is 'Email';
comment on column user_auth.password is 'Пароль';
comment on column user_auth.jwt_token is 'JWT-токен';
comment on column user_auth.status is 'Статус записи: A - активна, D - удалена';
comment on column user_auth.create_date is 'Дата создания записи';

-- 2. Посты пользователей:
create table if not exists user_post
(
  id          serial primary key,
  user_id     integer references user_auth (id)                   not null,
  title       varchar(256)                                        not null,
  text        varchar(1024)                                       not null,
  status      varchar(1) default 'A' check (status in ('A', 'D')) not null,
  create_date date       default now()                            not null
);

comment on table user_post is 'Посты пользователей';
comment on column user_post.id is 'Ид записи';
comment on column user_post.user_id is 'Ид пользователя, ссылка на user_auth (id)';
comment on column user_post.title is 'Заголовок';
comment on column user_post.text is 'Текст поста';
comment on column user_post.status is 'Статус записи: A - активна, D - удалена';
comment on column user_post.create_date is 'Дата создания записи';

-- 3. Изображения в постах пользователей:
create table if not exists user_post_image
(
  id      serial primary key,
  post_id integer references user_post (id) not null,
  content bytea                             not null
);

comment on table user_post_image is 'Изображения в постах пользователей';
comment on column user_post_image.id is 'Ид записи';
comment on column user_post_image.post_id is 'Ид поста, ссылка на user_post (id)';
comment on column user_post_image.content is 'Файл изображения';

-- 4. Друзья пользователей:
create table if not exists user_friend
(
  id             serial primary key,
  user_id        integer references user_auth (id) not null,
  friend_user_id integer references user_auth (id) not null
);

comment on table user_friend is 'Друзья пользователей';
comment on column user_friend.id is 'Ид записи';
comment on column user_friend.user_id is 'Ид пользователя, ссылка на user_auth (id)';
comment on column user_friend.friend_user_id is 'Ид друга пользователя, ссылка на user_auth (id)';

-- 5. Подписки пользователей:
create table if not exists user_subscribe
(
  id                serial primary key,
  user_id           integer references user_auth (id) not null,
  subscribe_user_id integer references user_auth (id) not null
);

comment on table user_subscribe is 'Подписки пользователей';
comment on column user_subscribe.id is 'Ид записи';
comment on column user_subscribe.user_id is 'Ид пользователя, ссылка на user_auth (id)';
comment on column user_subscribe.subscribe_user_id is 'Ид пользователя, на которого подписан, ссылка на user_auth (id)';

-- 6. Заявки в друзья пользователей:
create table if not exists user_friend_request
(
  id             serial primary key,
  user_id        integer references user_auth (id)                                        not null,
  friend_user_id integer references user_auth (id)                                        not null,
  status         varchar(1) default 'A' check (status in ('NEW', 'ACCEPTED', 'DECLINED')) not null,
  create_date    date       default now()                                                 not null
);

comment on table user_friend_request is 'Заявки в друзья пользователей';
comment on column user_friend_request.id is 'Ид записи';
comment on column user_friend_request.user_id is 'Ид пользователя, ссылка на user_auth (id)';
comment on column user_friend_request.friend_user_id is 'Ид пользователя, на которого сделана заявка в друзья, ссылка на user_auth (id)';
comment on column user_friend_request.status is 'Статус записи: NEW - новая, ACCEPTED - принята, DECLINED - отклонена';
comment on column user_friend_request.create_date is 'Дата создания записи';

-- 7. Чаты пользователей:
create table if not exists user_chat
(
  id             serial primary key,
  user_id_first  integer references user_auth (id)                   not null,
  user_id_second integer references user_auth (id)                   not null,
  status         varchar(1) default 'A' check (status in ('A', 'D')) not null,
  create_date    date       default now()                            not null
);

comment on table user_chat is 'Чаты пользователей';
comment on column user_chat.id is 'Ид записи';
comment on column user_chat.user_id_first is 'Ид первого пользователя, участвующего в чате, ссылка на user_auth (id)';
comment on column user_chat.user_id_second is 'Ид второго пользователя, участвующего в чате, ссылка на user_auth (id)';
comment on column user_chat.status is 'Статус записи: A - активна, D - удалена';
comment on column user_chat.create_date is 'Дата создания записи';

-- 8. Сообщения в чатах пользователей:
create table if not exists user_chat_message
(
  id          serial primary key,
  chat_id     integer references user_chat (id)                   not null,
  user_id     integer references user_auth (id)                   not null,
  text        varchar(1024)                                       not null,
  status      varchar(1) default 'A' check (status in ('A', 'D')) not null,
  create_date date       default now()                            not null
);

comment on table user_chat_message is 'Сообщения в чатах пользователей';
comment on column user_chat_message.id is 'Ид записи';
comment on column user_chat_message.chat_id is 'Ид чата, ссылка на user_chat (id)';
comment on column user_chat_message.user_id is 'Ид пользователя, ссылка на user_auth (id)';
comment on column user_chat_message.text is 'Текст сообщения';
comment on column user_chat_message.status is 'Статус записи: A - активна, D - удалена';
comment on column user_chat_message.create_date is 'Дата создания записи';
