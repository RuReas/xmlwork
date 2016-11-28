DROP TABLE IF EXISTS positions;

CREATE TABLE positions (
  id          SERIAL PRIMARY KEY ,
  depcode     VARCHAR(20),
  depjob      VARCHAR(100),
  description VARCHAR(255),
  CONSTRAINT codejob_idx UNIQUE (depcode, depjob)
);
