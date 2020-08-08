--Get table_name and server and receipt_date
SELECT
	ct.id, ct.table_name,
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
