#!/bin/bash

# Load environment variables from .env file
if [[ -f .env ]]; then
    export $(grep -v '^#' .env | xargs)
else
    echo ".env file not found!"
    exit 1
fi

# Database credentials
DB_NAME=slavic_gym
#DB_NAME="${DB_NAME}"
DB_USER="${DB_USER}"

# SQL files
SQL_FILES=("clear.sql" "sequences.sql" "create.sql" "insert_samples.sql"
          "create_triggers.sql" "create_constraints.sql" "create_functions.sql")

# Execute each SQL file
for sql_file in "${SQL_FILES[@]}"; do
    if [[ -f "$sql_file" ]]; then
        echo "Executing $sql_file..."
        sudo -u "$DB_USER" psql -d "$DB_NAME" < "$sql_file"
        if [[ $? -ne 0 ]]; then
            echo "Error executing $sql_file"
            exit 1
        fi
    else
        echo "File $sql_file does not exist"
        exit 1
    fi
done

echo "All SQL files executed successfully."
