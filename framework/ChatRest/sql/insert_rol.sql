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
CREATE PROCEDURE [dbo].[insert_rol]
(
	@tipo_rol varchar(60)
)
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

	INSERT INTO dbo.Rol (tipo_rol) VALUES (LTRIM(RTRIM(@tipo_rol)));
END
