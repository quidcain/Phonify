/*DROP TABLE Phones;*/
CREATE TABLE Phones(
  ID BIGINT IDENTITY PRIMARY KEY,
  model  VARCHAR(40),
  color VARCHAR(40),
  displaySize VARCHAR(35),
  price DECIMAL(10,0)
);