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
ALTER PROCEDURE [dbo].[insert_sala]
(
	@nombre nvarchar(60),
    @desc   nvarchar(256),
	@tipo varchar(60), 
	@color int
)
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

	INSERT INTO dbo.Sala (nombre_sala, desc_sala, tipo_sala, color_sala) VALUES (LTRIM(RTRIM(@nombre)), LTRIM(RTRIM(@desc)), @tipo, @color);
END