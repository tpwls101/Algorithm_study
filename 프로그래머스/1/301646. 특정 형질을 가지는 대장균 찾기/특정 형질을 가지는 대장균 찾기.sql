-- A & B = B가 성립하면 A의 2진수 표현 속에 B의 2진수 비트가 포함되어 있다.
-- 2번 형질을 보유하지 않는다 = 2번째 비트가 1이 아닌 (8 4 2 1 중 2)
-- 1번 형질을 보유한다 = 1번째 비트가 1인 (8 4 2 1 중 1)
-- 3번 형질을 보유한다 = 3번째 비트가 1인 (8 4 2 1 중 4)
SELECT COUNT(*) AS COUNT
FROM ECOLI_DATA
WHERE NOT (GENOTYPE & 2) AND (GENOTYPE & 1 OR GENOTYPE & 4)