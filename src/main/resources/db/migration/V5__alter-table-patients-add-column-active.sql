alter table patients add active tinyint;
update patients set active = 1;
alter table patients MODIFY COLUMN active tinyint NOT NULL;
