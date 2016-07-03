USE [finaldas]
GO
/****** Object:  StoredProcedure [dbo].[insert_sala]    Script Date: 03/07/2016 07:58:52 p.m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
ALTER PROCEDURE [dbo].[insert_sala_privada]
(
	@nombre nvarchar(60),
    @desc   nvarchar(256),
	@color int,
	@id_origen int,
	@id_destino int
)
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;
	DECLARE @IdSala int;

	INSERT INTO dbo.Sala (nombre_sala, desc_sala, tipo_sala, color_sala) 
		VALUES (LTRIM(RTRIM(@nombre)), LTRIM(RTRIM(@desc)), 'private', @color);

	SET @IdSala = SCOPE_IDENTITY();

	INSERT INTO dbo.UsuarioSala (id_usuario, id_sala, estado_usuario)
		VALUES (@id_origen, @IdSala, 1);

	INSERT INTO dbo.UsuarioSala (id_usuario, id_sala, estado_usuario)
		VALUES (@id_destino, @IdSala, 1);		

END