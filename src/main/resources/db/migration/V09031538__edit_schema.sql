ALTER TABLE employee CHANGE id employee_id BIGINT NOT NULL AUTO_INCREMENT;
ALTER TABLE employee CHANGE name employee_name VARCHAR(255);
PRIMARY KEY (employee_id)