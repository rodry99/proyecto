/* Este trigger se dispara al asignarle un determinado dia de clase a un curso*/
/*genera automaticamente las clases para ese curso, para que el docente no tenga que crearla una por una*/


CREATE TRIGGER dbo.TR_CURSO_DIA ON CURSO_DIAS
AFTER INSERT
AS BEGIN
declare @id_curso int
declare @desde datetime
declare @hasta datetime
declare @max_id int
select @id_curso = id_curso from inserted
select @desde= desde FROM CURSO WHERE ID_CURSO=@id_curso
select @hasta= hasta from CURSO WHERE ID_CURSO=@id_curso

while (@desde<=@hasta) begin


	IF EXISTS(SELECT * FROM CURSO_DIAS WHERE ID_CURSO=@id_curso AND DIA IN (DATEPART(weekday,@desde)))
                BEGIN
		select @max_id = ISNULL(MAX(id_clase),0)+1 from CLASE
                IF NOT EXISTS(SELECT * FROM CLASE WHERE ID_CURSO=@id_curso AND FECHA=@desde)
		       BEGIN
                       INSERT INTO CLASE(ID_CLASE,ID_CURSO,FECHA,TEMA,ESTADO) VALUES
		       (@max_id,@id_curso,@desde,'POR SISTEMA','a')
		       END
		END
	SET @desde = DATEADD(day,1,@desde)
	END
END
