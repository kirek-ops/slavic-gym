import os
import re
from pathlib import Path

# Function to clear directories
def clear_directory(directory):
    for file in Path(directory).glob("*"):
        file.unlink()

# Clear Entities, Repositories, and Daos directories
clear_directory('./Entities')
clear_directory('./Repositories')
clear_directory('./Daos')

# Read table definitions from create.sql
with open('../../../../../../slavic_gym_sql/create.sql', 'r') as file:
    sql_script = file.read()

# Regular expressions to parse table definitions
table_pattern = re.compile(r'CREATE TABLE (\w+) \((.*?)\);', re.DOTALL)
field_pattern = re.compile(r'^\s*(\w+)\s+(\w+)[\(\d+\)]*(?:\s+NOT NULL|\s+NULL)?', re.IGNORECASE)

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
        # Exclude primary key, foreign key, and constraint lines
        if field.upper().startswith(("PRIMARY", "FOREIGN", "CONSTRAINT")):
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
os.makedirs('./Daos', exist_ok=True)

# Template for entity class
entity_template = """
package MMM.demo.Entities;

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
package MMM.demo.Repositories;

import MMM.demo.Entities.{entity_name};
import java.util.List;

public interface {entity_name}Repository {{
    List<{entity_name}> findAll();
}}
"""

# Template for DAO implementation
dao_template = """
package MMM.demo.Daos;

import MMM.demo.Entities.{entity_name};
import MMM.demo.Repositories.{entity_name}Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class {entity_name}DaoImpl implements {entity_name}Repository {{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<{entity_name}> findAll() {{
        String sql = "SELECT * FROM {table_name}";
        return jdbcTemplate.query(sql, new {entity_name}RowMapper());
    }}

    private static class {entity_name}RowMapper implements RowMapper<{entity_name}> {{
        @Override
        public {entity_name} mapRow(ResultSet rs, int rowNum) throws SQLException {{
            {entity_name} result = new {entity_name}();
{fields_setters}
            return result;
        }}
    }}
}}
"""

# Function to convert field name and type to Java field declaration
def field_declaration(field):
    for name, java_type in field.items():
        return f"    private {java_type} {name};"

# Function to convert field name to setter calls in RowMapper
def field_setter(field):
    for name, java_type in field.items():
        if java_type == 'int':
            return f"            result.set{''.join([word.capitalize() for word in name.split('_')])}(rs.getInt(\"{name}\"));"
        elif java_type == 'boolean':
            return f"            result.set{''.join([word.capitalize() for word in name.split('_')])}(rs.getBoolean(\"{name}\"));"
        elif java_type == 'String':
            return f"            result.set{''.join([word.capitalize() for word in name.split('_')])}(rs.getString(\"{name}\"));"
        elif java_type == 'LocalDate':
            return f"            result.set{''.join([word.capitalize() for word in name.split('_')])}(rs.getDate(\"{name}\").toLocalDate());"
        elif java_type == 'LocalDateTime':
            return f"            result.set{''.join([word.capitalize() for word in name.split('_')])}(rs.getTimestamp(\"{name}\").toLocalDateTime());"
        elif java_type == 'ZonedDateTime':
            return f"            result.set{''.join([word.capitalize() for word in name.split('_')])}(rs.getObject(\"{name}\", ZonedDateTime.class));"
        elif java_type == 'Duration':
            return f"            result.set{''.join([word.capitalize() for word in name.split('_')])}(Duration.ofMillis(rs.getLong(\"{name}\")));"
        elif java_type == 'LocalTime':
            return f"            result.set{''.join([word.capitalize() for word in name.split('_')])}(rs.getTime(\"{name}\").toLocalTime());"
        elif java_type == 'BigDecimal':
            return f"            result.set{''.join([word.capitalize() for word in name.split('_')])}(rs.getBigDecimal(\"{name}\"));"
        else:
            return f"            result.set{''.join([word.capitalize() for word in name.split('_')])}(rs.getObject(\"{name}\", {java_type}.class));"

# Generate files for each table
for table, fields in tables.items():
    entity_name = ''.join([word.capitalize() for word in table.split('_')])
    entity_var = entity_name[0].lower() + entity_name[1:]

    # Generate entity fields
    entity_fields = "\n".join([field_declaration(field) for field in fields])
    entity_fields_setters = "\n".join([field_setter(field) for field in fields])

    # Fill entity template
    entity_content = entity_template.format(entity_name=entity_name, fields=entity_fields)

    # Write entity to a file
    with open(f"./Entities/{entity_name}.java", "w") as entity_file:
        entity_file.write(entity_content)

    # Fill repository template
    repository_content = repository_template.format(
        entity_name=entity_name
    )

    # Write repository to a file
    with open(f"./Repositories/{entity_name}Repository.java", "w") as repository_file:
        repository_file.write(repository_content)

    # Fill DAO template
    dao_content = dao_template.format(
        entity_name=entity_name,
        table_name=table,
        fields_setters=entity_fields_setters
    )

    # Write DAO to a file
    with open(f"./Daos/{entity_name}DaoImpl.java", "w") as dao_file:
        dao_file.write(dao_content)

print("Entity, Repository, and DAO files generated successfully.")
