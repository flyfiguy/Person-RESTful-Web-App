

/* 

If removing tables after they have already existed, remove the foriegn keys first.

ALTER TABLE person
DROP CONSTRAINT <ADD CONSTRAINT>

ALTER TABLE address
DROP CONSTRAINT <ADD CONSTRAINT>;
*/

DROP TABLE IF EXISTS person;

DROP TABLE IF EXISTS address;

CREATE TABLE address (
  id bigint  GENERATED BY DEFAULT AS IDENTITY (START WITH 1 INCREMENT BY 1) NOT NULL,
  street VARCHAR(85) DEFAULT NULL,
  city VARCHAR(85) DEFAULT NULL,
  state VARCHAR(60) DEFAULT NULL,
  zip_code VARCHAR(10) DEFAULT NULL,
  country VARCHAR(60) DEFAULT NULL,
  PRIMARY KEY (id),
);

CREATE TABLE person (
   id bigint  GENERATED BY DEFAULT AS IDENTITY (START WITH 1 INCREMENT BY 1) NOT NULL,
   address_id bigint DEFAULT NULL,
   first_name VARCHAR(20) DEFAULT NULL,
   last_name VARCHAR(20) DEFAULT NULL,
   PRIMARY KEY (id),
   FOREIGN KEY (address_id) REFERENCES address(id)   
);
	