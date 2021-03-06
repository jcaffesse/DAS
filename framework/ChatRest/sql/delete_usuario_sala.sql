USE [finaldas]
GO
/****** Object:  StoredProcedure [dbo].[insert_usuario_sala]    Script Date: 01/02/2016 06:48:22 p.m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
ALTER PROCEDURE [dbo].[delete_usuario_sala]
(
	@id_usuario int,
	@id_sala int = NULL
)
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;



	IF EXISTS(
		SELECT id_usuario FROM dbo.UsuarioSala 
		WHERE id_usuario = @id_usuario 
		AND (id_sala = @id_sala OR @id_sala is NULL)
	)

		INSERT INTO dbo.Actualizacion(id_accion, id_tipo, id_dato, id_sala)
		SELECT '4', '3', id_usuario, id_sala
		FROM dbo.UsuarioSala 
		WHERE id_usuario = @id_usuario AND (id_sala = @id_sala OR @id_sala is NULL)

		DELETE FROM dbo.UsuarioSala
			WHERE id_usuario = @id_usuario AND (id_sala = @id_sala OR @id_sala is NULL);
END
