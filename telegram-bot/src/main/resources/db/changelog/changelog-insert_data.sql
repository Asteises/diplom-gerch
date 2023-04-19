insert into customer(id, name, chat_id) values ('e58ed763-928c-4155-bee9-fdbaaadc15f3', 'qwe', 123);

insert into chapter(id, name) values ('118ed763-928c-4155-bee9-fdbaaadc15f3', 'Времена');
insert into chapter(id, name) values ('128ed763-928c-4155-bee9-fdbaaadc15f3', 'Обороты');

insert into sub_chapter(id, name, chapter_id) values ('258ed763-928c-4155-bee9-fdbaaadc15f3', 'Present simple', '118ed763-928c-4155-bee9-fdbaaadc15f3');
insert into sub_chapter(id, name, chapter_id) values ('358ed763-928c-4155-bee9-fdbaaadc15f3', 'Present continuous', '118ed763-928c-4155-bee9-fdbaaadc15f3');
insert into sub_chapter(id, name, chapter_id) values ('458ed763-928c-4155-bee9-fdbaaadc15f3', 'Past simple', '118ed763-928c-4155-bee9-fdbaaadc15f3');

insert into sub_chapter(id, name, chapter_id) values ('658ed763-928c-4155-bee9-fdbaaadc15f3', 'Used to', '128ed763-928c-4155-bee9-fdbaaadc15f3');

INSERT INTO public.task (id, name, test, answers, sub_chapter_id) VALUES ('c5dab062-dd20-11ed-afa1-0242ac120002', 'Do или does', '... your sister speak French fluently?
How often ... your brother go to the gym?
... the store close at 9 PM today?
Why ... your friend always forget to call you back?
... your parents like to travel?
When ... the next train to arrive?
... your boss usually assign you a lot of work?
What time ... the concert start tonight?
... you prefer coffee or tea in the morning?
... the company provide health insurance for its employees', 'does
do
does
does
do
does
does
does
do
does', '258ed763-928c-4155-bee9-fdbaaadc15f3');
INSERT INTO public.task (id, name, test, answers, sub_chapter_id) VALUES ('758ed763-928c-4155-bee9-fdbaaadc15f3', 'Am, is или are', 'He ... 22 years old
There ... many people at the concert last night
She ... tired after a long day at work.
We ... planning a trip to Europe next summer.
My sister ... studying for her exams all week.
I ... smart.
', 'is
are
is
are
is
am', '258ed763-928c-4155-bee9-fdbaaadc15f3');

