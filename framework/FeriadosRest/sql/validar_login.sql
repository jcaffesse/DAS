USE [finaldas]
GO
/****** Object:  StoredProcedure [dbo].[validate_login]    Script Date: 02/01/2016 03:00:07 p.m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER PROCEDURE [dbo].[validate_login] (
	@usuario varchar(60),
	@password varchar(60)
)
AS BEGIN

    DECLARE @Final bit = 0
    SELECT @Final = CASE
        WHEN password_usuario = @password THEN 1
		WHEN password_usuario != @password THEN 0 END
    FROM dbo.Usuario
    WHERE nombre_usuario = @usuario

	SELECT @Final AS isValid;

END