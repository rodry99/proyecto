/* Esta funcion devuelve la cantidad de cursos en los que se encuentra incripto un determinado alumno*/
/* se utiliza para que el programa no borre un alumno perteneciente a un curso*/

CREATE FUNCTION [dbo].[FN_ALUMNO_CURSO](@ID_ALUMNO integer)

RETURNS integer BEGIN
DECLARE @resultado integer;
SELECT @resultado = COUNT(*) FROM CURSO_ALUMNOS WHERE ID_ALUMNO = @ID_ALUMNO
RETURN @resultado

END

