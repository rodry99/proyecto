/* ---------------------------------------------------- */
/*  Generated by Enterprise Architect Version 13.0 		*/
/*  Created On : 16-nov.-2018 23:35:56 				*/
/*  DBMS       : SQL Server 2005 						*/
/* ---------------------------------------------------- */

/* Drop Foreign Key Constraints */

IF EXISTS (SELECT 1 FROM dbo.sysobjects WHERE id = object_id(N'[FK_ASISTENCIA_CLASE]') AND OBJECTPROPERTY(id, N'IsForeignKey') = 1) 
ALTER TABLE [ASISTENCIA] DROP CONSTRAINT [FK_ASISTENCIA_CLASE]
GO

IF EXISTS (SELECT 1 FROM dbo.sysobjects WHERE id = object_id(N'[FK_ASISTENCIA_CURSO_ALUMNOS]') AND OBJECTPROPERTY(id, N'IsForeignKey') = 1) 
ALTER TABLE [ASISTENCIA] DROP CONSTRAINT [FK_ASISTENCIA_CURSO_ALUMNOS]
GO

IF EXISTS (SELECT 1 FROM dbo.sysobjects WHERE id = object_id(N'[FK_CARRERA_MATERIA_CARRERA]') AND OBJECTPROPERTY(id, N'IsForeignKey') = 1) 
ALTER TABLE [CARRERA_MATERIAS] DROP CONSTRAINT [FK_CARRERA_MATERIA_CARRERA]
GO

IF EXISTS (SELECT 1 FROM dbo.sysobjects WHERE id = object_id(N'[FK_CARRERA_MATERIA_MATERIA]') AND OBJECTPROPERTY(id, N'IsForeignKey') = 1) 
ALTER TABLE [CARRERA_MATERIAS] DROP CONSTRAINT [FK_CARRERA_MATERIA_MATERIA]
GO

IF EXISTS (SELECT 1 FROM dbo.sysobjects WHERE id = object_id(N'[FK_CLASE_CURSO]') AND OBJECTPROPERTY(id, N'IsForeignKey') = 1) 
ALTER TABLE [CLASE] DROP CONSTRAINT [FK_CLASE_CURSO]
GO

IF EXISTS (SELECT 1 FROM dbo.sysobjects WHERE id = object_id(N'[FK_CURSO_CARRERA_MATERIA]') AND OBJECTPROPERTY(id, N'IsForeignKey') = 1) 
ALTER TABLE [CURSO] DROP CONSTRAINT [FK_CURSO_CARRERA_MATERIA]
GO

IF EXISTS (SELECT 1 FROM dbo.sysobjects WHERE id = object_id(N'[FK_CURSO_PROFESOR]') AND OBJECTPROPERTY(id, N'IsForeignKey') = 1) 
ALTER TABLE [CURSO] DROP CONSTRAINT [FK_CURSO_PROFESOR]
GO

IF EXISTS (SELECT 1 FROM dbo.sysobjects WHERE id = object_id(N'[FK_CURSO_ALUMNOS_ALUMNO]') AND OBJECTPROPERTY(id, N'IsForeignKey') = 1) 
ALTER TABLE [CURSO_ALUMNOS] DROP CONSTRAINT [FK_CURSO_ALUMNOS_ALUMNO]
GO

IF EXISTS (SELECT 1 FROM dbo.sysobjects WHERE id = object_id(N'[FK_CURSO_ALUMNOS_CURSO]') AND OBJECTPROPERTY(id, N'IsForeignKey') = 1) 
ALTER TABLE [CURSO_ALUMNOS] DROP CONSTRAINT [FK_CURSO_ALUMNOS_CURSO]
GO

IF EXISTS (SELECT 1 FROM dbo.sysobjects WHERE id = object_id(N'[FK_CURSO_DIA_CURSO]') AND OBJECTPROPERTY(id, N'IsForeignKey') = 1) 
ALTER TABLE [CURSO_DIAS] DROP CONSTRAINT [FK_CURSO_DIA_CURSO]
GO

/* Drop Tables */

IF EXISTS (SELECT 1 FROM dbo.sysobjects WHERE id = object_id(N'[ALUMNO]') AND OBJECTPROPERTY(id, N'IsUserTable') = 1) 
DROP TABLE [ALUMNO]
GO

IF EXISTS (SELECT 1 FROM dbo.sysobjects WHERE id = object_id(N'[ASISTENCIA]') AND OBJECTPROPERTY(id, N'IsUserTable') = 1) 
DROP TABLE [ASISTENCIA]
GO

