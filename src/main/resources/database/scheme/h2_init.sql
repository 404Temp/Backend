DROP TABLE IF EXISTS BOARD;
DROP SEQUENCE IF EXISTS BOARD_SEQ;

CREATE SEQUENCE BOARD_SEQ;
CREATE TABLE BOARD
(
  BOARD_ID BIGINT DEFAULT BOARD_SEQ.nextval PRIMARY KEY NOT NULL,
  TITLE    VARCHAR(100)
);

DROP TABLE IF EXISTS CATEGORY;
DROP SEQUENCE IF EXISTS CATEGORY_SEQ;

CREATE SEQUENCE CATEGORY_SEQ;
CREATE TABLE CATEGORY
(
  category_id BIGINT DEFAULT CATEGORY_SEQ.nextval PRIMARY KEY NOT NULL,
  NAME VARCHAR (100)
);