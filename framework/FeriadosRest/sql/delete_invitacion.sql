USE [finaldas]
GO
/****** Object:  StoredProcedure [dbo].[delete_invitacion]    Script Date: 02/02/2016 06:27:00 p.m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[delete_invitacion]
(
	@id_usuario int,
	@id_destino int
)
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

	DELETE FROM [dbo].[Invitacion] WHERE id_usuario = @id_usuario AND id_destino = @id_destino

END
