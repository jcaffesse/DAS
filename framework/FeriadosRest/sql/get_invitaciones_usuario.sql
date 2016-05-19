USE [finaldas]
GO
/****** Object:  StoredProcedure [dbo].[get_invitaciones_usuario]    Script Date: 02/02/2016 02:15:47 p.m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER PROCEDURE [dbo].[get_invitaciones_usuario] (
	@id_usuario int
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
		fecha_invitacion,
		mensaje_invitacion,
		CASE WHEN i.id_usuario = @id_usuario THEN 'S' ELSE 'R' END AS estado		
	FROM 
		[dbo].Invitacion i
	WHERE
		i.id_usuario = @id_usuario OR i.id_destino = @id_usuario
END