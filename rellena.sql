CREATE DEFINER=`root`@`localhost` PROCEDURE `prepare_data`()
BEGIN
  DECLARE i INT DEFAULT 100;
  declare nom varchar( 100);
  declare ape varchar(100);
  WHILE i < 1000 DO
	set nom := 'nombre';
    set ape :='apellido';
    set nom:=concat(nom,i);
    set ape:=concat(ape,i);
    INSERT INTO `sistemamonitoreo`.`aspiranteresponsable` (`Nombre`, `Apellido`, `Calle`, `Numero`, `Colonia`, `Agencia`, `Seccion`, `Telefono`, `Email`, `Barda`, `Lona`, `Banderin`, `Reunion`, `Gestion`, `PSocial`, `PInfraestructura`) VALUES (nom, ape, concat('casa',i), '201', 'cerros', 'pelones', '100', '213123132', 'asdasd@adsad.com', '0', '0', '1', '0', '0', '100', '100');
    SET i = i + 1;
  END WHILE;
END