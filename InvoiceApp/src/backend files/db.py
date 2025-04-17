import psycopg2
import creds

conn = psycopg2.connect(
    host="localhost",         
    database="ledgerly",
    user=creds.username,
    password = creds.password,
    port=5432                 
)

cur = conn.cursor()

cur.execute("SELECT * FROM transactions")

rows = cur.fetchall()

for row in rows:
    print(row)

cur.close()
conn.close()