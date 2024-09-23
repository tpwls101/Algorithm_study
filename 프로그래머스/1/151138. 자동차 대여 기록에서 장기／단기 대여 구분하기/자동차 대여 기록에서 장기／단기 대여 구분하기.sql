-- 코드를 입력하세요
SELECT HISTORY_ID, CAR_ID, DATE_FORMAT(START_DATE, '%Y-%m-%d') AS START_DATE, DATE_FORMAT(END_DATE, '%Y-%m-%d') AS END_DATE, 
                    CASE
                        WHEN DATEDIFF(END_DATE, START_DATE) + 1 >= 30 THEN '장기 대여'
                        ELSE '단기 대여'
                    END AS RENT_TYPE
FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
WHERE START_DATE LIKE '2022-09%'
ORDER BY HISTORY_ID DESC

#SELECT * FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY #506번 하루 510 3일
# SELECT DATEDIFF(END_DATE, START_DATE) FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY WHERE HISTORY_ID=510