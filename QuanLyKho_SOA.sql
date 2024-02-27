CREATE DATABASE QuanLyKho_SOA;
USE QuanLyKho_SOA;

-- Table: Suppliers (Nhà cung cấp)
CREATE TABLE Suppliers
(
    Id INT IDENTITY(1,1) PRIMARY KEY,
    DisplayName NVARCHAR(64) NOT NULL,
    Address NVARCHAR(100) NOT NULL,
    Phone NVARCHAR(15) NOT NULL,
    Email NVARCHAR(100) NOT NULL,
    MoreInfo NVARCHAR(MAX),
    ContractDate DATETIME NOT NULL
);

-- Table: Objects (Đối tượng - hàng hóa)
CREATE TABLE Objects
(
    Id INT IDENTITY(1,1) PRIMARY KEY,
    DisplayName NVARCHAR(64) NOT NULL,
    IdSupplier INT NOT NULL,
    Inventory INT,
    FOREIGN KEY (IdSupplier) REFERENCES Suppliers(Id)
);

-- Table: Users (Người dùng)
CREATE TABLE Users
(
    Id INT IDENTITY(1,1) PRIMARY KEY,
    DisplayName NVARCHAR(64) NOT NULL,
    UserName NVARCHAR(100) NOT NULL,
    Password NVARCHAR(MAX) NOT NULL,
);

-- Insert default users
INSERT INTO Users(DisplayName, UserName, Password) VALUES(N'admin', N'admin', N'admin');
INSERT INTO Users(DisplayName, UserName, Password) VALUES(N'staff', N'staff', N'staff');

-- Display users
SELECT * FROM Users;

-- Table: Inputs (Phiếu nhập)
CREATE TABLE Inputs
(
    Id INT IDENTITY(1,1) PRIMARY KEY,
    DateInput DATETIME NOT NULL,
    TotalPrice FLOAT
);

-- Table: InputInfo (Thông tin phiếu nhập)
CREATE TABLE InputInfo
(
    Id INT IDENTITY(1,1) PRIMARY KEY,
    Quantity INT NOT NULL,
    InputPrice FLOAT DEFAULT 0 NOT NULL,
    TotalPrice FLOAT,
    IdObject INT NOT NULL,
    IdInput INT NOT NULL,
    FOREIGN KEY (IdObject) REFERENCES Objects(Id),
    FOREIGN KEY (IdInput) REFERENCES Inputs(Id)
);

-- Table: Outputs (Phiếu xuất)
CREATE TABLE Outputs
(
    Id INT IDENTITY(1,1) PRIMARY KEY,
    DateOutput DATETIME NOT NULL,
    TotalPrice FLOAT
);

-- Table: OutputInfo (Thông tin phiếu xuất)
CREATE TABLE OutputInfo
(
    Id INT IDENTITY(1,1) PRIMARY KEY,
    Customer NVARCHAR(64) NOT NULL,
    Quantity INT NOT NULL,
    OutputPrice FLOAT NOT NULL,
    TotalPrice FLOAT,
    IdObject INT NOT NULL,
    IdOutput INT NOT NULL,
    FOREIGN KEY (IdObject) REFERENCES Objects(Id),
    FOREIGN KEY (IdOutput) REFERENCES Outputs(Id)
);

-- Update Inventory for affected Objects after any change in InputInfo
CREATE TRIGGER UpdateInventory
ON InputInfo
AFTER INSERT, UPDATE, DELETE
AS
BEGIN
    UPDATE Objects
    SET Inventory = (SELECT ISNULL(SUM(Quantity), 0) FROM InputInfo WHERE IdObject = Objects.Id) 
                  - (SELECT ISNULL(SUM(Quantity), 0) FROM OutputInfo WHERE IdObject = Objects.Id)
    FROM Objects
    WHERE Id IN (SELECT IdObject FROM Inserted UNION SELECT IdObject FROM Deleted);
END;

-- Update Inventory for affected Objects after any change in OutputInfo
CREATE TRIGGER UpdateInventoryOutput
ON OutputInfo
AFTER INSERT, UPDATE, DELETE
AS
BEGIN
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

-- Create trigger to automatically calculate TotalPrice in OutputInfo
CREATE TRIGGER CalculateOutputInfoTotalPrice
ON OutputInfo
AFTER INSERT, UPDATE
AS
BEGIN
    UPDATE OutputInfo
    SET TotalPrice = OutputInfo.Quantity * OutputInfo.OutputPrice
    FROM OutputInfo
    INNER JOIN Inserted ON OutputInfo.Id = Inserted.Id;
END;

-- Create trigger to automatically calculate TotalPrice in Outputs
CREATE TRIGGER CalculateOutputsTotalPrice
ON OutputInfo
AFTER INSERT, UPDATE, DELETE
AS
BEGIN
    UPDATE Outputs
    SET TotalPrice = (SELECT ISNULL(SUM(TotalPrice), 0) FROM OutputInfo WHERE IdOutput = Outputs.Id)
    FROM Outputs
    WHERE Id IN (SELECT IdOutput FROM Inserted UNION SELECT IdOutput FROM Deleted);
END;
