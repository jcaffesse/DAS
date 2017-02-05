USE [finaldas]
GO
/****** Object:  StoredProcedure [dbo].[get_actualizaciones_portal]    Script Date: 05/02/2017 01:40:55 p.m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER PROCEDURE [dbo].[get_actualizaciones_portal]
(
	@id_sala int = NULL,
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
		a.id_actualizacion,
		c.nombre_accion,
		t.nombre_tipo,
		a.id_dato,
		a.fecha_actualizacion,
		a.id_sala
	FROM dbo.Actualizacion a 
	INNER JOIN dbo.AccionActualizacion c
		ON a.id_accion = c.id_accion
	INNER JOIN dbo.TipoActualizacion t
		ON a.id_tipo = t.id_tipo
	WHERE
		(a.id_tipo = '1' OR a.id_tipo = '3')
	AND
		(a.id_sala = @id_sala)
	AND
		(a.fecha_actualizacion >= @fecha_ultimo OR @fecha_ultimo is NULL)
END
