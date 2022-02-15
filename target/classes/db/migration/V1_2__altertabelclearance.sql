Alter table clearance 
modify is_previous_engagement varchar(1) null;

Alter table clearance 
MODIFY is_previous_marriage varchar(1) null;

Alter table clearance 
modify is_previous_travel_board varchar(1) null;

Alter table clearance 
modify is_have_childern varchar(1) null;

Alter table clearance 
modify social_status varchar(50) null;

Alter table clearance 
Add (gender varchar(10));

Alter table clearance 
Add (church_id int(11) not null);

Alter table clearance 
ADD  CONSTRAINT clearance_church_fk FOREIGN KEY (church_id) REFERENCES churchs(church_id);