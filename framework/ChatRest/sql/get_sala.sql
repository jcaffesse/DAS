USE [finaldas]
GO
/****** Object:  StoredProcedure [dbo].[get_salas]    Script Date: 02/02/2016 05:32:52 p.m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[get_sala] (
	@id_sala int
)
	-- Add the parameters for the stored procedure here
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	SELECT 
		id_sala,
		LTRIM(RTRIM(nombre_sala)) as nombre_sala,
		LTRIM(RTRIM(desc_sala)) as desc_sala,
		tipo_sala
	FROM
		[dbo].Sala s
	WHERE
		s.id_sala = @id_sala
END
