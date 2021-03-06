USE [finaldas]
GO
/****** Object:  StoredProcedure [dbo].[set_sala]    Script Date: 02/02/2016 07:17:43 p.m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[update_sala]
(
	@id int,
	@nombre nvarchar(40),
    @desc   nvarchar(140),
	@tipo nvarchar(40)

)
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

	IF EXISTS (SELECT * FROM dbo.Sala WHERE id_sala = @id)
		UPDATE 
			dbo.Sala 
		SET 
			nombre_sala = isNull(@nombre, nombre_sala),
			desc_sala = isNull(@desc, desc_sala),
			tipo_sala = isNull(@tipo, tipo_sala) 
		WHERE 
			id_sala = @id;
	ELSE 
		INSERT INTO dbo.Sala (id_sala, nombre_sala, desc_sala, tipo_sala) 
		VALUES (@id, LTRIM(RTRIM(@nombre)), LTRIM(RTRIM(@desc)), LTRIM(RTRIM(@tipo)));
END
