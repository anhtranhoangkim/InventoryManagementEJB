create database QuanLyKho_SOA
use QuanLyKho_SOA

create table Supliers --Nhà cung cấp 
 (
	 Id int identity(1,1) primary key,
	 DisplayName nvarchar(64) not null,
	 Address nvarchar(100) not null,
	 Phone nvarchar(15) not null,
	 Email nvarchar(100) not null,
	 MoreInfo nvarchar(max),
	 ContractDate DateTime not null
 )

insert into Supliers(DisplayName, Address, Phone, Email, ContractDate)
values('H', 'N', '0283881','fifj', '11-11-2011')
values('C', 'N', '0283881','fifj', '11-11-2011')

create table Customers --Khách hàng 
 (
	Id int identity(1,1) primary key,
	DisplayName nvarchar(64) not null,
	Address nvarchar(100) not null,
	Phone nvarchar(15) not null,
	Email nvarchar(100) not null,
	MoreInfo nvarchar(max),
	ContractDate DateTime not null
)

create table Objects --Đối tượng (hàng hóa) 
(
	Id nvarchar(128) primary key,
	DisplayName nvarchar(64) not null,
	IdSuplier int not null,

	foreign key(IdSuplier) references Supliers(Id),
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
	Id nvarchar(128) primary key,
	DateInput DateTime not null
)

create table InputInfo  --Thông tin phiếu nhập 
(
	Id nvarchar(128) primary key,
	Quantity int not null,
	InputPrice float default 0 not null,
	Status nvarchar(max),

	IdObject nvarchar(128) not null,
	IdInput nvarchar(128) not null,

	foreign key (IdObject) references Objects(Id),
	foreign key (IdInput) references Inputs(Id)
)

create table Outputs  --Phiếu xuất
(
	Id nvarchar(128) primary key,
	DateOutput DateTime not null
)


create table OutputInfo  --Thông tin phiếu xuất 
(
	Id nvarchar(128) primary key,
	Quantity int not null,
	OutputPrice float not null,
	Status nvarchar(max),

	IdObject nvarchar(128) not null,
	IdInputInfo nvarchar(128) not null,
	IdCustomer int not null,
	IdOutput nvarchar(128) not null,

	foreign key (IdObject) references Objects(Id),
	foreign key (IdInputInfo) references InputInfo(Id),
	foreign key (IdCustomer) references Customers(Id),
	foreign key (IdOutput) references Outputs(Id)
)

select * from Inputs