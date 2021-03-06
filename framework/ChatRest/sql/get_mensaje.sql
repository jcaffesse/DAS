USE [finaldas]
GO
/****** Object:  StoredProcedure [dbo].[get_usuarios]    Script Date: 02/02/2016 02:15:47 p.m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[get_mensajes_mensaje] (
	@id_mensaje int
)
	-- Add the parameters for the stored procedure here
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	SELECT 
		id_mensaje,
		id_sala,
		id_usuario,
		texto_mensaje,
		fecha_mensaje
	FROM 
		[dbo].Mensaje m
	WHERE
		m.id_mensaje = @id_mensaje
END