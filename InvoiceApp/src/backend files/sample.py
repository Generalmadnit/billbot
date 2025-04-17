# Sample backend file

from fastapi import FastAPI

app = FastAPI()

@app.get("/")
async def root():
    return {"message":"Hello World!"}

# to run the file use the command uvicorn {filename}:app --reload