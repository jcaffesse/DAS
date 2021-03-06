USE [finaldas]
GO
/****** Object:  StoredProcedure [dbo].[get_token]    Script Date: 04/02/2016 05:39:48 p.m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[get_token] (
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
		id_token,
		id_usuario,
		fecha_expiracion,
		token		
	FROM 
		[dbo].Token
	WHERE 
		id_usuario = isNull(@id_usuario, id_usuario)
END