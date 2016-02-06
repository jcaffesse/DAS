USE [finaldas]
GO
DROP TABLE dbo.Invitacion
DROP TABLE dbo.UsuarioSala
DROP TABLE dbo.Mensaje
DROP TABLE dbo.Token
DROP TABLE dbo.Usuario
DROP TABLE dbo.Rol
DROP TABLE dbo.Sala
GO
CREATE TABLE dbo.Rol
(
	id_rol int PRIMARY KEY IDENTITY(100,100) NOT NULL,
	tipo_rol varchar(60)
)
GO
CREATE TABLE dbo.Usuario
(
	id_usuario int PRIMARY KEY IDENTITY(1,1) NOT NULL,
	nombre_usuario varchar(60) NOT NULL,
	email_usuario varchar(60) NOT NULL,
	password_usuario varchar(60) NOT NULL,
	id_rol int FOREIGN KEY REFERENCES dbo.Rol(id_rol)
)
GO
CREATE TABLE dbo.Sala
(
	id_sala int PRIMARY KEY IDENTITY(1,1) NOT NULL,
	nombre_sala varchar(60) NOT NULL,
	desc_sala varchar(256) NOT NULL,
	tipo_sala varchar(60) DEFAULT 'public'
)
GO
CREATE TABLE dbo.Mensaje
(
	id_mensaje int PRIMARY KEY IDENTITY(1,1) NOT NULL,
	texto_mensaje varchar(540) NOT NULL,
	fecha_mensaje datetime DEFAULT GETDATE() NOT NULL,
	id_sala int FOREIGN KEY REFERENCES dbo.Sala(id_sala),
	id_usuario int FOREIGN KEY REFERENCES dbo.Usuario(id_usuario)
)
GO
CREATE TABLE dbo.Token
(
	id_token int PRIMARY KEY IDENTITY(1,1) NOT NULL,
	fecha_expiracion datetime DEFAULT DATEADD(day, 1, GETDATE()) NOT NULL,
	token varchar(256) NOT NULL,
	id_usuario int FOREIGN KEY REFERENCES dbo.Usuario(id_usuario) NOT NULL
)
GO
CREATE TABLE dbo.Invitacion
(
	id_usuario int FOREIGN KEY REFERENCES dbo.Usuario(id_usuario),
	id_sala int FOREIGN KEY REFERENCES dbo.Sala(id_sala),
	id_destino int FOREIGN KEY REFERENCES dbo.Usuario(id_usuario),
	fecha_invitacion datetime DEFAULT GETDATE() NOT NULL,
	mensaje_invitacion varchar(256),
	PRIMARY KEY (id_usuario, id_sala, id_destino)
)
GO
CREATE TABLE dbo.UsuarioSala
(
	id_usuario int FOREIGN KEY REFERENCES dbo.Usuario(id_usuario),
	id_sala int FOREIGN KEY REFERENCES dbo.Sala(id_sala),
	estado_usuario int DEFAULT 0,
	fecha_ingreso datetime DEFAULT GETDATE() NOT NULL,
	PRIMARY KEY (id_usuario, id_sala)
)