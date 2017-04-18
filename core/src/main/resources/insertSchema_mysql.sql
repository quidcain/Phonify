CREATE TABLE IF NOT EXISTS  Phones (
  id BIGINT NOT NULL auto_increment,
  model  VARCHAR(40) NOT NULL,
  color VARCHAR(40) NOT NULL,
  displaySize INT NOT NULL,
  price DECIMAL(10,0) NOT NULL,
  length INT NOT NULL,
  width INT NOT NULL,
  camera INT NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS Orders(
  id BIGINT NOT NULL AUTO_INCREMENT,
  subtotal DECIMAL NOT NULL,
  deliveryPrice DECIMAL NOT NULL,
  firstName varchar(40) NOT NULL,
  lastName varchar(40) NOT NULL,
  deliveryAddress varchar(60) NOT NULL,
  contactPhoneNo varchar(20) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS OrderItems(
  id BIGINT NOT NULL AUTO_INCREMENT,
  pId BIGINT NOT NULL,
  oId BIGINT NOT NULL,
  quantity BIGINT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (pId) REFERENCES Phones(id),
  FOREIGN KEY (oId) REFERENCES Orders(id)
);

