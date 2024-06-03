
package Repositories;

import Entities.Memberships;
import java.util.List;

public interface MembershipsRepository {
    List<Memberships> findAll();
    Memberships findById();
    int addMemberships(Memberships memberships);
    int updateMemberships(Memberships memberships);
    int deleteMemberships();
}
