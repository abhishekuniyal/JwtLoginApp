DROP TABLE IF EXISTS customers;
DROP TABLE IF EXISTS items;
 
CREATE TABLE customers (
  cust_id INT PRIMARY KEY,
  cust_name VARCHAR(250) NOT NULL
);

CREATE TABLE items (
  item_id INT PRIMARY KEY,
  item_name VARCHAR(250) NOT NULL,
  item_price INT NOT NULL,
);
 
INSERT INTO customers (cust_id, cust_name) VALUES
  (1, 'Abhishek'),
  (2, 'Kiran');

INSERT INTO items (item_id, item_name, item_price) VALUES
  (1, 'Chicken Tikka', 10),
  (2, 'Chicken Biryani', 20),
  (3, 'Butter Paneer', 12),
  (4, 'Dal Makhani', 20);
