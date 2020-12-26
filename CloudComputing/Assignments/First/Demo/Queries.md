# Queries to Run

- `localhost:5000/runquery?query=select category_name, count(category_code) from category where category_name = 'Books' group by category_name having count(category_code) > 0`
- `localhost:5000/runquery?query=select asin, title, sum(downloaded) from product where asin = 0827229534 group by asin, title having sum(downloaded) > 0`
- `localhost:5000/runquery?query=select customer_id, max(rating) from review where customer_id = 'A2JW67OY8U6HHK' group by customer_id having max(rating) > 0`
- `localhost:5000/runquery?query=select product_id, count(similar_product_id) from similar where product_id = 1559362022 group by product_id having count(similar_product_id) > 0`
- `localhost:5000/runquery?query=select category_name, count(category_code) from category where category_name = 'Books' group by category_name having count(category_code) > 0`
