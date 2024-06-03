
package Repositories;

import Entities.AccessCodes;
import java.util.List;

public interface AccessCodesRepository {
    List<AccessCodes> findAll();
    AccessCodes findById(int id_member);
    int addAccessCodes(AccessCodes accessCodes);
    int updateAccessCodes(AccessCodes accessCodes);
    int deleteAccessCodes(int id_member);
}
