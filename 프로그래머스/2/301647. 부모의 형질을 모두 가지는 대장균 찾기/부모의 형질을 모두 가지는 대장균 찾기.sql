-- 코드를 작성해주세요
select a.id, a.genotype, b.genotype as parent_genotype
from ECOLI_DATA a 
join ECOLI_DATA b
on a.PARENT_ID = b.ID
where a.genotype & b.genotype = b.genotype
order by a.id asc;
