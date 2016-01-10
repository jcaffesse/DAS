USE [finaldas]
GO
/****** Object:  StoredProcedure [dbo].[get_usuarios]    Script Date: 10/01/2016 04:48:06 p.m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER PROCEDURE [dbo].[get_usuarios] (
	@nombre varchar(60)
)
	-- Add the parameters for the stored procedure here
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	IF (LEN(@nombre) > 0)
		SELECT 
			id_usuario,
			LTRIM(RTRIM(nombre_usuario)) as nombre_usuario, 
			LTRIM(RTRIM(email_usuario)) as email_usuario, 
			id_rol  
		FROM 
			[dbo].usuario
		WHERE @nombre = nombre_usuario
	ELSE
		SELECT 
			id_usuario,
			LTRIM(RTRIM(nombre_usuario)) as nombre_usuario, 
			LTRIM(RTRIM(email_usuario)) as email_usuario, 
			id_rol  
		FROM 
			[dbo].usuario
END