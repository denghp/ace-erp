/*** custom function getChildList **/
DROP FUNCTION IF EXISTS `ace`.`getChildList`;
DELIMITER ||
CREATE FUNCTION `ace`.`getChildList`(rootId INT) 
     RETURNS VARCHAR(1000)  
BEGIN 
       DECLARE pTemp VARCHAR(1000);  
       DECLARE cTemp VARCHAR(1000);  
       SET pTemp = '$';  
       SET cTemp =cast(rootId as CHAR);  
       WHILE cTemp is not null DO  
         SET pTemp = concat(pTemp,',',cTemp);
         SELECT group_concat(id) INTO cTemp FROM sys_resource   
         WHERE FIND_IN_SET(parent_id,cTemp)>0; 
       END WHILE;  
       RETURN pTemp;  
END ||
DELIMITER ;

