CREATE TABLE IF NOT EXISTS  Phones (
  id bigint NOT NULL auto_increment,
  model  VARCHAR(40) NOT NULL,
  color VARCHAR(40) NOT NULL,
  displaySize VARCHAR(35) NOT NULL,
  price DECIMAL(10,0) NOT NULL,
  PRIMARY KEY (id)
);