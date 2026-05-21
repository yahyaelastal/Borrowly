# Borrowly
## Project Description
Borrowly is a peer-to-peer rental marketplace designed to maximize the utility of high-value items within a community. The platform allows users to monetize their underutilized assets—such as power tools, professional cameras, and camping gear—while providing others with an affordable, short-term rental alternative to expensive purchases. By managing secure user authentication, image resource uploads, and a date-based booking system, Borrowly fosters a sustainable circular economy.

## Team Members
- Elastal Yahya (yahia.elastal@stud.fils.upb.ro)
- Alsalman Ahmad (aal2@stud.fils.upb.ro)
- Aktas Furkan Said (furkan_said.aktas@stud.fils.upb.ro)

## Informal User Stories
### 1. The Owner (Handyman / Homeowner)
- Story A: As a professional worker, I want to upload photos and set a daily price for my idle power tools so that I can generate passive income from gear I already own.

- Story B: As an equipment owner, I want to list my high-end camping tent on a secure platform so that I can track who is using it and ensure my investment is put to good use instead of gathering dust.

### 2. The Renter (Student / Casual User)

- Story A: As a student, I want to browse local listings and book a professional camera stabilizer for two days so that I can complete my film project and pass my class without overspending.

- Story B: As an apartment renter, I want to rent a heavy-duty ladder from a neighbor for a one-day repair so that I do not have to find storage space for a bulky item I will rarely use.

### 3. The Admin (Platform Manager)

- Story A: As a platform administrator, I want to manage user accounts and remove inappropriate listings so that I can ensure the marketplace remains a safe and professional environment.

- Story B: As a platform administrator, I want to update database categories and add new item types so that the site stays organized and up to date as the platform grows.


## 🐳 Running with Docker

This is the easiest way to run the full application — no Java or PostgreSQL installation needed.

### Prerequisites

- [Docker Desktop](https://www.docker.com/products/docker-desktop/) installed and running

### Steps

**1. Clone the repository**
```bash
git clone <your-repo-url>
cd Borrowly/Borrowly
```

**2. Start the application**
```bash
docker-compose up --build
```

This single command will:
- Pull the **PostgreSQL 16** database image and start it
- **Build** the Spring Boot application from source (using Maven inside Docker)
- **Start** the app connected to the database

> First run takes a few minutes while Maven downloads dependencies. Subsequent runs are much faster.

**3. Open the app**

Once you see `Started BorrowlyApplication` in the logs, visit:

```
http://localhost:8080
```

### Stopping the app

```bash
# Stop containers (keeps database data)
docker-compose down

# Stop and delete all data (full reset)
docker-compose down -v
```

### Services

| Service  | Container       | Port            |
|----------|-----------------|-----------------|
| App      | `borrowly-app`  | `8080` → `8080` |
| Database | `borrowly-db`   | `5433` → `5432` |

> The database is also accessible at `localhost:5433` with credentials `borrowly` / `borrowly123` using any PostgreSQL client (e.g. IntelliJ Database tool, DBeaver, pgAdmin).

## Figma Design: https://glass-chuck-23706870.figma.site/