IF EXISTS (SELECT 1 FROM dbo.sysobjects WHERE id = object_id(N'[CARRERA]') AND OBJECTPROPERTY(id, N'IsUserTable') = 1) 
DROP TABLE [CARRERA]
GO

IF EXISTS (SELECT 1 FROM dbo.sysobjects WHERE id = object_id(N'[CARRERA_MATERIAS]') AND OBJECTPROPERTY(id, N'IsUserTable') = 1) 
DROP TABLE [CARRERA_MATERIAS]
GO

IF EXISTS (SELECT 1 FROM dbo.sysobjects WHERE id = object_id(N'[CLASE]') AND OBJECTPROPERTY(id, N'IsUserTable') = 1) 
DROP TABLE [CLASE]
GO

IF EXISTS (SELECT 1 FROM dbo.sysobjects WHERE id = object_id(N'[CURSO]') AND OBJECTPROPERTY(id, N'IsUserTable') = 1) 
DROP TABLE [CURSO]
GO

IF EXISTS (SELECT 1 FROM dbo.sysobjects WHERE id = object_id(N'[CURSO_ALUMNOS]') AND OBJECTPROPERTY(id, N'IsUserTable') = 1) 
DROP TABLE [CURSO_ALUMNOS]
GO

IF EXISTS (SELECT 1 FROM dbo.sysobjects WHERE id = object_id(N'[CURSO_DIAS]') AND OBJECTPROPERTY(id, N'IsUserTable') = 1) 
DROP TABLE [CURSO_DIAS]
GO

IF EXISTS (SELECT 1 FROM dbo.sysobjects WHERE id = object_id(N'[LOGIN]') AND OBJECTPROPERTY(id, N'IsUserTable') = 1) 
DROP TABLE [LOGIN]
GO

IF EXISTS (SELECT 1 FROM dbo.sysobjects WHERE id = object_id(N'[MATERIA]') AND OBJECTPROPERTY(id, N'IsUserTable') = 1) 
DROP TABLE [MATERIA]
GO

IF EXISTS (SELECT 1 FROM dbo.sysobjects WHERE id = object_id(N'[PROFESOR]') AND OBJECTPROPERTY(id, N'IsUserTable') = 1) 
DROP TABLE [PROFESOR]
GO

/* Create Tables */

CREATE TABLE [ALUMNO]
(
	[ID_ALUMNO] int NOT NULL,
	[APELLIDO] varchar(50) NOT NULL,
	[NOMBRE] varchar(50) NOT NULL,
	[DNI] int NOT NULL,
	[GENERO] varchar(1) NULL,
	[FECHA_DE_NACIMIENTO] datetime NULL,
	[EMAIL] varchar(100) NULL,
	[ESTADO] bit NULL
)
GO

CREATE TABLE [ASISTENCIA]
(
	[ID_ASISTENCIA] int NOT NULL,
	[ID_CURSO_ALUMNO] int NOT NULL,
	[ID_CLASE] int NOT NULL,
	[ASISTENCIA] bit NOT NULL
)
GO

CREATE TABLE [CARRERA]
(
	[ID_CARRERA] int NOT NULL,
	[DESCRIPCION] varchar(50) NULL
)
GO

CREATE TABLE [CARRERA_MATERIAS]
(
	[ID_CARRERA_MATERIA] int NOT NULL,
	[ID_CARRERA] int NOT NULL,
	[ID_MATERIA] int NOT NULL,
	[ANIO_CARRERA] int NOT NULL
)
GO

CREATE TABLE [CLASE]
(
	[ID_CLASE] int NOT NULL,
	[ID_CURSO] int NOT NULL,
	[FECHA] datetime NOT NULL,
	[TEMA] text NULL,
	[ESTADO] varchar(3) NOT NULL
)
GO

CREATE TABLE [CURSO]
(
	[ID_CURSO] int NOT NULL,
	[ID_CARRERA_MATERIA] int NULL,
	[ANIO_LECTIVO] int NOT NULL,
	[CUATRIMESTRE] varchar(1) NOT NULL,
	[COMISION] varchar(3) NOT NULL,
	[ID_PROFESOR] int NULL,
	[ESTADO] varchar(1) NOT NULL,
	[CANTIDAD_INSCRIPTOS] int NULL,
	[DESDE] datetime NOT NULL,
	[HASTA] datetime NOT NULL
)
GO

CREATE TABLE [CURSO_ALUMNOS]
(
	[ID_CURSO_ALUMNO] int NOT NULL,
	[ID_CURSO] int NOT NULL,
	[ID_ALUMNO] int NOT NULL,
	[ESTADO] varchar(1) NULL
)
GO

