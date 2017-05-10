CREATE TABLE Phones(
  id BIGINT NOT NULL IDENTITY,
  model  VARCHAR(40) NOT NULL,
  color VARCHAR(40) NOT NULL,
  displaySize INT NOT NULL,
  price DECIMAL(10,0) NOT NULL,
  length INT NOT NULL,
  width INT NOT NULL,
  camera INT NOT NULL
);

CREATE TABLE Orders(
  id BIGINT NOT NULL IDENTITY,
  subtotal DECIMAL NOT NULL,
  deliveryPrice DECIMAL NOT NULL,
  firstName VARCHAR(40) NOT NULL,
  lastName VARCHAR(40) NOT NULL,
  deliveryAddress VARCHAR(60) NOT NULL,
  contactPhoneNo VARCHAR(20) NOT NULL,
  additionalInfo VARCHAR(255),
  status VARCHAR(10) DEFAULT 'awaiting',
  CHECK (status in ('awaiting', 'shipped', 'completed', 'cancelled'))
);

CREATE TABLE OrderItems(
  id BIGINT NOT NULL IDENTITY,
  pId BIGINT NOT NULL,
  oId BIGINT NOT NULL,
  quantity BIGINT NOT NULL,
  FOREIGN KEY (pId) REFERENCES Phones(id),
  FOREIGN KEY (oId) REFERENCES Orders(id)
);
