package employeeCrude.com.Empcrud.controller;




import org.springframework.web.bind.annotation.*;

import employeeCrude.com.Empcrud.entity.employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final Map<Integer, employee> employeeMap = new HashMap<>();
    private int idCounter = 1;

    @PostMapping("/addEMP")
    public employee addEmployee(@RequestBody employee employee) {
        employee.setId(idCounter++);
        employeeMap.put(employee.getId(), employee);
        return employee;
    }
    @PostMapping("/addAllEMp")
    public List<employee> AddALlEmployees(@RequestBody List<employee> employees) {
        List<employee> createdEmployees = new ArrayList<>();
        for (employee employee : employees) {
            employee.setId(idCounter++);
            employeeMap.put(employee.getId(), employee);
            createdEmployees.add(employee);
        }
        return createdEmployees;
    }

    @GetMapping("/{id}")
    public employee getEmployee(@PathVariable int id) {
        return employeeMap.get(id);
    }

    @PutMapping("/{id}")
    public employee updateEmployee(@PathVariable int id, @RequestBody employee employee) {
        if (!employeeMap.containsKey(id)) {
            throw new IllegalArgumentException("Employee with id " + id + " not found");
        }
        employee.setId(id);
        employeeMap.put(id, employee);
        return employee;
    }
    @GetMapping("/getallEmp")
    public Map<Integer, employee> getAllEmployees() {
        return employeeMap;
    }

    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable int id) {
        if (!employeeMap.containsKey(id)) {
            throw new IllegalArgumentException("Employee with id " + id + " not found");
        }
        employeeMap.remove(id);
        return "Employee with id " + id + " has been deleted";
    }
}
