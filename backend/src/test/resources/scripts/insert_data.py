import json
import psycopg2
from psycopg2.extras import execute_values
from dotenv import load_dotenv
import os

# Load environment variables from .env file
load_dotenv()

# Database connection parameters
DB_HOST = "localhost"
DB_NAME = "yearbook"
DB_USER = os.getenv("DEV_USERNAME")
DB_PASSWORD = os.getenv("DEV_PASSWORD")

def load_json(file_path):
    with open(file_path, 'r') as file:
        return json.load(file)

def insert_users(cursor, users):
    user_ids = []
    user_values = [(user['username'], user['firstName'], user['lastName'], user['emailId'], user['password'], user['role']) for user in users]
    insert_user_query = """
    INSERT INTO public."User" (username, "firstName", "lastName", "emailId", password, role)
    VALUES %s
    RETURNING "userId";
    """
    execute_values(cursor, insert_user_query, user_values)
    user_ids = [row[0] for row in cursor.fetchall()]
    return user_ids

def insert_profiles(cursor, profiles, user_ids):
    profile_values = [
        (
            user_id,
            profile.get('bio'),
            profile.get('interests'),
            profile.get('hobbies'),
            profile.get('favoriteCodingSnack'),
            profile.get('favoriteQuote'),
            profile.get('mostLikelyToQuestion'),
            profile.get('mostLikelyToAnswer'),
            profile.get('mostMemorableBootcampMoment'),
            profile.get('adviceForFutureCohort'),
            profile.get('biggestChallenge'),
            profile.get('howYouOvercameIt'),
            profile.get('lastWords'),
            profile.get('instagram'),
            profile.get('linkedIn'),
            profile.get('previousField'),
            profile.get('currentRole'),
            profile.get('picture')
        )
        for user_id, profile in zip(user_ids, profiles)
    ]
    insert_profile_query = """
    INSERT INTO public."Profile" ("userId", bio, interests, hobbies, "favoriteCodingSnack", "favoriteQuote", "mostLikelyToQuestion", "mostLikelyToAnswer", "mostMemorableBootcampMoment", "adviceForFutureCohort", "biggestChallenge", "howYouOvercameIt", "lastWords", instagram, "linkedIn", "previousField", "currentRole", picture)
    VALUES %s;
    """
    execute_values(cursor, insert_profile_query, profile_values)

def main():
    data = load_json("data.json")
    users = data["users"]
    profiles = data["profiles"]

    conn = psycopg2.connect(
        host=DB_HOST,
        dbname=DB_NAME,
        user=DB_USER,
        password=DB_PASSWORD
    )
    cursor = conn.cursor()

    try:
        user_ids = insert_users(cursor, users)
        insert_profiles(cursor, profiles, user_ids)
        conn.commit()
    except Exception as e:
        conn.rollback()
        print(f"Error: {e}")
    finally:
        cursor.close()
        conn.close()

if __name__ == "__main__":
    main()