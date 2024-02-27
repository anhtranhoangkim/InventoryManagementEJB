create database QuanLyKho_SOA
use QuanLyKho_SOA

create table Suppliers --Nhà cung cấp 
 (
	 Id int identity(1,1) primary key,
	 DisplayName nvarchar(64) not null,
	 Address nvarchar(100) not null,
	 Phone nvarchar(15) not null,
	 Email nvarchar(100) not null,
	 MoreInfo nvarchar(max),
	 ContractDate DateTime not null
 )

--create table Customers --Khách hàng 
-- (
--	Id int identity(1,1) primary key,
--	DisplayName nvarchar(64) not null,
--	Address nvarchar(100),
--	Phone nvarchar(15),
--	Email nvarchar(100),
--	MoreInfo nvarchar(max),
--	ContractDate DateTime
--)

create table Objects --Đối tượng (hàng hóa) 
(
	Id int identity(1,1) primary key,
	DisplayName nvarchar(64) not null,
	IdSupplier int not null,
	Inventory int,

	foreign key(IdSupplier) references Suppliers(Id),
)

create table UserRole --Phân quyền người dùng 
(
	Id int identity(1,1) primary key,
	DisplayName nvarchar(64) not null
)

insert into UserRole(DisplayName) values(N'Admin')
insert into UserRole(DisplayName) values(N'Nhân viên')

select * from UserRole

create table Users --Người dùng 
(
	Id int identity(1,1) primary key,
	DisplayName nvarchar(64) not null,
	UserName nvarchar(100) not null,
	Password nvarchar(max) not null,
	IdRole int not null

	foreign key (IdRole) references UserRole(Id)
)

insert into Users(DisplayName, Username, Password, IdRole) values(N'admin', N'admin', N'admin', 1)
insert into Users(DisplayName, Username, Password, IdRole) values(N'staff', N'staff', N'staff', 2)

select * from Users

create table Inputs --Phiếu nhập 
(
	Id int identity(1,1) primary key,
	DateInput DateTime not null,
	TotalPrice float,
)

create table InputInfo  --Thông tin phiếu nhập 
(
	Id int identity(1,1) primary key,
	Quantity int not null,
	InputPrice float default 0 not null,
	TotalPrice float,

	IdObject int not null,
	IdInput int not null,

	foreign key (IdObject) references Objects(Id),
	foreign key (IdInput) references Inputs(Id)
)

create table Outputs  --Phiếu xuất
(
	Id int identity(1,1) primary key,
	DateOutput DateTime not null,
	TotalPrice float
)


create table OutputInfo  --Thông tin phiếu xuất 
(
	Id int identity(1,1) primary key,
	Customer nvarchar(64) not null,
	Quantity int not null,
	OutputPrice float not null,
	TotalPrice float,

	IdObject int not null,
	IdOutput int not null,

	foreign key (IdObject) references Objects(Id),
	foreign key (IdOutput) references Outputs(Id)
)

-- Inserting 5 records into Suppliers table
INSERT INTO Suppliers (DisplayName, Address, Phone, Email, MoreInfo, ContractDate)
VALUES
    ('Supplier1', '123 Main St, City1', '123-456-7890', 'supplier1@example.com', 'Additional info for Supplier1', '2022-02-27'),
    ('Supplier2', '456 Oak St, City2', '987-654-3210', 'supplier2@example.com', 'Additional info for Supplier2', '2022-02-27'),
    ('Supplier3', '789 Pine St, City3', '555-123-4567', 'supplier3@example.com', 'Additional info for Supplier3', '2022-02-27'),
    ('Supplier4', '321 Elm St, City4', '111-222-3333', 'supplier4@example.com', 'Additional info for Supplier4', '2022-02-27'),
    ('Supplier5', '555 Birch St, City5', '444-555-6666', 'supplier5@example.com', 'Additional info for Supplier5', '2022-02-27');



-- Inserting 5 records into Objects table
INSERT INTO Objects (DisplayName, IdSupplier, Inventory)
VALUES
    ('Product1', 1, 100),
    ('Product2', 2, 50),
    ('Product3', 3, 75),
    ('Product4', 4, 120),
    ('Product5', 5, 90);

select * from Objects

-- Inserting 5 records into Inputs table
INSERT INTO Inputs (DateInput, TotalPrice)
VALUES
    ('2022-02-27 10:00:00', 150.50),
    ('2022-02-28 12:30:00', 200.75),
    ('2022-03-01 09:45:00', 180.00),
    ('2022-03-02 15:20:00', 300.25),
    ('2022-03-03 08:00:00', 250.50);

