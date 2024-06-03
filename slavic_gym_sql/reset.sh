#!/bin/bash

# Database credentials
DB_NAME="slavic_gym"
DB_USER="postgres"

# SQL files
SQL_FILES=("clear.sql" "create.sql" "sequences.sql" "insert_samples.sql")

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
