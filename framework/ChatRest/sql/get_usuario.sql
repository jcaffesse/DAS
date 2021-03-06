USE [finaldas]
GO
/****** Object:  StoredProcedure [dbo].[get_usuario]    Script Date: 04/02/2016 06:54:36 p.m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER PROCEDURE [dbo].[get_usuario] (
	@id int = -1,
	@nombre varchar(60) = NULL
)
	-- Add the parameters for the stored procedure here
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	IF (LEN(@nombre) > 0 AND @id = -1)
		SELECT 
			id_usuario,
			LTRIM(RTRIM(nombre_usuario)) as nombre_usuario, 
			LTRIM(RTRIM(email_usuario)) as email_usuario, 
			id_rol  
		FROM 
			[dbo].usuario
		WHERE 
			nombre_usuario = @nombre
	ELSE IF (@id != -1 AND @nombre is NULL)
		SELECT 
			id_usuario,
			LTRIM(RTRIM(nombre_usuario)) as nombre_usuario, 
			LTRIM(RTRIM(email_usuario)) as email_usuario, 
			id_rol  
		FROM 
			[dbo].usuario
		WHERE 
			id_usuario = @id
	ELSE 
		SELECT 
			id_usuario,
			LTRIM(RTRIM(nombre_usuario)) as nombre_usuario, 
			LTRIM(RTRIM(email_usuario)) as email_usuario, 
			id_rol  
		FROM 
			[dbo].usuario
		WHERE 
			id_usuario = @id AND nombre_usuario = @nombre
END