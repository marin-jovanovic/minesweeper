import { apiCalls } from './comm';



class GameBoard {
    constructor(rows, cols, containerId) {
      this.rows = rows;
      this.cols = cols;
      this.containerId = containerId;
      this.createTable();
        this.addTableStyle();

    }
  
    createTable() {
      let table = document.createElement("table");
  
      for (let i = 0; i < this.rows; i++) {
        let row = table.insertRow(i); 
        for (let j = 0; j < this.cols; j++) {
          let cell = row.insertCell(j); 
        }
      }
  
      let container = document.getElementById(this.containerId);

      container.appendChild(table);
    }

    addTableStyle() {
        var table = document.getElementById("tableContainer");
        if (!table) {
            console.log("no table");
        }

  var rows = table.getElementsByTagName("tr");
  for (var i = 0; i < rows.length; i++) {
    var cells = rows[i].getElementsByTagName("td");
    for (var j = 0; j < cells.length; j++) {
      var cell = cells[j];
      cell.addEventListener("click", function() {
        console.log("Cell clicked: " + cell.innerHTML);

        (async () => {
          let t = await getAllColours("f");
      })();

      });
    }
  }

    }

}

async function getAllColours(portfolioName) {
  console.log("portfolio name", portfolioName)
  return await apiCalls.handleNewResponse(
      await apiCalls.api.get(
          "board"
      )
  );
}
  
function main() {
    const gameBoard = new GameBoard(5, 4, "tableContainer");
}


onload = (event) => {main()};