CREATE TABLE [CURSO_DIAS]
(
	[ID_CURSO_DIA] int NOT NULL,
	[ID_CURSO] int NULL,
	[DIA] int NULL
)
GO

CREATE TABLE [LOGIN]
(
	[ID_LOGIN] int NOT NULL IDENTITY (1, 1),
	[USERNAME] varchar(40) NOT NULL,
	[PASSWORD] varchar(32) NOT NULL
)
GO

CREATE TABLE [MATERIA]
(
	[ID_MATERIA] int NOT NULL,
	[DESCRIPCION] varchar(50) NULL
)
GO

CREATE TABLE [PROFESOR]
(
	[ID_PROFESOR] int NOT NULL,
	[APELLIDO] nvarchar(50) NOT NULL,
	[NOMBRE] varchar(50) NOT NULL,
	[DNI] int NOT NULL,
	[GENERO] varchar(1) NULL,
	[FECHA_DE_NACIMIENTO] datetime NULL,
	[EMAIL] varchar(100) NULL,
	[ESTADO] bit NULL
)
GO

/* Create Primary Keys, Indexes, Uniques, Checks */

ALTER TABLE [ALUMNO] 
 ADD CONSTRAINT [PK_ALUMNO]
	PRIMARY KEY CLUSTERED ([ID_ALUMNO] ASC)
GO

ALTER TABLE [ALUMNO] 
 ADD CONSTRAINT [UQ_ALUMNO_DNI] UNIQUE NONCLUSTERED ([DNI] ASC)
GO

ALTER TABLE [ALUMNO] 
 ADD CONSTRAINT [CK_ALUMNO_GENERO] CHECK (GENERO IN ('M','F'))
GO

ALTER TABLE [ASISTENCIA] 
 ADD CONSTRAINT [PK_ASISTENCIA]
	PRIMARY KEY CLUSTERED ([ID_ASISTENCIA] ASC)
GO

ALTER TABLE [ASISTENCIA] 
 ADD CONSTRAINT [UQ_ASISTENCIA] UNIQUE NONCLUSTERED ([ID_CURSO_ALUMNO] ASC,[ID_CLASE] ASC)
GO

ALTER TABLE [CARRERA] 
 ADD CONSTRAINT [PK_CARRERA]
	PRIMARY KEY CLUSTERED ([ID_CARRERA] ASC)
GO

ALTER TABLE [CARRERA_MATERIAS] 
 ADD CONSTRAINT [PK_CARRERA_MATERIA]
	PRIMARY KEY CLUSTERED ([ID_CARRERA_MATERIA] ASC)
GO

ALTER TABLE [CARRERA_MATERIAS] 
 ADD CONSTRAINT [UQ_CARRERA_MATERIA] UNIQUE NONCLUSTERED ([ID_CARRERA] ASC,[ID_MATERIA] ASC,[ANIO_CARRERA] ASC)
GO

ALTER TABLE [CLASE] 
 ADD CONSTRAINT [PK_CLASE]
	PRIMARY KEY CLUSTERED ([ID_CLASE] ASC)
GO

ALTER TABLE [CURSO] 
 ADD CONSTRAINT [PK_CURSO]
	PRIMARY KEY CLUSTERED ([ID_CURSO] ASC)
GO

ALTER TABLE [CURSO] 
 ADD CONSTRAINT [UQ_CURSO] UNIQUE NONCLUSTERED ([ID_CARRERA_MATERIA] ASC,[ANIO_LECTIVO] ASC,[CUATRIMESTRE] ASC,[COMISION] ASC)
GO

ALTER TABLE [CURSO] 
 ADD CONSTRAINT [CK_CURSO_CUATRIMESTRE] CHECK (CUATRIMESTRE IN ('P','S','A'))
GO

ALTER TABLE [CURSO] 
 ADD CONSTRAINT [CK_CURSO_ESTADO] CHECK (ESTADO IN ('A','E','F'))
GO

ALTER TABLE [CURSO_ALUMNOS] 
 ADD CONSTRAINT [PK_CURSO_ALUMNO]
	PRIMARY KEY CLUSTERED ([ID_CURSO_ALUMNO] ASC)
GO

ALTER TABLE [CURSO_ALUMNOS] 
 ADD CONSTRAINT [UQ_CURSO_ALUMNOS] UNIQUE NONCLUSTERED ([ID_CURSO] ASC,[ID_ALUMNO] ASC)
GO

ALTER TABLE [CURSO_ALUMNOS] 
 ADD CONSTRAINT [CK_CURSO_ALUMNOS_ESTADO] CHECK (ESTADO IN ('L','R'))
GO

