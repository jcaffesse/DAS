USE [finaldas]
GO
/****** Object:  StoredProcedure [dbo].[update_mensaje]    Script Date: 02/02/2016 07:44:00 p.m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
ALTER PROCEDURE [dbo].[update_mensaje]
(
	@id_mensaje int,
	@texto_mensaje varchar(540),
	@id_usuario int,
	@id_sala int
)
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

	IF EXISTS (SELECT * FROM dbo.Mensaje WHERE id_mensaje = @id_mensaje)
		UPDATE 
			dbo.Mensaje 
		SET 
			texto_mensaje = isNull(@texto_mensaje, texto_mensaje)
		WHERE
			id_mensaje = @id_mensaje;
	ELSE
		INSERT INTO dbo.Mensaje(texto_mensaje, id_sala, id_usuario)
			VALUES (@texto_mensaje, @id_sala, @id_usuario);
END
