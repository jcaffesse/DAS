USE [finaldas]
GO
/****** Object:  StoredProcedure [dbo].[insert_token]    Script Date: 04/02/2016 05:42:59 p.m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[insert_token]
(
	@id_usuario int,
	@token varchar(256)
)
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

	INSERT INTO [dbo].Token (id_usuario, token) VALUES (@id_usuario, @token);
END
