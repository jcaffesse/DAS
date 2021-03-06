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
CREATE PROCEDURE [dbo].[insert_invitacion]
(
	@id_usuario int,
	@id_destino int,
	@mensaje varchar(540)
)
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

	INSERT INTO dbo.Invitacion(id_usuario, id_destino, mensaje_invitacion)
		VALUES (@id_usuario, @id_destino, @mensaje);
END
