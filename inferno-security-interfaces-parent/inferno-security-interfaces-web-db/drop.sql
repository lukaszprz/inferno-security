alter table "inferno_authorization_schema"."inferno_address" drop constraint "FKl8h23rn7vrka3dsovk0r6rb95"
alter table "inferno_authorization_schema"."inferno_person" drop constraint "FKboehpk6lpu5ftdaugce9d1gk0"
alter table "inferno_authorization_schema"."inferno_roles_assigment" drop constraint "FKt4hhiptmxvuvqihixphygmhic"
alter table "inferno_authorization_schema"."inferno_roles_assigment" drop constraint "FKt6xkwgck597y7y8wjn7trgyu8"
drop table if exists "inferno_authorization_schema"."inferno_address" cascade
drop table if exists "inferno_authorization_schema"."inferno_person" cascade
drop table if exists "inferno_authorization_schema"."inferno_roles" cascade
drop table if exists "inferno_authorization_schema"."inferno_roles_assigment" cascade
drop table if exists "inferno_authorization_schema"."inferno_users" cascade
drop sequence "inferno_address_seq"
drop sequence "inferno_person_seq"
drop sequence "inferno_roles_seq"
drop sequence "inferno_user_roles_assigment_seq"
drop sequence "inferno_users_seq"
