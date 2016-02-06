USE [finaldas]
GO
/****** Object:  StoredProcedure [dbo].[update_usuario_sala]    Script Date: 02/02/2016 07:31:49 p.m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
ALTER PROCEDURE [dbo].[update_usuario_sala]
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

	IF EXISTS(SELECT id_usuario FROM dbo.UsuarioSala WHERE id_usuario = @id_usuario AND id_sala = @id_sala)
		UPDATE dbo.UsuarioSala
			SET estado_usuario = isNull(@estado_usuario, estado_usuario)
			WHERE id_usuario = @id_usuario AND id_sala = @id_sala; 
END
