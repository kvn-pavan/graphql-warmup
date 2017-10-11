-- Every application start will execute this script.
-- Place the schema and test data inside.

DROP TABLE IF EXISTS `inventory_item` ;
CREATE TABLE IF NOT EXISTS `inventory_item` (
  `id` VARCHAR(36) NOT NULL,
  `type` VARCHAR(10) NOT NULL,
  `tag_line` VARCHAR(140) NULL,
  `description` VARCHAR(400) NULL,
  `year` INT NULL,
  `price` DECIMAL(15,2) NULL,
  `currency_code` VARCHAR(3) NULL,
  `vehicle_make` VARCHAR(45) NULL,
  `vehicle_model` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
  ENGINE = InnoDB;

-- Add some test data.
INSERT INTO inventory_item (id, type, tag_line, description, year, price, currency_code, vehicle_make, vehicle_model) VALUES ('1', 'VEHICLE', 'Tag Line 1', 'Initial test entry.', 2017, 4321, 'CAD', 'Honda', 'Civic');


ALTER TABLE `inventory_item` ADD `vehicle_cylinders` INT(2) AFTER `vehicle_model`;
ALTER TABLE `inventory_item` ADD `vehicle_displacement` DOUBLE  AFTER `vehicle_cylinders`;
ALTER TABLE `inventory_item` ADD `vehicle_fuelType` VARCHAR(20)  AFTER `vehicle_displacement`;
ALTER TABLE `inventory_item` ADD `vehicle_odometer_mileage` INT(3) AFTER `vehicle_fuelType`;
ALTER TABLE `inventory_item` ADD `vehicle_odometer_unit` VARCHAR(2)  AFTER `vehicle_odometer_mileage`;

UPDATE `inventory_item` SET vehicle_cylinders = 4, vehicle_displacement = 2.5, vehicle_fuelType = 'gasoline', vehicle_odometer_mileage = 50, vehicle_odometer_unit = 'mi' where id = "1";

ALTER TABLE `inventory_item` ADD `boat_motorMount` VARCHAR(20) NOT NULL AFTER `vehicle_odometer_unit`;

INSERT INTO inventory_item (id, type, tag_line, description, year, price, currency_code, boat_motorMount) VALUES ('2', 'BOAT', 'Tag Line 2', 'Second test entry.', 2017, 5123, 'CAD', 'inboard');
