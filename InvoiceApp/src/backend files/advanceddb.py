from fastapi import FastAPI
from fastapi.responses import JSONResponse
import psycopg2
import creds
from datetime import date, datetime

app = FastAPI()

@app.get("/")
async def root():
    return {"message":"Hello World!"}

def serialize_row(columns, row):
    serialized = {}
    for col, val in zip(columns, row):
        if isinstance(val, (date, datetime)):
            serialized[col] = val.isoformat()  # Convert to string
        else:
            serialized[col] = val
    return serialized

@app.get("/dbdata")
async def dbdata():
    try:
        conn = psycopg2.connect(
            host="localhost",
            database="ledgerly",
            user=creds.username,
            password=creds.password,
            port=5432
        )

        cur = conn.cursor()
        cur.execute("SELECT * FROM transactions")
        rows = cur.fetchall()
        columns = [desc[0] for desc in cur.description]
        data = []
        for row in rows:
            data.append(list(row))
        data = [serialize_row(columns, row) for row in rows]

        cur.close()
        conn.close()

        return JSONResponse(content=data)

    except Exception as e:
        return JSONResponse(content={"error": str(e)}, status_code=500)