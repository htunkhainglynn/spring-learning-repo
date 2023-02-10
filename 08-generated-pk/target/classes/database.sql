CREATE TABLE category (
  id INT GENERATED BY DEFAULT AS IDENTITY(START WITH 1) PRIMARY KEY,
  name VARCHAR(20) NOT NULL UNIQUE
);

CREATE TABLE product (
  id INT GENERATED BY DEFAULT AS IDENTITY(START WITH 1) PRIMARY KEY,
  category_id INT NOT NULL,
  name VARCHAR(20) NOT NULL,
  price INT,
  FOREIGN KEY (category_id) REFERENCES category(id)
);