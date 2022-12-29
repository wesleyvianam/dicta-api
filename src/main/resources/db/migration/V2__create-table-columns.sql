create table columns (

    id bigint NOT NULL AUTO_INCREMENT,
    name varchar(100) NOT NULL,
    project_id bigint,
    FOREIGN KEY (project_id) REFERENCES projects(id),

    PRIMARY KEY(id)

);