
package Repositories;

import Entities.ClientMembership;
import java.util.List;

public interface ClientMembershipRepository {
    List<ClientMembership> findAll();
    ClientMembership findById(int id_member, int id_membership);
    int addClientMembership(ClientMembership clientMembership);
    int updateClientMembership(ClientMembership clientMembership);
    int deleteClientMembership(int id_member, int id_membership);
}
