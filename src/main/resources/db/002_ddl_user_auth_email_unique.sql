--liquibase formatted sql
--changeset vadimstrya:002_ddl_user_auth_email_unique

alter table user_auth
  add constraint user_auth_email_unq unique (email);