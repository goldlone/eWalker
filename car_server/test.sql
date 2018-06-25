select *
from Geo
where time = (select MAX(time) from Geo where userId='18435187057');
