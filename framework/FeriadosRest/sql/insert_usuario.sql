USE [finaldas]
GO
/****** Object:  StoredProcedure [dbo].[set_sala]    Script Date: 08/11/2015 01:30:59 p.m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[insert_usuario]
(
	@nombre varchar(60),
	@email varchar(60),
	@password varchar(60),
	@id_rol int
)
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

	INSERT INTO dbo.Usuario (nombre_usuario, email_usuario, password_usuario, id_rol) VALUES (LTRIM(RTRIM(@nombre)), LTRIM(RTRIM(@email)), LTRIM(RTRIM(@password)), @id_rol);
END
