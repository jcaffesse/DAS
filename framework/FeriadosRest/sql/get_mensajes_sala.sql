USE [finaldas]
GO
/****** Object:  StoredProcedure [dbo].[get_mensajes_sala]    Script Date: 26/06/2016 07:07:25 p.m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER PROCEDURE [dbo].[get_mensajes_sala] (
	@id_sala int,
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
		(m.fecha_mensaje >= @fecha_ultimo OR @fecha_ultimo is NULL)
END