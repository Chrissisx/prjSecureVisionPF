--database: ../DataBase/Secure.sqlite

-- DROP TABLE IF EXISTS Persona;
-- DROP TABLE IF EXISTS Registro;
-- DROP TABLE IF EXISTS Grabaciones;

CREATE TABLE Registro (
        IdRegistro      INTEGER     NOT NULL PRIMARY KEY AUTOINCREMENT     
        ,Username       VARCHAR(10) NOT NULL UNIQUE
        ,Password       VARCHAR(30)
        ,Estado         VARCHAR(1)  NOT NULL DEFAULT('A')
        ,FechaCreacion  DATETIME    DEFAULT(datetime('now','locatime'))
        ,FechaMod       DATETIME
);

CREATE TABLE Persona (
        IdPersona       INTEGER     primary key AUTOINCREMENT
        ,IdRegistro     INTEGER     NOT NULL REFERENCES Registro(IdRegistro)
        ,IdGrabaciones    INTEGER     NOT NULL REFERENCES Grabaciones(IdGrabaciones)
        ,Nombre         VARCHAR(50) NOT NULL
        ,Apellido       VARCHAR(50) NOT NULL
        ,Estado         VARCHAR(1)  NOT NULL DEFAULT('A')
        ,FechaCreacion            DATETIME    DEFAULT(datetime('now','localtime'))
        ,FechaModifica            DATETIME
);

CREATE TABLE Grabaciones (
         IdGrabaciones    INTEGER     primary key AUTOINCREMENT 
        ,Grabacion        VARCHAR(50) NOT NULL
        ,Estado           VARCHAR(1)  NOT NULL DEFAULT('A')
        ,FechaCreacion    DATETIME    DEFAULT(datetime('now','localtime'))
);