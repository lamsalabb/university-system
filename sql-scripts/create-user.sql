DROP USER if exists 'universitydba'@'%' ;

CREATE USER 'universitydba'@'%' IDENTIFIED BY 'universitydba';

GRANT ALL PRIVILEGES ON * . * TO 'universitydba'@'%';
