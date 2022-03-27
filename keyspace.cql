CREATE KEYSPACE IF NOT EXISTS vod WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1 };

CREATE TYPE IF NOT EXISTS vod.id_name (
	id int,
    name text
);

CREATE TYPE IF NOT EXISTS vod.id_name (
	id int,
    name text
);

CREATE TYPE IF NOT EXISTS vod.iso_name (
	iso_3166_1 text,
    name text
);


CREATE TABLE  IF NOT EXISTS vod.movies (
	budget bigint,
	genres frozen<list<id_name>>,
	homepage text,
	id bigint PRIMARY KEY,
	keywords frozen<list<id_name>>,
	original_language text,
	original_title text,
	overview text,
	popularity double,
	production_companies frozen<list<id_name>>,
	production_countries frozen<list<iso_name>>,
	release_date date,
	revenue double,
	runtime int,
	spoken_languages frozen<list<iso_name>>,
	status text,
	tagline text,
	title text,
	vote_average double,
	vote_count bigint
);


CREATE TABLE  IF NOT EXISTS vod.movies_stats (
	budget bigint PRIMARY KEY,
);

---CREATE TABLE vod.sessions (
---    employee_age int,
---    company_name text,
---    employee_email text,
---    PRIMARY KEY ((employee_age), company_name, employee_email)
---);
---
---CREATE TABLE vod.users_by_id (
---    employee_age int,
---    company_name text,
---    employee_email text,
---    PRIMARY KEY ((employee_age), company_name, employee_email)
---);
---
---CREATE TABLE vod.recommendations (
---    employee_age int,
---    company_name text,
---    employee_email text,
---    PRIMARY KEY ((employee_age), company_name, employee_email)
---);



