DROP DATABASE IF EXISTS `vfox`;
CREATE DATABASE `vfox`;

DROP TABLE IF EXISTS `vfox`.`UserRole`;
CREATE TABLE IF NOT EXISTS `vfox`.`UserRole` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `Role` VARCHAR(45) NOT NULL,
  `RoleState` ENUM('A', 'D', 'I') NOT NULL COMMENT 'A=Active, D= Deleted, I= Inactive',
  `CreatedBy` INT NOT NULL,
  `CreatedAt` DATETIME NOT NULL,
  `UpdatedBy` INT NOT NULL,
  `UpdatedAt` DATETIME NOT NULL,
  PRIMARY KEY (`Id`))
ENGINE = InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

DROP TABLE IF EXISTS `vfox`.`User`;
CREATE TABLE IF NOT EXISTS `vfox`.`User` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `UserName` VARCHAR(45) NOT NULL,
  `Password` VARCHAR(150) NOT NULL,
  `RoleId` INT NOT NULL,
  `PasswordSetDate` DATETIME NULL,
  `UserState` ENUM('A', 'D', 'I') NOT NULL COMMENT 'A=Active, I= Inactive, D= Deleted',
  `isFirstLogin` boolean NOT NULL default true,
  `CreatedBy` INT NOT NULL,
  `CreatedAt` DATETIME NOT NULL,
  `UpdatedBy` INT NOT NULL,
  `UpdatedAt` DATETIME NOT NULL,
  PRIMARY KEY (`Id`),
  INDEX `FK_USER_ROLE_idx` (`RoleId` ASC),
  CONSTRAINT `FK_USER_ROLE`
    FOREIGN KEY (`RoleId`)
    REFERENCES `vfox`.`UserRole` (`Id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

DROP TABLE IF EXISTS `vfox`.`AdvisorClient`;
 CREATE TABLE IF NOT EXISTS `vfox`.`AdvisorClient` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `AdvisorId` INT NOT NULL,
  `ClientId` INT NOT NULL,
  `CreatedBy` INT NOT NULL,
  `CreatedAt` DATETIME NOT NULL,
  `UpdatedBy` INT NOT NULL,
  `UpdatedAt` DATETIME NOT NULL,
  PRIMARY KEY (`Id`),
  INDEX `FK_ADVISORCLIENT_ADVISOR_idx` (`AdvisorId` ASC),
  INDEX `FK_ADVISORCLIENT_CLIENT_idx` (`ClientId` ASC),
  CONSTRAINT `FK_ADVISORCLIENT_ADVISOR`
    FOREIGN KEY (`AdvisorId`)
    REFERENCES `vfox`.`User` (`Id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `FK_ADVISORCLIENT_CLIENT`
    FOREIGN KEY (`ClientId`)
    REFERENCES `vfox`.`User` (`Id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = innodb DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

DROP TABLE IF EXISTS `vfox`.`Services`;
CREATE TABLE IF NOT EXISTS `vfox`.`Services` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(150) NOT NULL,
  `ShortDesc` VARCHAR(250) NULL,
  `LongDesc` VARCHAR(450) NULL,
  `ServiceState` ENUM('A', 'I', 'D') NOT NULL COMMENT 'A=Active, I=Inactive,D=Deleted',
  `CreatedBy` INT NOT NULL,
  `CreatedAt` DATETIME NOT NULL,
  `UpdatedBy` INT NOT NULL,
  `UpdatedAt` DATETIME NOT NULL,
  PRIMARY KEY (`Id`))
ENGINE = InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

DROP TABLE IF EXISTS `vfox`.`ServiceProvider`;
CREATE TABLE IF NOT EXISTS `vfox`.`ServiceProvider` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `ServiceId` INT NOT NULL,
  `Name` VARCHAR(150) NOT NULL,
  `ShortDesc` VARCHAR(250) NULL,
  `LongDesc` VARCHAR(450) NULL,
  `Logo` longblob NULL,
  `RedirectUrl` VARCHAR(450) NULL,
  `CreatedBy` INT NOT NULL,
  `CreatedAt` DATETIME NOT NULL,
  `UpdatedBy` INT NOT NULL,
  `UpdatedAt` DATETIME NOT NULL,
  PRIMARY KEY (`Id`),
    INDEX `FK_SERVICEPROVIDER_SERVICES_idx` (`ServiceId` ASC),
  CONSTRAINT `FK_SERVICEPROVIDER_SERVICES`
    FOREIGN KEY (`ServiceId`)
    REFERENCES `vfox`.`Services` (`Id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

DROP TABLE IF EXISTS `vfox`.`AdvisorServices`;
CREATE TABLE IF NOT EXISTS `vfox`.`AdvisorServices` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `UserId` INT NOT NULL,
  `ServiceId` INT NOT NULL,
  `AdvisorServicesState` ENUM('A', 'D', 'I') NOT NULL DEFAULT 'A' COMMENT 'A=Active, I= Inactive, D= Deleted',
  `CreatedBy` INT NOT NULL,
  `CreatedAt` DATETIME NOT NULL,
  `UpdatedBy` INT NOT NULL,
  `UpdatedAt` DATETIME NOT NULL,
  PRIMARY KEY (`Id`),
  INDEX `FK_ADVISORSERVICES_USER_idx` (`UserId` ASC),
  INDEX `FK_ADVISORSERVICES_SERVICES_idx` (`ServiceId` ASC),
  CONSTRAINT `FK_ADVISORSERVICES_USER`
    FOREIGN KEY (`UserId`)
    REFERENCES `vfox`.`User` (`Id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `FK_ADVISORSERVICES_SERVICES`
    FOREIGN KEY (`ServiceId`)
    REFERENCES `vfox`.`Services` (`Id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

DROP TABLE IF EXISTS `vfox`.`Billing`;
CREATE TABLE IF NOT EXISTS `vfox`.`Billing` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `ServiceId` INT NOT NULL,
  `ServiceProviderId` INT NOT NULL,
  `UserId` INT NULL,
  `CreatedBy` INT NOT NULL,
  `CreatedAt` DATETIME NOT NULL,
  `UpdatedBy` INT NOT NULL,
  `UpdatedAt` DATETIME NOT NULL,
  PRIMARY KEY (`Id`),
  INDEX `FK_BILLING_SERVICES_idx` (`ServiceId` ASC),
  INDEX `FK_BILLING_SERVICEPROVIDER_idx` (`ServiceProviderId` ASC),
  INDEX `FK_BILLING_USER_idx` (`UserId` ASC),
  CONSTRAINT `FK_BILLING_SERVICES`
    FOREIGN KEY (`ServiceId`)
    REFERENCES `vfox`.`Services` (`Id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `FK_BILLING_SERVICEPROVIDER`
    FOREIGN KEY (`ServiceProviderId`)
    REFERENCES `vfox`.`ServiceProvider` (`Id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `FK_BILLING_USER`
    FOREIGN KEY (`UserId`)
    REFERENCES `vfox`.`User` (`Id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

DROP TABLE IF EXISTS `vfox`.`Token`;
CREATE TABLE IF NOT EXISTS `vfox`.`Token` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `UserId` INT NOT NULL,
  `Expiry` TIMESTAMP NULL,
  `Token` BINARY(200) NULL,
  `TokenState` ENUM('A', 'I', 'D', 'U') NOT NULL COMMENT 'A=Active, I= Inactive, D= Deleted, U= Used',
  `CreatedBy` VARCHAR(45) NOT NULL DEFAULT 'System',
  `CreatedAt` DATETIME NOT NULL,
  PRIMARY KEY (`Id`),
  INDEX `FK_TOKEN_USER_idx` (`UserId` ASC),
  CONSTRAINT `FK_TOKEN_USER`
    FOREIGN KEY (`UserId`)
    REFERENCES `vfox`.`User` (`Id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

INSERT INTO vfox.UserRole (Id,`Role`,RoleState,CreatedBy,CreatedAt,UpdatedBy,UpdatedAt) VALUES (
1,'admin','A',1,now(),1,now());
INSERT INTO vfox.UserRole (Id,`Role`,RoleState,CreatedBy,CreatedAt,UpdatedBy,UpdatedAt) VALUES (
2,'advisor','A',1,now(),1,now());
INSERT INTO vfox.UserRole (Id,`Role`,RoleState,CreatedBy,CreatedAt,UpdatedBy,UpdatedAt) VALUES (
3,'client','A',1,now(),1,now());

INSERT INTO vfox.`User` (Id,UserName,Password,RoleId,PasswordSetDate,UserState,CreatedBy,CreatedAt,UpdatedBy,UpdatedAt,IsFirstLogin) VALUES (
1,'admin@vfox.com','$10$ZHNiei36NQLDou3DUrxoN.ODhnTzmnCRh2zkbM755QyFyLSilZPTi',1,now(),'A',1,now(),1,now(),1);

INSERT INTO vfox.Services (Id,Name,ShortDesc,LongDesc,ServiceState,CreatedBy,CreatedAt,UpdatedBy,UpdatedAt) VALUES (
1,'Bill Pay','Bill Pay','Bill Pay','A',1,now(),1,now());
INSERT INTO vfox.Services (Id,Name,ShortDesc,LongDesc,ServiceState,CreatedBy,CreatedAt,UpdatedBy,UpdatedAt) VALUES (
2,'Alternate Investment','Alternate Investment','Alternate Investment','A',1,now(),1,now());
INSERT INTO vfox.Services (Id,Name,ShortDesc,LongDesc,ServiceState,CreatedBy,CreatedAt,UpdatedBy,UpdatedAt) VALUES (
3,'Wealth Management','Wealth Management','Wealth Management','A',1,now(),1,now());
INSERT INTO vfox.Services (Id,Name,ShortDesc,LongDesc,ServiceState,CreatedBy,CreatedAt,UpdatedBy,UpdatedAt) VALUES (
4,'Insurance','Insurance','Insurance','A',1,now(),1,now());
INSERT INTO vfox.Services (Id,Name,ShortDesc,LongDesc,ServiceState,CreatedBy,CreatedAt,UpdatedBy,UpdatedAt) VALUES (
5,'Tax Mitigation','Tax Mitigation','Tax Mitigation','A',1,now(),1,now());
INSERT INTO vfox.Services (Id,Name,ShortDesc,LongDesc,ServiceState,CreatedBy,CreatedAt,UpdatedBy,UpdatedAt) VALUES (
6,'Asset Protection','Asset Protection','Asset Protection','A',1,now(),1,now());
INSERT INTO vfox.Services (Id,Name,ShortDesc,LongDesc,ServiceState,CreatedBy,CreatedAt,UpdatedBy,UpdatedAt) VALUES (
7,'Trust Services','Trust Services','Trust Services','A',1,now(),1,now());
INSERT INTO vfox.Services (Id,Name,ShortDesc,LongDesc,ServiceState,CreatedBy,CreatedAt,UpdatedBy,UpdatedAt) VALUES (
8,'Business Valuation','Business Valuation','Business Valuation','A',1,now(),1,now());
INSERT INTO vfox.Services (Id,Name,ShortDesc,LongDesc,ServiceState,CreatedBy,CreatedAt,UpdatedBy,UpdatedAt) VALUES (
9,'Cost Remediation','Cost Remediation','Cost Remediation','A',1,now(),1,now());
INSERT INTO vfox.Services (Id,Name,ShortDesc,LongDesc,ServiceState,CreatedBy,CreatedAt,UpdatedBy,UpdatedAt) VALUES (
10,'Business Transition','Business Transition','Business Transition','A',1,now(),1,now());