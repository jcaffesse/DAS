USE [finaldas]
GO
/****** Object:  StoredProcedure [dbo].[get_mensajes_usuario_salas]    Script Date: 03/07/2016 04:43:38 p.m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER PROCEDURE [dbo].[get_mensajes_usuario_salas] (
	@id_usuario int,
	@fecha_ultimo datetime = NULL
)
	-- Add the parameters for the stored procedure here
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	SELECT 
		m.id_mensaje,
		m.id_sala,
		m.id_usuario,
		texto_mensaje,
		fecha_mensaje
	FROM 
		[dbo].UsuarioSala us
		INNER JOIN [dbo].Mensaje m
			ON us.id_sala = m.id_sala
	WHERE
		us.id_usuario = @id_usuario
	AND 
		us.estado_usuario = 1
	AND
		(m.fecha_mensaje >= @fecha_ultimo OR @fecha_ultimo is NULL)
END