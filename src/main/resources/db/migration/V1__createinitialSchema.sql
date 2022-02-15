CREATE TABLE churchs (
  church_id int(11) NOT NULL AUTO_INCREMENT,
  church_name_en varchar(50) NOT NULL,
  church_name_ar varchar(50) NOT NULL,
  country varchar(50) NOT NULL,
  PRIMARY KEY (church_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE roles (
  rol_id int(11) NOT NULL AUTO_INCREMENT,
  rol_name varchar(50) NOT NULL,
  PRIMARY KEY (rol_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE users (
  user_id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(50) NOT NULL,
  user_name varchar(50) NOT NULL,
  password varchar(20) NOT NULL,
  rol_id int(11)NOT NULL,
  church_id int(11) NOT NULL,
  PRIMARY KEY (user_id),
  KEY users_roles (rol_id),
  KEY users_church (church_id),
  CONSTRAINT users_roles_fk FOREIGN KEY (rol_id) REFERENCES roles (rol_id),
  CONSTRAINT users_church_fk FOREIGN KEY (church_id) REFERENCES churchs (church_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE personal_data (
  Emirate_id varchar(50) NOT NULL ,
  name varchar(100) NOT NULL,
  birthdate date NOT NULL,
  birth_location varchar(50) NOT NULL,
  baptism  varchar(50),
  baptism_place  varchar(50),
  education varchar(100),
  education_date date,
  pic varchar(100),
  PRIMARY KEY (Emirate_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE clearance (
  ref_no varchar(50) NOT NULL ,
  Emirate_id varchar(50) NOT NULL ,
  address varchar(100) NOT NULL,
  job varchar(50) NOT NULL,
  job_address varchar(50) NOT NULL,
  military_service varchar(30),
  from_church varchar(50),
  is_previous_engagement varchar(1) NOT NULL,
  is_previous_marriage varchar(1) NOT NULL,
  is_previous_travel_board varchar(1) NOT NULL,
  recognition_regularity_rate varchar(30),
  intake_rate varchar(30),
  father_of_confession varchar(50),
  is_have_childern varchar(1) NOT NULL,
  num_of_childern int(5),
  social_status varchar(50) NOT NULL,
  source_of_marriage_permit varchar(50),
  date_of_marriage_permit date, 
  Status varchar(50) NOT NULL,
  printed_file_attachment varchar(100),
  original_file_attachment varchar(100),
  engagment_file_attachment varchar(100),
  annulment_engagment_attachmnet varchar(100),
  user_id int(11) NOT NULL,
  PRIMARY KEY (ref_no),
  KEY clearance_personal (Emirate_id),
  KEY clearance_users (user_id),
  CONSTRAINT clearance_personal_fk FOREIGN KEY (Emirate_id) REFERENCES personal_data (Emirate_id),
  CONSTRAINT clearance_users_fk FOREIGN KEY (user_id) REFERENCES users (user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE previous_marriage (
  mar_id int(11) NOT NULL AUTO_INCREMENT,
  kind_of_marriage varchar(50) NOT NULL,
  marriage_date date NOT NULL,
  marriage_place varchar(100) NOT NULL,
  priest_father varchar(100) NOT NULL,
  status varchar(50)NOT NULL,
  case_no varchar(50),
  date_of_case date,
  court varchar (100),
  ref_no varchar(50) NOT NULL ,
  PRIMARY KEY (mar_id),
  KEY previous_marriage_clearance (ref_no),
  CONSTRAINT previous_marriage_clearance_fk FOREIGN KEY (ref_no) REFERENCES clearance (ref_no)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE previous_engagment (
  eng_id int(11) NOT NULL AUTO_INCREMENT, 
  engagment_date date NOT NULL,
  engagment_place varchar(100) NOT NULL,
  priest_father varchar(100) NOT NULL,
  status varchar(50)NOT NULL,
  ref_no varchar(50) NOT NULL ,
  engagment_file_attachment varchar(100),
  annulment_engagment_attachmnet varchar(100),
  PRIMARY KEY (eng_id),
  KEY previous_engagment_clearance (ref_no),
  CONSTRAINT previous_engagment_clearance_fk FOREIGN KEY (ref_no) REFERENCES clearance (ref_no)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE child (
  child_id int(11) NOT NULL AUTO_INCREMENT, 
  child_name varchar(100) NOT NULL,
  child_age int(5) NOT NULL,
  baptism varchar(50)NOT NULL,
  ref_no varchar(50) NOT NULL ,
  PRIMARY KEY (child_id),
  KEY child_clearance (ref_no),
  CONSTRAINT child_clearance_fk FOREIGN KEY (ref_no) REFERENCES clearance (ref_no)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


