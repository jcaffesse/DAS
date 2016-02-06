USE [finaldas]
GO
/****** Object:  StoredProcedure [dbo].[get_usuarios]    Script Date: 02/02/2016 08:05:43 p.m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER PROCEDURE [dbo].[get_usuarios]
	-- Add the parameters for the stored procedure here
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	SELECT 
		id_usuario,
		LTRIM(RTRIM(nombre_usuario)) as nombre_usuario, 
		LTRIM(RTRIM(email_usuario)) as email_usuario, 
		id_rol  
	FROM 
		[dbo].usuario
END