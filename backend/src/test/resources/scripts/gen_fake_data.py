import json
import random
from faker import Faker

# first run "pip3 install -r requirements.txt" within the reosurces/scripts folder to install dependencies
# Initialize Faker
fake = Faker()

def generate_data():
    users = []
    profiles = []
    
    # Generate 50 user records
    for i in range(1, 51):
        # Generate user data
        username = fake.user_name() + str(random.randint(1, 999))
        user = {
            "username": username,
            "firstName": fake.first_name(),
            "lastName": fake.last_name(),
            "emailId": fake.email(),
            "password": fake.password() if random.random() > 0.2 else None,
            "picture": "https://placeholder.pagebee.io/api/random/300/200" if random.random() > 0.2 else None,
            "role": "user" if random.random() < 0.1 else "admin"
        }
        users.append(user)
        
        # Generate profile data with 20% chance to omit optional fields
        include_optional = random.random() > 0.2
        profile = {}
        
        if include_optional or random.random() > 0.5:
            profile["bio"] = fake.text(max_nb_chars=200)
        if include_optional or random.random() > 0.5:
            profile["interests"] = ", ".join(fake.words(nb=random.randint(3, 6)))
        if include_optional or random.random() > 0.5:
            profile["hobbies"] = ", ".join(fake.words(nb=random.randint(3, 6)))
        if include_optional or random.random() > 0.5:
            profile["favoriteCodingSnack"] = fake.random_element(elements=("Chips", "Chocolate", "Nuts", "Fruit", "Coffee"))
        if include_optional or random.random() > 0.5:
            profile["favoriteQuote"] = fake.sentence()
        if include_optional or random.random() > 0.5:
            profile["linkedIn"] = f"linkedin.com/in/{username}"
        if include_optional or random.random() > 0.5:
            profile["previousField"] = fake.job()
        if include_optional or random.random() > 0.5:
            profile["currentRole"] = fake.job()
        
        profiles.append(profile)
    
    # Create a dictionary with both user and profile data
    data = {
        "users": users,
        "profiles": profiles
    }
    
 # Write to JSON file
    try:
        with open("data.json", "w") as f:
            json.dump(data, f, indent=4)
        print("Sample data generated and saved to data.json")
    except Exception as e:
        print(f"An error occurred while writing to the file: {e}")
    
    return data

if __name__ == "__main__":
    print("Generating data...")
    generate_data()
    print("Data generation complete.")
