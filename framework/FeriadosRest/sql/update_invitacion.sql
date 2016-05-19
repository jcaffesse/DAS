USE [finaldas]
GO
/****** Object:  StoredProcedure [dbo].[update_invitacion]    Script Date: 02/02/2016 07:44:00 p.m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[update_invitacion]
(
	@id_usuario int,
	@id_destino int,
	@mensaje varchar(540)
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
			mensaje_invitacion = isNull(@mensaje, mensaje_invitacion)
		WHERE 
			id_usuario = @id_usuario AND id_destino = @id_destino
	ELSE
		INSERT INTO dbo.Invitacion(id_usuario, id_destino, mensaje_invitacion)
			VALUES (@id_usuario, @id_destino, @mensaje);
END
