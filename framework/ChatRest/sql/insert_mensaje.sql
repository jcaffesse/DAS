USE [finaldas]
GO
/****** Object:  StoredProcedure [dbo].[insert_usuario_sala]    Script Date: 01/02/2016 05:59:49 p.m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[insert_mensaje]
(
	@id_usuario int,
	@id_sala int,
	@mensaje varchar(540)
)
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

	INSERT INTO dbo.Mensaje(texto_mensaje, id_sala, id_usuario)
		VALUES (@mensaje, @id_sala, @id_usuario);
END
