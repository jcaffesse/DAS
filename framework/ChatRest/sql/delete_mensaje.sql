USE [finaldas]
GO
/****** Object:  StoredProcedure [dbo].[delete_usuario]    Script Date: 02/02/2016 06:27:00 p.m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
ALTER PROCEDURE [dbo].[delete_mensaje]
(
	@id int
)
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

	INSERT INTO dbo.Actualizacion(id_accion, id_tipo, id_dato, id_sala)
	SELECT '4', '1', id_mensaje, id_sala
	FROM dbo.Mensaje
		WHERE id_mensaje = @id


	DELETE FROM dbo.Mensaje WHERE id_mensaje = @id

END
