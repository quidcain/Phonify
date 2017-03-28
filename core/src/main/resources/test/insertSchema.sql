CREATE TABLE Phones(
  id BIGINT NOT NULL IDENTITY,
  model  VARCHAR(40) NOT NULL,
  color VARCHAR(40) NOT NULL,
  displaySize VARCHAR(35) NOT NULL,
  price DECIMAL(10,0) NOT NULL
);

CREATE TABLE Orders(
  id BIGINT NOT NULL IDENTITY,
  subtotal DECIMAL NOT NULL,
  deliveryPrice DECIMAL NOT NULL,
  firstName varchar(40) NOT NULL,
  lastName varchar(40) NOT NULL,
  deliveryAddress varchar(60) NOT NULL,
  contactPhoneNo varchar(20) NOT NULL
);

CREATE TABLE OrderItems(
  id BIGINT NOT NULL IDENTITY,
  pId BIGINT NOT NULL,
  oId BIGINT NOT NULL,
  quantity BIGINT NOT NULL,
  FOREIGN KEY (pId) REFERENCES Phones(id),
  FOREIGN KEY (oId) REFERENCES Orders(id)
);
