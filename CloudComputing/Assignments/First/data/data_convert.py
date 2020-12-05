import re, csv

txt_file = open('data_sample.txt')
product_file = open('products.csv', 'w')
product_writer = csv.writer(product_file)
product_writer.writerow(['ID' , 'ASIN', 'title', 'group', 'salesrank', 'average_rating', 'downloaded'])

review_file = open('reviews.csv', 'w')
review_writer = csv.writer(review_file)
review_writer.writerow(['customer_id', 'product_id', 'rating', 'votes', 'helpful', 'date'])

similar_file = open('similar.csv', 'w')
similar_writer = csv.writer(similar_file)
similar_writer.writerow(['product_id', 'similar_product_id'])
similar_set = set()

category_file = open('categories.csv', 'w')
category_writer = csv.writer(category_file)
category_writer.writerow(['category_name', 'category_code'])
category_set = set()

lines = txt_file.readlines()
num_lines = len(lines)
line_num = 0
products = []
product = {}

while line_num < num_lines:
    id_line = lines[line_num]
    id_pattern = r'^Id: +(\d+)\n'
    id_num = re.search(id_pattern, id_line).group(1)

    line_num = line_num+1
    
    asin_line = lines[line_num]
    asin_pattern = r'^ASIN: +(\w+)\n'
    asin = re.search(asin_pattern, asin_line).group(1)
    print(asin)
    line_num = line_num + 1
    
    if lines[line_num].strip().lower() == 'discontinued product':
        line_num = line_num + 2
        continue
    
    title_line = lines[line_num]
    title_pattern = r'^ +title: +(.*)\n'
    title = re.search(title_pattern, title_line).group(1)

    line_num = line_num + 1

    group_line = lines[line_num]
    group_pattern = r'^ +group: +(.*)\n'
    group = re.search(group_pattern, group_line).group(1)

    line_num = line_num + 1

    salesrank_line = lines[line_num]
    salesrank_pattern = r'^ +salesrank: +(-?\d+)\n'
    salesrank = re.search(salesrank_pattern, salesrank_line).group(1)

    line_num = line_num + 1

    similar_line = lines[line_num]
    similar_num_pattern = r'^ +similar: +(\d+)'
    similar_search = re.search(similar_num_pattern, similar_line)
    num_similar = similar_search.group(1)
    similar_pattern = r'^ +similar: +(?:\d+)'
    similar_products = []
    for i in range(int(num_similar)):
        similar_pattern = similar_pattern + '(?: +(\w+))'
    similar_prods_search = re.search(similar_pattern, similar_line)
    for i in range(int(num_similar)):
        similar_products.append(similar_prods_search.group(i+1))

    line_num = line_num + 1

    categories_line = lines[line_num]
    categories_num_pattern = r'^ +categories: +(\d+)\n'
    categories_search = re.search(categories_num_pattern, categories_line)
    num_categories = categories_search.group(1)
    categories = []
    for i in range(int(num_categories)):
        line_num = line_num + 1
        categories.append(lines[line_num].strip()[1:].split('|'))
    
    line_num = line_num + 1

    reviews_line = lines[line_num]
    reviews_num_pattern = r'^ +reviews: +total: +(\d+) +downloaded: +(\d+) +avg +rating: +(\d+(.\d+)?)\n'
    reviews_search = re.search(reviews_num_pattern, reviews_line)
    num_reviews = reviews_search.group(1)
    num_downloaded = reviews_search.group(2)
    avg_rating = reviews_search.group(3)
    reviews = []
    for i in range(int(num_downloaded)):
        line_num = line_num + 1
        review_line = lines[line_num]
        review_pattern = r'^ +(\d{4}-\d{1,2}-\d{1,2}) +cutomer: +(\w+) +rating: +(\d+) +votes: +(\d+) +helpful: +(\d+)\n?'
        review = re.search(review_pattern, review_line)
        rv = {
            'date' : review.group(1),
            'customer' : review.group(2),
            'rating' : review.group(3),
            'votes' : review.group(4),
            'helpful' : review.group(5)
        }
        reviews.append(rv)
    
    line_num = line_num + 2
    product = {
        'id' : id_num,
        'asin' : asin,
        'title' : title,
        'group' : group,
        'salesrank' : salesrank,
        'similar' : similar_products,
        'categories' : categories,
        'reviews' : {
            'total' : num_reviews,
            'downloaded' : num_downloaded,
            'avg_rating' : avg_rating,
            'list' : reviews
        }
    }
    product_writer.writerow([id_num, asin, title, group, salesrank, avg_rating, num_downloaded])
    for r in reviews:
        review_writer.writerow([r['customer'], asin, r['rating'], r['votes'], r['helpful'], r['date']])
    for s in similar_products:
        sim = asin + s
        if sim in similar_set:
            continue
        similar_set.add(sim)
        similar_writer.writerow([asin, s])
    for clist in categories:
        for c in clist:
            if c in category_set:
                continue
            category_set.add(c)
            category_pattern = r'(.*)\[(\d+)\]'
            category = re.search(category_pattern, c)
            category_writer.writerow([category.group(1), category.group(2)])
# print(products)