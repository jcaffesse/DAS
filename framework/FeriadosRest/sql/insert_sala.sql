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
CREATE PROCEDURE [dbo].[insert_sala]
(
	@nombre nvarchar(60),
    @desc   nvarchar(256),
	@tipo varchar(60)

)
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

	INSERT INTO dbo.Sala (nombre_sala, desc_sala, tipo_sala) VALUES (LTRIM(RTRIM(@nombre)), LTRIM(RTRIM(@desc)), @tipo);
END
