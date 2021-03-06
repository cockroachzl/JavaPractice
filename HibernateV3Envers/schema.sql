
    alter table EVENTS_AUD 
        drop constraint FK58B4CE6AA7C21108

    alter table EVENTS_AUD 
        drop constraint FK58B4CE6ADF74E053

    drop table EVENTS if exists

    drop table EVENTS_AUD if exists

    drop table REVINFO if exists

    create table EVENTS (
        id bigint not null,
        EVENT_DATE timestamp,
        title varchar(255),
        primary key (id)
    )

    create table EVENTS_AUD (
        id bigint not null,
        REV integer not null,
        REVTYPE tinyint,
        REVEND integer,
        REVEND_TSTMP timestamp,
        EVENT_DATE timestamp,
        title varchar(255),
        primary key (id, REV)
    )

    create table REVINFO (
        REV integer generated by default as identity,
        REVTSTMP bigint,
        primary key (REV)
    )

    alter table EVENTS_AUD 
        add constraint FK58B4CE6AA7C21108 
        foreign key (REVEND) 
        references REVINFO

    alter table EVENTS_AUD 
        add constraint FK58B4CE6ADF74E053 
        foreign key (REV) 
        references REVINFO
