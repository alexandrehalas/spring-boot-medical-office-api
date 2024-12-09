alter table doctors add active tinyint;
update doctors set active = 1;
alter table doctors MODIFY COLUMN active tinyint NOT NULL;