-- Inserting 10 records into InputInfo table
INSERT INTO InputInfo (Quantity, InputPrice, TotalPrice, IdObject, IdInput)
VALUES
    (10, 15.50, 155.00, 1, 1),
    (20, 9.75, 195.00, 2, 1),
    (15, 12.00, 180.00, 3, 2),
    (25, 12.01, 300.25, 4, 2),
    (30, 8.35, 250.50, 5, 3),
    (12, 20.00, 240.00, 1, 3),
    (18, 15.75, 283.50, 2, 4),
    (22, 11.50, 253.00, 3, 4),
    (16, 18.75, 300.00, 4, 5),
    (24, 10.50, 252.00, 5, 5);

-- Inserting 5 records into Outputs table
INSERT INTO Outputs (DateOutput, TotalPrice)
VALUES
    ('2022-02-27 10:00:00', 150.50),
    ('2022-02-28 12:30:00', 200.75),
    ('2022-03-01 14:45:00', 180.00),
    ('2022-03-02 09:15:00', 120.25),
    ('2022-03-03 11:30:00', 160.50);

-- Inserting 10 records into OutputInfo table
INSERT INTO OutputInfo (Customer, Quantity, OutputPrice, TotalPrice, IdObject, IdOutput)
VALUES
    ('Customer1', 10, 15.00, 150.00, 1, 1),
    ('Customer2', 5, 25.50, 127.50, 2, 2),
    ('Customer3', 8, 20.00, 160.00, 3, 3),
    ('Customer4', 12, 10.00, 120.00, 4, 4),
    ('Customer5', 15, 11.00, 165.00, 5, 5),
    ('Customer1', 7, 22.50, 157.50, 1, 1),
    ('Customer2', 4, 18.75, 75.00, 2, 2),
    ('Customer3', 6, 30.00, 180.00, 3, 3),
    ('Customer4', 9, 12.50, 112.50, 4, 4),
    ('Customer5', 10, 16.50, 165.00, 5, 5);



select * from Inputs
select * from InputInfo;

CREATE TRIGGER UpdateInventory
ON InputInfo
AFTER INSERT, UPDATE, DELETE
AS
BEGIN
    -- Update Inventory for affected Objects after any change in InputInfo
    UPDATE Objects
    SET Inventory = (SELECT ISNULL(SUM(Quantity), 0) FROM InputInfo WHERE IdObject = Objects.Id) 
                  - (SELECT ISNULL(SUM(Quantity), 0) FROM OutputInfo WHERE IdObject = Objects.Id)
    FROM Objects
    WHERE Id IN (SELECT IdObject FROM Inserted UNION SELECT IdObject FROM Deleted);
END;

-- Similar trigger for OutputInfo
CREATE TRIGGER UpdateInventoryOutput
ON OutputInfo
AFTER INSERT, UPDATE, DELETE
AS
BEGIN
    -- Update Inventory for affected Objects after any change in OutputInfo
    UPDATE Objects
    SET Inventory = (SELECT ISNULL(SUM(Quantity), 0) FROM InputInfo WHERE IdObject = Objects.Id) 
                  - (SELECT ISNULL(SUM(Quantity), 0) FROM OutputInfo WHERE IdObject = Objects.Id)
    FROM Objects
    WHERE Id IN (SELECT IdObject FROM Inserted UNION SELECT IdObject FROM Deleted);
END;

-- Create trigger to automatically calculate TotalPrice in InputInfo
CREATE TRIGGER CalculateInputInfoTotalPrice
ON InputInfo
AFTER INSERT, UPDATE
AS
BEGIN
    UPDATE InputInfo
    SET TotalPrice = InputInfo.Quantity * InputInfo.InputPrice
    FROM InputInfo
    INNER JOIN Inserted ON InputInfo.Id = Inserted.Id;
END;

-- Create trigger to automatically calculate TotalPrice in Inputs
CREATE TRIGGER CalculateInputsTotalPrice
ON InputInfo
AFTER INSERT, UPDATE, DELETE
AS
BEGIN
    UPDATE Inputs
    SET TotalPrice = (SELECT ISNULL(SUM(TotalPrice), 0) FROM InputInfo WHERE IdInput = Inputs.Id)
    FROM Inputs
    WHERE Id IN (SELECT IdInput FROM Inserted UNION SELECT IdInput FROM Deleted);
END;

select * from Objects
select * from OutputInfo
