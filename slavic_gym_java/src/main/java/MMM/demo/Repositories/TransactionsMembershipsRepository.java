
package Repositories;

import Entities.TransactionsMemberships;
import java.util.List;

public interface TransactionsMembershipsRepository {
    List<TransactionsMemberships> findAll();
    TransactionsMemberships findById(int id_membership, int id_member);
    int addTransactionsMemberships(TransactionsMemberships transactionsMemberships);
    int updateTransactionsMemberships(TransactionsMemberships transactionsMemberships);
    int deleteTransactionsMemberships(int id_membership, int id_member);
}
