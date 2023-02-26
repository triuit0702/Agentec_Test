package tri.chung.agentec;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AgentecProgram {

	// map contain key : id and value : Csv data
	Map<Integer, CsvModel> mapCsv = new HashMap<>();
	// variable contain root
	CsvModel root;

	public void readDataFromCsvFile(String path) {
		CsvModel csvModel;
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			br.readLine(); // this will read the first line
			String line;
			while ((line = br.readLine()) != null) {
				String[] values = line.split(",");
				if (values.length < 3) {
					csvModel = new CsvModel(values[0], values[1], "0");
				} else {
					csvModel = new CsvModel(values[0], values[1], values[2]);
					mapCsv.put(csvModel.getId(), csvModel);
				}
				
				
				if (values[0].equals("0")) {
					root = csvModel;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void buildHierarchical(CsvModel root) {
		CsvModel csvModel = root;
		List<CsvModel> childList = findChilds(csvModel.getId());
		childList.sort((ch1, ch2) -> ch1.getName().compareTo(ch2.getName()));
		csvModel.setChildList(childList);
		if (childList.size() == 0) {
			return;
		}
		for (CsvModel child: childList) {
			buildHierarchical(child);
		}
	}
	
	public List<CsvModel> findChilds(int id) {
		List<CsvModel> csvModelChilds = new ArrayList<>();
		for (CsvModel item: mapCsv.values()) {
			if (id == item.getParentId()) {
				csvModelChilds.add(item);
			}
		}
		return csvModelChilds;
	}
	
	
	public void printData(CsvModel root, int tabLevel) {
		String tab = "";
		for (int i = 0; i < tabLevel ; i ++) {
			// indent with 1 space when the hierarchy becomes deep.
			tab +=" ";
		}
		System.out.println(tab + root.getName());
		List<CsvModel> childs = root.getChildList();
		for (CsvModel item: childs) {
			printData(item, tabLevel + 1);
		}
	}

	public static void main(String[] args) {
		AgentecProgram agentecProgram = new AgentecProgram();
		if (args.length == 0) {
			System.out.println("Please input argument path csv file");
			return;
		}
		agentecProgram.readDataFromCsvFile(args[0]);
		agentecProgram.buildHierarchical(agentecProgram.root);
		agentecProgram.printData(agentecProgram.root, 0);
	}

}
