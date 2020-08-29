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
        let obj = {};
        obj.percent = (Math.round(object.totalCorrect/(object.totalCorrect + object.totalIncorrect)*100));
        obj.date = object.date;
        dataset.push(obj);
    }
    console.log(resultsObjectsArray);

    const w = 400;
    const h = 600;

    const svg = d3.select("body")
                  .append("svg")
                  .attr("class", "myD3rectangle")
                  .attr("width", w)
                  .attr("height", h);

    svg.selectAll("rect")
       .data(dataset)
       .enter()
       .append("rect")
       .attr("y", (d, i) => i * h/(dataset.length))
       .attr("x", 0)
       .attr("height", h/(dataset.length + 1))
       .attr("width", (d, i) => 2.5 * d.percent)
       .attr("fill", "black");

    svg.selectAll("text")
       .data(dataset)
       .enter()
       .append("text")
       .text((d) => d.date + ": " + d.percent + "%")
       .attr("height", h/(dataset.length + 1))
       .attr("y", (d, i) => (i * h/(dataset.length)) + (h/dataset.length + 1)/2)
       .attr("x", (d, i) => 2.5 * d.percent + 3);


