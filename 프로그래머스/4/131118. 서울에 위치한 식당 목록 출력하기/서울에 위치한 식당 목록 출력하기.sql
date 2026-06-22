-- 코드를 입력하세요
SELECT a.REST_ID, a.REST_NAME, a.FOOD_TYPE, a.FAVORITES, a.ADDRESS, round(avg(b.review_score), 2) as SCORE
from rest_info a
join rest_review b
on a.rest_id = b.rest_id
where a.address like "서울%"
group by 1,2,3,4,5
order by 6 desc, 4 desc;