USE [finaldas]
GO
/****** Object:  StoredProcedure [dbo].[insert_usuario_sala]    Script Date: 10/01/2016 04:24:53 p.m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
ALTER PROCEDURE [dbo].[insert_usuario_sala]
(
	@id_usuario int,
	@id_sala int,
	@estado_usuario int
)
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

	INSERT INTO dbo.Actualizacion(id_accion, id_tipo, id_dato, id_sala)
		VALUES ('1', '3', @id_usuario, @id_sala);

	IF EXISTS(SELECT id_usuario FROM dbo.UsuarioSala WHERE id_usuario = @id_usuario AND id_sala = @id_sala)
		UPDATE dbo.UsuarioSala
			SET estado_usuario = @estado_usuario
			WHERE id_usuario = @id_usuario AND id_sala = @id_sala; 
	ELSE
		INSERT INTO dbo.UsuarioSala (id_usuario, id_sala, estado_usuario)
			VALUES (@id_usuario, @id_sala, @estado_usuario);
END
