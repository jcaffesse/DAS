USE [finaldas]
GO
/****** Object:  StoredProcedure [dbo].[update_sala]    Script Date: 02/02/2016 08:32:40 p.m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[update_usuario]
(
	@id int,
	@nombre varchar(60),
	@email varchar(60),
	@password varchar(60),
	@rol int

)
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

	IF EXISTS (SELECT * FROM dbo.Usuario WHERE id_usuario = @id)
		UPDATE 
			dbo.Usuario 
		SET 
			nombre_usuario = isNull(@nombre, nombre_usuario),
			email_usuario = isNull(@email, email_usuario),
			password_usuario = isNull(@password, password_usuario),
			id_rol = isNull(@rol, id_rol)
		WHERE 
			id_usuario = @id;
	ELSE 
		INSERT INTO dbo.Usuario (nombre_usuario, email_usuario, password_usuario, id_rol) 
			VALUES (LTRIM(RTRIM(@nombre)), LTRIM(RTRIM(@email)), LTRIM(RTRIM(@password)), @rol);
END
