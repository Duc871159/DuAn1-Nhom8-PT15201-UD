﻿Drop database Duan1_Nhom8_PT15201

Create database Duan1_Nhom8_PT15201
Go

Use Duan1_Nhom8_PT15201
Go


Drop table sach
Go
Create table sach(
	maSach		int not null identity(1, 1) primary key,
	tenSach		Nvarchar(100) not null,
	loaiSach	Nvarchar(50) null,
	tacGia		Nvarchar(50) not null,
	nhaXuatBan	Nvarchar(100) not null,
	viTri		Nvarchar(100),
	soLuong		int,
	hinhAnh		Varchar(100)
)

Drop table nguoiDUng
Go
Create table nguoiDung(
	maNguoiDung		varchar(50) not null primary key,
	matKhau			varchar(50) not null,
	VaiTro			bit not null,
	HoTen			Nvarchar(50) not null,
	DiaChi			Nvarchar(100) null,
	GioiTinh		bit not null,
	NgaySinh		date null,
	Email			varchar(50) not null unique,
	SoDienThoai		varchar(10) not null unique,
	hinhAnh			varchar(100)
)

Drop table NhanVien
Go
Create table nhanVien(
	maNhanVien		int not null identity(1, 1) primary key,
	caLamviec		int not null,
	luong			float not null,
	maNguoiDung		varchar(50) not null unique foreign key(maNguoiDung) references NguoiDung
)

Drop table nguoiDoc
Go
Create table nguoiDoc(
	maNguoiDoc		int not null identity(1, 1) primary key,
	maNguoiDung		varchar(50) not null unique foreign key(maNguoiDung) references NguoiDung
)

Drop table phieuMuon
Go
Create table phieuMuon(
	maPhieuMuon		int not null identity(1, 1) primary key,
	ngayMuon		date not null,
	ngayTra			date not null,
	guiMail			int not null Default(0),
	maNguoidoc		int not null foreign key(maNguoiDoc) references NguoiDoc,
	maNhanVien		int not null foreign key(maNhanVien) references NhanVien,
	trangThai		bit not null default(0)
)

Drop table trangThaiSach
Go
Create table trangThaiSach(
	maTrangThai		int not null identity(1, 1) primary key,
	maSach			int not null foreign key(maSach) references Sach,
	maPhieuMuon		int not null foreign key(maPhieuMuon) references PhieuMuon,
	trangThai		bit not null Default(0)
)

Select * from sach
Select * from nguoiDung
Select * from nhanVien
Select * from nguoiDoc
Select * from phieuMuon
Select * from trangThaiSach

Insert into sach values(N'Chiến Tranh Tiền Tệ - Sự Thống Trị Của Quyền Lực Tài Chính', N'Kinh tế', N'Song Hongbing', N'NXB Lao Động', N'Kệ A3 - Hàng 1', 3, null)
Insert into sach values(N'Tiền Đấu Với Vàng', N'Kinh tế', N'James Rickards', N'NXB Tài Chính', N'Kệ A3 - Hàng 1', 1, null)
Insert into sach values(N'Sổ Tay Nhà Thôi Miên', N'Tâm lý', N'Cao Minh', N'NXB Thế Giới', N'Kệ A1 - Hàng 4', 2, null)

Insert into nguoiDung values('ducna13', '123', 1, N'Nguyễn Anh Đức', N'Hà Nội', 1, '12/13/2000', 'Ducna13@gmail.com', '0333188195', null)
Insert into nguoiDung values('nguoidung1', '321', 0, N'Người dùng 1', N'Hà Nội', 1, '11/14/1994', 'Ducnaph07089@fpt.edu.com', '0333183456', null)

Insert into nhanVien values(1, 4000000, 'ducna13')

Insert into nguoiDoc values('nguoidung1')

Insert into phieuMuon values('07/28/2020', '08/04/2020', 0, 1, 1, 0)


Insert into trangThaiSach values(1, 1, 0)
Insert into trangThaiSach values(2, 1, 0)