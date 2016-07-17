-- ================================================
-- Template generated from Template Explorer using:
-- Create Procedure (New Menu).SQL
--
-- Use the Specify Values for Template Parameters 
-- command (Ctrl-Shift-M) to fill in the parameter 
-- values below.
--
-- This block of comments will not be included in
-- the definition of the procedure.
-- ================================================
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		Javier
-- Create date: 
-- Description:	
-- =============================================
USE finaldas
GO
ALTER PROCEDURE [dbo].get_salas
	-- Add the parameters for the stored procedure here
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	SELECT id_sala, LTRIM(RTRIM(nombre_sala)) as nombre_sala, LTRIM(RTRIM(desc_sala)) as desc_sala, tipo_sala  FROM [dbo].Sala
END
GO
