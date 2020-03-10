create table timewindow (
	id identity,
	start_time int,
	end_time int
);
create table request (
	id identity,
	setId int,
	value double,
	longitude double,
	latitude double
);

create table request_v_timewindow(
    request_id int,
    timewindow_id int
);

alter table request_v_timewindow add foreign key (request_id) references request(id);
alter table request_v_timewindow add foreign key (timewindow_id) references timewindow(id);

insert into request(id, setId, value, latitude, longitude) select * from csvread('src/test/resources/requests.csv');
insert into timewindow(id, start_time, end_time) select * from csvread('src/test/resources/time_windows.csv');
insert into request_v_timewindow(request_id, timewindow_id) select * from csvread('src/test/resources/requests_v_time_windows.csv');