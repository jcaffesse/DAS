USE [finaldas]
GO
/****** Object:  StoredProcedure [dbo].[get_invitaciones_usuario]    Script Date: 17/07/2016 06:26:12 p.m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[get_invitacion] (
	@id_origen int,
	@id_destino int
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
		(i.id_usuario = @id_origen)
	AND 
		(i.id_destino = @id_destino)
END