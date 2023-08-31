-- CREATE DATABASE IF NOT EXISTS 'pam' USE 'pam';

DROP TABLE IF EXISTS 'cognos_csem';

CREATE TABLE 'cognos_csem'
( 'ticket' int
    (
    10
    ) unsigned NOT NULL AUTO_INCREMENT,
    'status' varchar
    (
    10
    ) COLLATE utf8mb4_bin NOT NULL,
    'subject' varchar
    (
    1000
    ) COLLATE utf8mb4_bin NOT NULL,
    'cusCare' tinyint
    (
    4
    ) DEFAULT NULL,
    'open' varchar
    (
    19
    ) COLLATE utf8mb4_bin NOT NULL,
    'close' varchar
    (
    19
    ) COLLATE utf8mb4_bin DEFAULT NULL,
    'hold' varchar
    (
    19
    ) COLLATE utf8mb4_bin DEFAULT NULL,
    'openDate' varchar
    (
    10
    ) COLLATE utf8mb4_bin NOT NULL,
    'openHour' smallint
    (
    2
    ) unsigned zerofill NOT NULL,
    'openMin' smallint
    (
    2
    ) unsigned zerofill NOT NULL,
    'closeDate' varchar
    (
    10
    ) COLLATE utf8mb4_bin DEFAULT NULL,
    'closeHour' smallint
    (
    2
    ) unsigned zerofill DEFAULT NULL,
    'closeMin' smallint
    (
    2
    ) unsigned zerofill DEFAULT NULL,
    'holdDate' varchar
    (
    10
    ) COLLATE utf8mb4_bin DEFAULT NULL,
    'holdHour' smallint
    (
    2
    ) DEFAULT NULL,
    'holdMin' smallint
    (
    2
    ) DEFAULT NULL,
    'timeDiff' int
    (
    11
    ) NOT NULL,
    'timeDiffHold' int
    (
    11
    ) DEFAULT NULL,
    'lastEdited' varchar
    (
    100
    ) COLLATE utf8mb4_bin NOT NULL,
    PRIMARY KEY
    (
    'ticket'
    ))
ENGINE=InnoDB AUTO_INCREMENT=81416 DEFAULT CHARSET=utf8mb4 COLLATE =utf8mb4_bin;