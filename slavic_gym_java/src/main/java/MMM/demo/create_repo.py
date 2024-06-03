# ../../../../../../slavic_gym_sql/create.sql

import os
import re
from pathlib import Path

# Function to clear directories
def clear_directory(directory):
    for file in Path(directory).glob("*"):
        file.unlink()

# Clear Entities and Repositories directories
clear_directory('./Entities')
clear_directory('./Repositories')

# Read table definitions from tables.sql
with open('../../../../../../slavic_gym_sql/create.sql', 'r') as file:
    sql_script = file.read()

# Regular expressions to parse table definitions
table_pattern = re.compile(r'CREATE TABLE (\w+) \((.*?)\);', re.DOTALL)
field_pattern = re.compile(r'(\w+)\s+(\w+)[\(\d+\)]*\s*(?:NOT NULL|NULL)?', re.IGNORECASE)

# Mapping from SQL types to Java types
type_mapping = {
    'INT': 'int',
    'VARCHAR': 'String',
    'DATE': 'LocalDate',
    'TIMESTAMP': 'LocalDateTime',
    'TIMESTAMPTZ': 'ZonedDateTime',
    'BOOLEAN': 'boolean',
    'DECIMAL': 'BigDecimal',
    'INTERVAL': 'Duration',
    'TIME': 'LocalTime'
}

# Parse table definitions
tables = {}
for table_match in table_pattern.finditer(sql_script):
    table_name = table_match.group(1)
    fields_block = table_match.group(2).strip()
    field_definitions = []
    for field in fields_block.split(',\n'):
        field = field.strip()
        # Exclude primary key and foreign key constraints
        if "PRIMARY KEY" in field or "FOREIGN KEY" in field:
            continue
        field_match = field_pattern.search(field)
        if field_match:
            field_name = field_match.group(1)
            field_type = field_match.group(2).upper()
            java_type = type_mapping.get(field_type, 'String')
            field_definitions.append({field_name: java_type})
    tables[table_name] = field_definitions

# Create directories if they don't exist
os.makedirs('./Entities', exist_ok=True)
os.makedirs('./Repositories', exist_ok=True)

# Template for entity class
entity_template = """
package Entities;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.Duration;
import java.time.LocalTime;
import java.math.BigDecimal;

@Getter
@Setter
public class {entity_name} {{
{fields}
}}
"""

# Template for repository interface
repository_template = """
package Repositories;

import Entities.{entity_name};
import java.util.List;

public interface {entity_name}Repository {{
    List<{entity_name}> findAll();
    {entity_name} findById({id_params});
    int add{entity_name}({entity_name} {entity_var});
    int update{entity_name}({entity_name} {entity_var});
    int delete{entity_name}({id_params_with_types});
}}
"""

# Function to convert field name and type to Java field declaration
def field_declaration(field):
    for name, java_type in field.items():
        return f"    private {java_type} {name};"

# Function to generate ID parameters for repository methods
def generate_id_params(fields):
    id_params = []
    for field in fields:
        for name, java_type in field.items():
            if name.startswith("id_"):
                id_params.append(f"{java_type} {name}")
    return ", ".join(id_params)

# Generate files for each table
for table, fields in tables.items():
    entity_name = ''.join([word.capitalize() for word in table.split('_')])
    entity_var = entity_name[0].lower() + entity_name[1:]

    # Generate entity fields
    entity_fields = "\n".join([field_declaration(field) for field in fields])

    # Fill entity template
    entity_content = entity_template.format(entity_name=entity_name, fields=entity_fields)

    # Write entity to a file
    with open(f"./Entities/{entity_name}.java", "w") as entity_file:
        entity_file.write(entity_content)

    # Generate repository method parameters
    id_params = generate_id_params(fields)
    id_params_with_types = generate_id_params(fields)

    # Fill repository template
    repository_content = repository_template.format(
        entity_name=entity_name,
        id_params=id_params,
        entity_var=entity_var,
        id_params_with_types=id_params_with_types
    )

    # Write repository to a file
    with open(f"./Repositories/{entity_name}Repository.java", "w") as repository_file:
        repository_file.write(repository_content)

print("Entity and Repository files generated successfully.")