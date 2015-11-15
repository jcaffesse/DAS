USE [finaldas]
GO
/****** Object:  StoredProcedure [dbo].[set_sala]    Script Date: 08/11/2015 01:30:59 p.m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
ALTER PROCEDURE [dbo].[set_sala]
(
	@id int,
	@nombre nvarchar(40),
    @desc   nvarchar(140),
	@parts int

)
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

	IF EXISTS (SELECT * FROM dbo.Salas WHERE id = @id)
		UPDATE dbo.Salas SET nombre = @nombre, descripcion = @desc, participantes = @parts WHERE nombre = @nombre;
	ELSE 
		INSERT INTO dbo.Salas (id, nombre, descripcion, participantes) VALUES (@id, LTRIM(RTRIM(@nombre)), LTRIM(RTRIM(@desc)), @parts);
END
