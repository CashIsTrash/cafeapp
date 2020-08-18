--Get table_name and server and receipt_date
SELECT
	cr.id AS receipt_id, ct.id AS table_id, ct.table_name,
    cr.receipt_server, cr.receipt_date
FROM
	cafe.tables ct,
    cafe.receipts_tables crt
JOIN
	cafe.receipts cr ON cr.id = crt.receipt_id
WHERE
	ct.id = crt.tables_id;

--Get specific table drinks added
SELECT
	d.drink_name, d.drink_category, d.drink_price
FROM
	cafe.drinks d,
  cafe.tables_drinks td
JOIN
	cafe.tables tb ON tb.id = td.table_id
WHERE
	d.id = td.drink_id AND td.table_id = 56;

-- Get tableId by Table Name
SELECT
	tl.id
FROM
	cafe.tables tl
WHERE
	tl.table_name = 'Table 1'
ORDER BY
    tl.id DESC LIMIT 1;

--Get drinkId by Drink Name
SELECT
	dr.id
FROM
	cafe.drinks dr
WHERE
	dr.drink_name = 'Mohito'

--Get TOTAL SUM of specific table
SELECT
	SUM(d.drink_price) as total_sum
FROM
	cafe.drinks d,
    cafe.tables_drinks td
JOIN
	cafe.tables tb ON tb.id = td.table_id
WHERE
	d.id = td.drink_id AND td.table_id = 56

--CREATING A VIEW
--normal query
SELECT
	tb.table_name,
  cd.drink_name,
  cd.drink_price
FROM
	cafe.tables_drinks td
JOIN
	cafe.tables tb ON tb.id = td.table_id
JOIN
	cafe.drinks cd ON cd.id = td.drink_id
--Converted to view:
CREATE VIEW
	all_ordered_drinks
AS SELECT
	tb.table_name,
    cd.id AS drink_id,
    cd.drink_name,
    cd.drink_price,
    cd.drink_category
FROM
	cafe.tables_drinks td
JOIN
	cafe.tables tb ON tb.id = td.table_id
JOIN
	cafe.drinks cd ON cd.id = td.drink_id

--get the view info:
SELECT
    *
FROM
   all_ordered_drinks

--Get some statistics of the all drinks bought
SELECT
  SUM(drink_price) as total_revenue,
  AVG(drink_price) as drink_price_average,
  COUNT(drink_price) as sold_drinks
FROM
	all_ordered_drinks

--Get all receipts
SELECT
    *
FROM
    cafe.receipts

--Remove all receipts

DELETE FROM cafe.receipts_tables crt WHERE crt.receipt_id = 1;
DELETE FROM cafe.receipts cr WHERE cr.id = 1;

-- Remove all drinks from a table ID:

DELETE FROM cafe.tables_drinks td WHERE td.table_id = 1;

--Get total sum of a specific table
