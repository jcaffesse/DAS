USE [finaldas]
GO
/****** Object:  StoredProcedure [dbo].[get_invitaciones_usuario]    Script Date: 03/07/2016 06:45:59 p.m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER PROCEDURE [dbo].[get_invitaciones_usuario] (
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
		id_usuario,
		id_destino,
		id_sala,
		fecha_invitacion,
		mensaje_invitacion,
		estado_invitacion		
	FROM 
		[dbo].Invitacion i
	WHERE
		(i.id_destino = @id_usuario)
	AND 
		(i.estado_invitacion = 0)
	AND
		(i.fecha_invitacion >= @fecha_ultimo OR @fecha_ultimo is NULL)
END