create table speaker (
  id INT not null primary key,
  name VARCHAR(50)
);

create table conference (
  id INT not null primary key,
  name VARCHAR(50)
);

create table talk (
  id INT not null primary key,
  name VARCHAR(500) not null,
  conferenceid INT not null,
  foreign key (conferenceid) references conference(id)
);


create table talkspeakers (
 talkid INT not null ,
 speakerid INT not null,
 primary key (talkid, speakerid),
 foreign key (talkid) references talk(id),
 foreign key (speakerid) references speaker(id)
);

