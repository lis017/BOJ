SELECT ORDER_ID,
        PRODUCT_ID,
        OUT_DATE,
        CASE
            WHEN OUT_DATE <= '2022=05=01' THEN '출고완료'
            WHEN ISNULL(OUT_DATE) THEN '출고미정'
            ELSE '출고대기'
        END
FROM FOOD_ORDER
ORDER BY ORDER_ID ASC
;