create table if not exists MOVIES (
  id varchar(255) not null,
  vector double precision array,
  primary key (id)
);
