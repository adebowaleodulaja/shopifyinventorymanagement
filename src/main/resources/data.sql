DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS supplier;
DROP TABLE IF EXISTS purchases;
DROP TABLE IF EXISTS orders;

CREATE TABLE products (
id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
name VARCHAR(255) NOT NULL,
serialNumber VARCHAR(255) NOT NULL,
quantityReceived INT DEFAULT 0,
quantityLeft INT DEFAULT 0,
totalOrdered INT DEFAULT 0,
minimumTolerance INT NOT NULL,
notify BIT
);

CREATE TABLE supplier (
id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
name VARCHAR(255) NOT NULL,
phoneNumber VARCHAR(25)
);

CREATE TABLE purchases (
id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
supplierId INT NOT NULL,
productId INT NOT NULL,
itemCount INT NOT NULL,
purchaseDate VARCHAR(255) NOT NULL,
CONSTRAINT fk_supplier FOREIGN KEY (supplierId) REFERENCES supplier (id),
CONSTRAINT fk_products FOREIGN KEY (productId) REFERENCES products (id)
);

CREATE TABLE orders (
id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
customerFirstName VARCHAR(255) NOT NULL,
customerLastName VARCHAR(255) NOT NULL,
productId INT NOT NULL,
orderAmount INT NOT NULL,
orderDate VARCHAR(255) NOT NULL,
CONSTRAINT fk_orders FOREIGN KEY (productId) REFERENCES products (id)
);

INSERT INTO products(name,serialNumber,quantityReceived,quantityLeft,totalOrdered,minimumTolerance,notify)
VALUES
('Lexus i330','2345DEFHQREC',200,200,0,0,0),
('Google Tablet','12345ABCEXYZ',1000,1000,0,50,1),
('Shopify Speaker','SHP6453434FGH',50,50,0,0,0),
('Phone Case','PHO8764534CFF',500,500,0,50,1),
('Mini Dolby Speaker','MDS7745343HEE',200,200,0,0,0);

INSERT INTO supplier(name, phoneNumber)
VALUES
('Toyota', '012345678'),
('Google', '0177764532'),
('Apple Inc', '0188234569'),
('XTech', '07412345673'),
('Shopify', '072263534226');

INSERT INTO purchases(supplierId,productId,itemCount,purchaseDate)
VALUES(1,1,10,'09/06/2022 21:55 PM'),
(2,2,25,'09/06/2022 21:55 PM'),
(3,3,30,'09/06/2022 21:57 PM'),
(4,4,50,'09/06/2022 21:57 PM'),
(5,5,55,'09/06/2022 21:58 PM');