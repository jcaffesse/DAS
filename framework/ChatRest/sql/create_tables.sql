USE [finaldas]
/*GO
DROP TABLE dbo.Invitacion
DROP TABLE dbo.UsuarioSala
DROP TABLE dbo.Mensaje
DROP TABLE dbo.Token
DROP TABLE dbo.Usuario
DROP TABLE dbo.Rol
DROP TABLE dbo.Sala
DROP TABLE dbo.Actualizacion
DROP TABLE dbo.TipoActualizacion
DROP TABLE dbo.AccionActualizacion
*/
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
	id_destino int FOREIGN KEY REFERENCES dbo.Usuario(id_usuario),
	id_sala int FOREIGN KEY REFERENCES dbo.Sala(id_sala),
	fecha_invitacion datetime DEFAULT GETDATE() NOT NULL,
	mensaje_invitacion varchar(256),
	estado_invitacion int DEFAULT 0,
	PRIMARY KEY (id_usuario, id_destino)
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
GO
CREATE TABLE dbo.TipoActualizacion
(
	id_tipo int PRIMARY KEY IDENTITY(1,1) NOT NULL,
	nombre_tipo varchar(80) NOT NULL
)
GO
CREATE TABLE dbo.AccionActualizacion
(
	id_accion int PRIMARY KEY IDENTITY(1,1) NOT NULL,
	nombre_accion varchar(12) NOT NULL
)
GO
CREATE TABLE dbo.Actualizacion
(
	id_actualizacion int PRIMARY KEY IDENTITY(1,1) NOT NULL,
	id_accion int FOREIGN KEY REFERENCES dbo.AccionActualizacion(id_accion),
	id_tipo int FOREIGN KEY REFERENCES dbo.TipoActualizacion(id_tipo),
	id_dato int NOT NULL,
	fecha_actualizacion datetime DEFAULT GETDATE() NOT NULL,
	id_sala int FOREIGN KEY REFERENCES dbo.Sala(id_sala),
)
GO
INSERT INTO dbo.TipoActualizacion(nombre_tipo) VALUES('Mensaje')
INSERT INTO dbo.TipoActualizacion(nombre_tipo) VALUES('Invitacion')
INSERT INTO dbo.TipoActualizacion(nombre_tipo) VALUES('UsuarioSala')
INSERT INTO dbo.TipoActualizacion(nombre_tipo) VALUES('Sala')
GO
INSERT INTO dbo.AccionActualizacion(nombre_accion) VALUES('Create')
INSERT INTO dbo.AccionActualizacion(nombre_accion) VALUES('Remove')
INSERT INTO dbo.AccionActualizacion(nombre_accion) VALUES('Update')
INSERT INTO dbo.AccionActualizacion(nombre_accion) VALUES('Delete')