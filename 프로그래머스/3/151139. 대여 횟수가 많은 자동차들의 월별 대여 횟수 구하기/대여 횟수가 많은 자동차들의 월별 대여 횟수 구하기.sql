/*
select month(start_date) as MONTH, car_id, count(*) as RECORDS
from CAR_RENTAL_COMPANY_RENTAL_HISTORY
where date_format(start_date, "%Y-%m") between "2022-08" and "2022-10"
group by 1, 2
;
*/

select month(start_date) as MONTH,CAR_ID, count(*) as RECORDS
from CAR_RENTAL_COMPANY_RENTAL_HISTORY
where (date_format(start_date, "%Y-%m") between "2022-08" and "2022-10") and car_id in (
    select car_id
    from CAR_RENTAL_COMPANY_RENTAL_HISTORY
    where date_format(start_date, "%Y-%m") between "2022-08" and "2022-10"
    group by car_id
    having count(*) >= 5
)
group by 1, 2
order by 1 asc, 2 desc;