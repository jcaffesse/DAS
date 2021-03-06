USE [finaldas]
GO
/****** Object:  StoredProcedure [dbo].[update_invitacion]    Script Date: 03/07/2016 06:38:13 p.m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
ALTER PROCEDURE [dbo].[update_invitacion]
(
	@id_usuario int,
	@id_destino int,
	@estado int = NULL,
	@mensaje varchar(540) = NULL
)
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

	IF EXISTS (SELECT * FROM dbo.Invitacion WHERE id_usuario = @id_usuario AND id_destino = @id_destino)
		UPDATE 
			dbo.Invitacion 
		SET 
			mensaje_invitacion = isNull(@mensaje, mensaje_invitacion),
			estado_invitacion = isNull(@estado, estado_invitacion)
		WHERE 
			id_usuario = @id_usuario AND id_destino = @id_destino
	ELSE
		INSERT INTO dbo.Invitacion(id_usuario, id_destino, mensaje_invitacion)
			VALUES (@id_usuario, @id_destino, @mensaje);

	IF (@estado = 2)
		INSERT INTO dbo.UsuarioSala(id_usuario, id_sala, estado_usuario)
		SELECT i.id_destino, i.id_sala, 1
		FROM dbo.Invitacion i
		WHERE 
			id_usuario = @id_usuario AND id_destino = @id_destino
END
