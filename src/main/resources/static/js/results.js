    //console.log(myArray);
    let datesArray = [];
    let resultsObjectsArray = [];
    class ResultsObject {
      constructor(date, totalCorrect, totalIncorrect, name) {  // Constructor
        this.date = date;
        this.totalCorrect = totalCorrect;
        this.totalIncorrect = totalIncorrect;
        this.name = name;
      }
    }
    for (let entry of myArray){
        let totalDate = (entry.month) + "/" + (entry.day) + "/" + (entry.year);

        if (!datesArray.includes(totalDate)){
            datesArray.push(totalDate);
            if (entry.correct == "yes"){
                resultsObjectsArray.push(new ResultsObject(totalDate, 1, 0, entry.name));
            } else {
                resultsObjectsArray.push(new ResultsObject(totalDate, 0, 1, entry.name));
            }

        } else {
            for (let object of resultsObjectsArray){
                if (object.date == totalDate){
                    if (entry.correct == "yes"){
                        object.totalCorrect ++;
                    } else {
                        object.totalIncorrect ++;
                    }
                }
            }

        }
    }

    const dataset = [];
    const dateSet = [];
    for (object of resultsObjectsArray){
        dataset.push(Math.round(object.totalCorrect/(object.totalCorrect + object.totalIncorrect)*100));
        dateSet.push(object.date);

    }

    const w = 300;
    const h = 300;

    const svg = d3.select("body")
                  .append("svg")
                  .attr("class", "myD3rectangle")
                  .attr("width", w)
                  .attr("height", h);

    svg.selectAll("rect")
       .data(dataset)
       .enter()
       .append("rect")
       .attr("x", (d, i) => i * w/(dataset.length))
       .attr("y", (d, i) => h - 3 * d)
       .attr("width", w/(dataset.length + 1))
       .attr("height", (d, i) => 3 * d)
       .attr("fill", "navy");

    svg.selectAll("text")
       .data(dateSet)
       .enter()
       .append("text")
       .text((d) => d)
       .attr("x", (d, i) => i * 30)
       .attr("y", (d, i) => h - (3 * d) - 3);

