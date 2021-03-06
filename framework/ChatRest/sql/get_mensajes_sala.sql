USE [finaldas]
GO
/****** Object:  StoredProcedure [dbo].[get_mensajes_sala]    Script Date: 20/11/2016 01:00:19 p.m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER PROCEDURE [dbo].[get_mensajes_sala] (
	@id_sala int,
	@id_msg int = NULL,
	@fecha_desde datetime = NULL
)
	-- Add the parameters for the stored procedure here
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	IF @id_msg = -1
	
	SELECT TOP 1
		id_mensaje,
		id_sala,
		id_usuario,
		texto_mensaje,
		fecha_mensaje
	FROM 
		[dbo].Mensaje m
	WHERE
		m.id_sala = @id_sala
		
 	ORDER BY id_mensaje DESC
		
	ELSE
	
	SELECT 
		id_mensaje,
		id_sala,
		id_usuario,
		texto_mensaje,
		fecha_mensaje
	FROM 
		[dbo].Mensaje m
	WHERE
		m.id_sala = @id_sala
	AND
		(m.id_mensaje > @id_msg OR @id_msg is NULL)
	AND
		(m.fecha_mensaje > @fecha_desde OR @fecha_desde is NULL)		
	
END