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
CREATE PROCEDURE [dbo].[delete_usuario]
(
	@id nvarchar(60)
)
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

	DELETE FROM [dbo].[Usuario] WHERE id_usuario = @id

END
