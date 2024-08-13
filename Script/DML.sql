--database: ../DataBase/Secure.sqlite
INSERT INTO Persona  
( IdPersona ,IdRegistro ,IdGrabaciones, Nombre ,Apellido) VALUES
( 1  , 1         , 1                      , "ZZ"  ,"Bedon" );

INSERT INTO Registro
 ( IdRegistro  ,Username  ,Password) VALUES
 ( 1  , "joan"        , "123"  ),
 ( 2,   "Jairo"        , "127"  );;

INSERT INTO Grabaciones
 ( IdGrabaciones  ,Grabacion ) VALUES
 ( 1  , "primera"       );

SELECT Nombre, Grabacion FROM Persona INNER JOIN Grabaciones
   ON Grabaciones.IdGrabaciones = Persona.IdGrabaciones;


   INSERT INTO Registro
 ( IdRegistro  ,Username  ,Password) VALUES
 ( 2,   "Jairo"        , "127"  );