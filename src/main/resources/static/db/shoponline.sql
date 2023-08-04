create database shoponline
use shoponline
create table accounts(
	username varchar(20) primary key not null,
	password varchar(70) not null,
	fullname nvarchar(150) not null,
	email varchar(100) not null,
	birthday datetime not null,
	avatar varchar(100) not null,
	address nvarchar(200) not null,
	phone varchar(20)
)


create table roles(
	id varchar(10) primary key,
	name varchar(30)
)

create table authorities(
	id int IDENTITY(1,1) primary key,
	usernameid varchar(20),
	roleid varchar(10),
	foreign key(usernameid) references accounts(username) on delete cascade on update cascade,
	foreign key(roleid) references roles(id) on delete cascade on update cascade
)

create table categories(
	cateid int identity(1,1) primary key,
	name nvarchar(250),
	status bit,
	sort int,
	parentid int,
	metakeyword nvarchar(250),
	metadescription nvarchar(250),
	createBy varchar(20),
	createDate datetime,
	updateBy varchar(20),
	updateDate datetime
)

create table products(
	productId int identity(1,1) primary key,
	name nvarchar(250),
	status int,
	thumbnail nvarchar(50),
	listImage xml,
	price float,
	promotionPrice float,
	vat bit,
	warranty int,
	hot datetime,
	descriptions nvarchar(50),
	detail ntext,
	metakeyword nvarchar(250),
	metadescription nvarchar(250),
	createBy varchar(20),
	createDate datetime,
	updateBy varchar(20),
	updateDate datetime,
	cateID int,
	foreign key(cateid) references categories(cateid) on delete cascade on update cascade,
)

create table productdetail(
	id int identity(1,1) primary key,
	quantity int,
	size int,
	productid int,
	foreign key(productid) references products(productId) on delete cascade on update cascade,
)

create table coupon(
	id int identity(1,1) primary key,
	code varchar(100),
	type varchar(100), --amount giam theo so tien, percent giam theo phan tram
	value float,
	status bit,
	quantity int,
	dateEnd datetime,
	createBy varchar(20),
	createDate datetime,
	updateBy varchar(20),
	updateDate datetime,
)

create table orders(
	orderId int primary key,
	orderdate datetime,
	status bit, --
	delivered bit,
	deliveredDate datetime,
	discount float,
	--ma khach hang
	username varchar(20),
	foreign key(username) references accounts(username) on delete NO ACTION on update NO ACTION,
	name nvarchar(200),
	email varchar(100),
	address nvarchar(250),
	phone varchar(50),
	subtotal float,
	total float,
	createDate datetime,
	updateBy varchar(20),
	updateDate datetime,
	--payment online
	content nvarchar(200),
	bankcode varchar(10),
	datesend datetime,
	banktranno varchar(50),
	cardtype varchar(7),
	paydate datetime,
)

create table orderdetail(
	id int identity(1,1) primary key,
	orderid int,
	productid int,
	productname nvarchar(250),
	price decimal(18,0),
	quantity int,
	foreign key(productid) references products(productId) on delete NO ACTION on update NO ACTION,
	foreign key(orderid) references orders(orderId) on delete cascade on update cascade,
)


create table Favorite(
	id bigint IDENTITY(1,1)  primary key,
	productId int not null,
	username varchar(20) not null,
	foreign key (productId) references products(productId) on delete cascade on update cascade ,
	foreign key (username) references accounts(username) on delete cascade on update cascade
)

----news----
create table postcategory(
	cateid int identity(1,1) primary key,
	name nvarchar(250),
	status bit,
	sort int,
	parentid int,
	metakeyword nvarchar(250),
	metadescription nvarchar(250),
	createBy varchar(20),
	createDate datetime,
	updateBy varchar(20),
	updateDate datetime
)

create table post(
	postId int identity(1,1) primary key,
	name nvarchar(250),
	status int,
	thumbnail nvarchar(50),
	descriptions nvarchar(50),
	detail ntext,
	metakeyword nvarchar(250),
	metadescription nvarchar(250),
	tags nvarchar(200),
	createBy varchar(20),
	createDate datetime,
	updateBy varchar(20),
	updateDate datetime,
	cateID int,
	foreign key(cateid) references categories(cateid),
)

create table comment(
	commentid bigint identity(1,1) primary key,
	name nvarchar(250),
	email varchar(50),
	detail nvarchar(500),
	status bit,
	createBy varchar(20),
	createDate datetime,
	updateBy varchar(20),
	updateDate datetime,
	--khoa ngoai
	postid int,
	foreign key(postid) references post(postId),
)

create table productcomment(
	commentid int identity(1,1) primary key,
	name nvarchar(250),
	email varchar(50),
	detail nvarchar(500),
	status bit,
	createBy varchar(20),
	createDate datetime,
	updateBy varchar(20),
	updateDate datetime,
	--khoa ngoai
	productid int,
	foreign key(productid) references products(productId),
)

create table tag(
	id nvarchar(50) primary key,
	name nvarchar(200)
)

create table posttag(
	postid int ,
	tagid nvarchar(50),
	foreign key(tagid) references tag(id),
)
---menus
create table menu(
	id int primary key,
	name nvarchar(200),
	link nvarchar(200),
	target nvarchar(200),
	description nvarchar(200),
	status bit,
	position varchar(20),
)

--slider--
create table slide(
	id int identity(1,1) primary key,
	name nvarchar(50),
	image varchar(50),
	sort int,
	link nvarchar(50),
	status bit,
	position varchar(20),
)
--thong tin lien he--
create table contact(
	id int identity(1,1) primary key,
	name nvarchar(50),
	detail ntext,
	status bit,
)

---feedback----
create table feedback(
	id int identity(1,1) primary key,
	name nvarchar(200),
	email nvarchar(50),
	images varchar(50),
	address nvarchar(250),
	detail nvarchar(500)
)
