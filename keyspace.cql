CREATE KEYSPACE vod WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1 };

CREATE TYPE IF NOT EXISTS vod.id_name (
	id text,
        name text
);

CREATE TYPE IF NOT EXISTS vod.iso_name (
	iso_3166_1 text,
        name text
);


CREATE TABLE vod.movies ( 
	budget bigint,
	genres list<frozen<id_name>>,
	homepage text,
	id bigint PRIMARY KEY,
	keywords list<frozen<id_name>>,
	original_language text,
	original_title text,
	overview text,
	popularity double,
	production_companies list<frozen<id_name>>,
	production_countries list<frozen<iso_name>>,
	release_date date,
	revenue double,
	runtime int,
	spoken_languages list<frozen<iso_name>>,
	status text,
	tagline text,
	title text,
	vote_average double,
	vote_count bigint
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



