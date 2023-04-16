create table customer (
    id uuid primary key,
    name varchar(100),
    chat_id bigint not null
);

create table chapter (
    id uuid primary key,
    name varchar(100) not null
);

create table sub_chapter (
    id uuid primary key,
    name varchar(100) not null,
    chapter_id uuid,
    foreign key (chapter_id) references chapter(id)
);

create table task (
    id uuid primary key,
    name varchar(150) not null,
    test text not null,
    answers text not null,
    sub_chapter_id uuid,
    foreign key (sub_chapter_id) references sub_chapter(id)
);

create table theory (
    id uuid primary key,
    name varchar(150) not null,
    info text not null,
    chapter_id uuid,
    foreign key (chapter_id) references chapter(id)
);