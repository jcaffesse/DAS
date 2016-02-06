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
CREATE PROCEDURE [dbo].[delete_usuario_sala]
(
	@id_usuario int,
	@id_sala int
)
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

	IF EXISTS(SELECT id_usuario FROM dbo.UsuarioSala WHERE id_usuario = @id_usuario AND id_sala = @id_sala)
		DELETE FROM dbo.UsuarioSala
			WHERE id_usuario = @id_usuario AND id_sala = @id_sala; 
END
