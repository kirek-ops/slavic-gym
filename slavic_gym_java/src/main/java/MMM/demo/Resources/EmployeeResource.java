package MMM.demo.Resources;

import MMM.demo.Daos.EmployeeDaoImpl;
import MMM.demo.Entities.Employee;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Map;
import MMM.demo.Utils.LocationFetcher;

@Slf4j
@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeResource {
  private final EmployeeDaoImpl employeeDaoImpl;

  @GetMapping("/{id}/get-positions")
  public ResponseEntity < Map <Integer, String> > getPositionsList(@PathVariable Integer id) {
    return ResponseEntity.ok(employeeDaoImpl.positions_gyms_ById(id));
  }
}
