/*
   sábado, 10 de octubre de 201502:03:58 p.m.
   User: sa
   Server: PC-CASA
   Database: finaldas
   Application: 
*/

/* To prevent any potential data loss issues, you should review this script in detail before running it outside the context of the database designer.*/
BEGIN TRANSACTION
SET QUOTED_IDENTIFIER ON
SET ARITHABORT ON
SET NUMERIC_ROUNDABORT OFF
SET CONCAT_NULL_YIELDS_NULL ON
SET ANSI_NULLS ON
SET ANSI_PADDING ON
SET ANSI_WARNINGS ON
COMMIT
BEGIN TRANSACTION
GO
CREATE TABLE dbo.Salas
	(
	id int NOT NULL,
	nombre nchar(40) NOT NULL,
	descripcion nchar(256) NULL,
	participantes smallint NOT NULL
	)  ON [PRIMARY]
GO
ALTER TABLE dbo.Salas ADD CONSTRAINT
	PK_Salas PRIMARY KEY CLUSTERED 
	(
	id
	) WITH( STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]

GO
ALTER TABLE dbo.Salas SET (LOCK_ESCALATION = TABLE)
GO
COMMIT
select Has_Perms_By_Name(N'dbo.Salas', 'Object', 'ALTER') as ALT_Per, Has_Perms_By_Name(N'dbo.Salas', 'Object', 'VIEW DEFINITION') as View_def_Per, Has_Perms_By_Name(N'dbo.Salas', 'Object', 'CONTROL') as Contr_Per 