ALTER TABLE [CURSO_DIAS] 
 ADD CONSTRAINT [PK_CURSO_DIA]
	PRIMARY KEY CLUSTERED ([ID_CURSO_DIA] ASC)
GO

ALTER TABLE [CURSO_DIAS] 
 ADD CONSTRAINT [CK_CURSO_DIA] CHECK (DIA IN (0,1,2,3,4,5))
GO

ALTER TABLE [LOGIN] 
 ADD CONSTRAINT [PK_LOGIN]
	PRIMARY KEY CLUSTERED ([ID_LOGIN] ASC)
GO

ALTER TABLE [LOGIN] 
 ADD CONSTRAINT [UQ_LOGIN] UNIQUE NONCLUSTERED ([USERNAME] ASC)
GO

ALTER TABLE [MATERIA] 
 ADD CONSTRAINT [PK_MATERIA]
	PRIMARY KEY CLUSTERED ([ID_MATERIA] ASC)
GO

ALTER TABLE [PROFESOR] 
 ADD CONSTRAINT [PK_PROFESOR]
	PRIMARY KEY CLUSTERED ([ID_PROFESOR] ASC)
GO

ALTER TABLE [PROFESOR] 
 ADD CONSTRAINT [UQ_PROFESOR_DNI] UNIQUE NONCLUSTERED ([DNI] ASC)
GO

ALTER TABLE [PROFESOR] 
 ADD CONSTRAINT [CK_PROFESOR_GENERO] CHECK (GENERO IN ('M','F'))
GO

/* Create Foreign Key Constraints */

ALTER TABLE [ASISTENCIA] ADD CONSTRAINT [FK_ASISTENCIA_CLASE]
	FOREIGN KEY ([ID_CLASE]) REFERENCES [CLASE] ([ID_CLASE]) ON DELETE No Action ON UPDATE No Action
GO

ALTER TABLE [ASISTENCIA] ADD CONSTRAINT [FK_ASISTENCIA_CURSO_ALUMNOS]
	FOREIGN KEY ([ID_CURSO_ALUMNO]) REFERENCES [CURSO_ALUMNOS] ([ID_CURSO_ALUMNO]) ON DELETE No Action ON UPDATE No Action
GO

ALTER TABLE [CARRERA_MATERIAS] ADD CONSTRAINT [FK_CARRERA_MATERIA_CARRERA]
	FOREIGN KEY ([ID_CARRERA]) REFERENCES [CARRERA] ([ID_CARRERA]) ON DELETE No Action ON UPDATE No Action
GO

ALTER TABLE [CARRERA_MATERIAS] ADD CONSTRAINT [FK_CARRERA_MATERIA_MATERIA]
	FOREIGN KEY ([ID_MATERIA]) REFERENCES [MATERIA] ([ID_MATERIA]) ON DELETE No Action ON UPDATE No Action
GO

ALTER TABLE [CLASE] ADD CONSTRAINT [FK_CLASE_CURSO]
	FOREIGN KEY ([ID_CURSO]) REFERENCES [CURSO] ([ID_CURSO]) ON DELETE No Action ON UPDATE No Action
GO

ALTER TABLE [CURSO] ADD CONSTRAINT [FK_CURSO_CARRERA_MATERIA]
	FOREIGN KEY ([ID_CARRERA_MATERIA]) REFERENCES [CARRERA_MATERIAS] ([ID_CARRERA_MATERIA]) ON DELETE No Action ON UPDATE No Action
GO

ALTER TABLE [CURSO] ADD CONSTRAINT [FK_CURSO_PROFESOR]
	FOREIGN KEY ([ID_PROFESOR]) REFERENCES [PROFESOR] ([ID_PROFESOR]) ON DELETE No Action ON UPDATE No Action
GO

ALTER TABLE [CURSO_ALUMNOS] ADD CONSTRAINT [FK_CURSO_ALUMNOS_ALUMNO]
	FOREIGN KEY ([ID_ALUMNO]) REFERENCES [ALUMNO] ([ID_ALUMNO]) ON DELETE No Action ON UPDATE No Action
GO

ALTER TABLE [CURSO_ALUMNOS] ADD CONSTRAINT [FK_CURSO_ALUMNOS_CURSO]
	FOREIGN KEY ([ID_CURSO]) REFERENCES [CURSO] ([ID_CURSO]) ON DELETE No Action ON UPDATE No Action
GO

ALTER TABLE [CURSO_DIAS] ADD CONSTRAINT [FK_CURSO_DIA_CURSO]
	FOREIGN KEY ([ID_CURSO]) REFERENCES [CURSO] ([ID_CURSO]) ON DELETE No Action ON UPDATE No Action
GO
